package cz.horcica.todoizer.web;

import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.users.User;

import cz.horcica.todoizer.SecurityHelper;
import cz.horcica.todoizer.data.Task;
import cz.horcica.todoizer.logic.TaskRepository;
import cz.horcica.todoizer.logic.UserAccountRepository;

public class TasksListBean {
	
	private static final Logger log = Logger.getLogger(TaskRepository.class.getName());

	
	private TaskRepository repository;
	private List<Task> tasks = null;
	
	private String labelFilter = null;
	
	
	public TasksListBean() {
		repository = new TaskRepository();
		isThisNewUser();
	}


	private void isThisNewUser() {
		User user = SecurityHelper.getUser();
		
		UserAccountRepository userRepository = new UserAccountRepository();
		if(!userRepository.existsUser(user.getUserId())){
			log.info("New user " + SecurityHelper.getUser().getEmail());
			
			// Add user
			userRepository.addUser(user);
			
			// Add default tasks
			repository.addTaskWithLabels("Přečíst nápovědu", "todoizer");
			//repository.addTaskWithLabels("Poslat děkovný dopis autorovi aplikace", "todoizer");
		}
	}
	
	
	public List<Task> getTasks(){
		if(tasks == null){
			tasks = repository.getTasks(labelFilter);
		}	
		return tasks;
	}
	
	
	public int getCount(){
		return getTasks().size();
	}
	
	
	public boolean isEmpty(){
		return getCount() == 0;
	}
	
	public boolean isFiltered(){
		return labelFilter != null;
	}
	
	public void close(){
		repository.close();
	}
	
	
	/**
	 * Filter for labels
	 * 
	 * @return filter
	 */
	public String getFilter() {
		return labelFilter;
	}

	/**
	 * Set filter for labels (null for no filter)
	 * 
	 * @param labelFilter
	 */
	public void setFilter(String labelFilter) {
		this.labelFilter = labelFilter;
	}
}

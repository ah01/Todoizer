package cz.horcica.todoizer.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cz.horcica.todoizer.SecurityHelper;
import cz.horcica.todoizer.data.PMF;
import cz.horcica.todoizer.data.Task;

/**
 * Handle working with tasks
 * 
 * @author Adam Horcica
 */
public class TaskRepository {
	
	private final String TASKS_QUERY = "select from " + Task.class.getName() + "  order by name, labels";
	
	private PersistenceManager pm;
	
	/**
	 * UserId of tasks owner
	 */
	private String userId;
	
	
	public TaskRepository(){
		this(SecurityHelper.getUser().getUserId());
	}
	
	
	public TaskRepository(String userId){
		pm = PMF.getPM();
		this.userId = userId;
	}
	
	
	/**
	 * Return all tasks of user
	 * 
	 * @return tasks
	 */
	public List<Task> getTasks(){
		return getTasks(null);
	}
	
	
	/**
	 * Return all users tasks with specific label
	 * 
	 * @param filter label
	 * @return tasks
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getTasks(String label){
		List<Task> result = null;
		Query q = pm.newQuery(TASKS_QUERY);
		
		
		if(label == null){
			q.setFilter("ownerId == '" + userId + "'");
		}else{
			q.setFilter("ownerId == '" + userId + "' && labels == '" + label + "'");
		}
		
		result = (List<Task>) q.execute();
		return result;
	}
	
	
	/**
	 * Add new task with labels
	 * @param name Name of task
	 * @param labels Labels (string separated by commas)
	 */
	public void addTaskWithLabels(String name, String labels){
		Set<String> labelsSet = null;
		
		if(labels != null){
			String[] labelsArray = labels.trim().split(",");
			List<String> l = new ArrayList<String>();
			for (String string : labelsArray) {
				String s = string.trim();
				if(!s.isEmpty()){
					l.add(s.replace(" ", "_"));
				}
			}
			
			labelsSet = new TreeSet<String>(l);
		}
		
		addTask(name, labelsSet);
	}
		
	
	/**
	 * Add new task with set of labels
	 * 
	 * @param name Name of task
	 * @param labels Set of labels
	 */
	public void addTask(String name, Set<String> labels){
		Task task = new Task();
		
		task.setOwnerId(userId);
		task.setName(name);
		task.setState(false);
		
		if(labels != null){
			task.setLabels(labels);
		}
		
		pm.makePersistent(task);
	}
	
	
	/**
	 * Return task by specific Id OR null
	 * 
	 * @param id Id of task
	 * @return task
	 */
	public Task getTaskById(Long id){
		Task result;
		try{
			result = pm.getObjectById(Task.class, id);
		}catch(JDOObjectNotFoundException e){
			result = null;
		}
		return result;
	}
	
	
	/**
	 * Change state of task given by Id
	 * 
	 * @param id Id of task
	 * @return new state of task
	 */
	public boolean checkTask(Long id){
		Task task = getTaskById(id);
		if(task == null){
			throw new JDOObjectNotFoundException("Task not found");
		}
		
		boolean newState = ! task.getState();
		task.setState(newState);

		return newState;
	}
	
	
	/**
	 * Delete task by id
	 * 
	 * @param id Id of task
	 * @return 
	 */
	public boolean deleteTaskById(Long id){
		Task task = getTaskById(id);
		
		if(task != null){
			
			if(!task.getOwnerId().equals(userId)){
				throw new SecurityException("You can not delete this task");
			}
			
			pm.deletePersistent(task);
			return true;
			
		} else {
			return false;
		}
	}
	
	
	/**
	 * Close
	 */
	public void close(){
		if(pm != null) pm.close();
	}
	
}

package cz.horcica.todoizer.logic;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import cz.horcica.todoizer.SecurityHelper;
import cz.horcica.todoizer.data.PMF;
import cz.horcica.todoizer.data.Task;

public class TaskRepository {
	
	private final String TASKS_QUERY = "select from " + Task.class.getName() + "  order by name";
	
	private PersistenceManager pm;
	
	public TaskRepository(){
		pm = PMF.getPM();
	}
	
	public List<Task> getTasks(){
		List<Task> result = null;
		Query q = pm.newQuery(TASKS_QUERY);
		
		q.setFilter("ownerId == '" + SecurityHelper.getUser().getUserId() + "'");
		
		result = (List<Task>) q.execute();
		return result;
	}
	
	public void addTask(String name){
		
		Task task = new Task();
		
		task.setOwnerId(SecurityHelper.getUser().getUserId());
		task.setName(name);
		task.setState(false);
		
		pm.makePersistent(task);
	}
	
	public void close(){
		if(pm != null) pm.close();
	}
	
}

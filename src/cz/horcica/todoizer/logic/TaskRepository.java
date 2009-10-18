package cz.horcica.todoizer.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import cz.horcica.todoizer.SecurityHelper;
import cz.horcica.todoizer.data.PMF;
import cz.horcica.todoizer.data.Task;

public class TaskRepository {
	
	private final String TASKS_QUERY = "select from " + Task.class.getName() + "  order by name, labels";
	
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
	
	public void addTask(String name){
		addTask(name, null);
	}
	
	public void addTask(String name, Set<String> labels){
		Task task = new Task();
		
		task.setOwnerId(SecurityHelper.getUser().getUserId());
		task.setName(name);
		task.setState(false);
		
		if(labels != null){
			task.setLabels(labels);
		}
		
		pm.makePersistent(task);
	}
	
	public void close(){
		if(pm != null) pm.close();
	}
	
}

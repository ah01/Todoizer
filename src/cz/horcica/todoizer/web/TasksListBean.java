package cz.horcica.todoizer.web;

import java.util.List;

import cz.horcica.todoizer.data.Task;
import cz.horcica.todoizer.logic.TaskRepository;

public class TasksListBean {
	
	private TaskRepository repository;
	private List<Task> tasks = null;
	
	private String labelFilter = null;
	
	
	public TasksListBean() {
		repository = new TaskRepository();
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

package cz.horcica.todoizer.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import cz.horcica.todoizer.data.PMF;
import cz.horcica.todoizer.data.Task;
import cz.horcica.todoizer.logic.TaskRepository;

@SuppressWarnings("serial")
public class TaskSaver extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String name = req.getParameter("name");
		String labels = req.getParameter("labels");
		
		if(labels == null) labels = "";
		
		String[] labelsArray = labels.trim().split(",");
		List<String> l = new ArrayList<String>();
		for (String string : labelsArray) {
			String s = string.trim();
			if(!s.isEmpty()){
				l.add(s.replace(" ", "_"));
			}
		}
		
		Set<String> s = new TreeSet<String>(l);
		
		TaskRepository repository = new TaskRepository();
		repository.addTask(name, s);
		repository.close();
		
		resp.sendRedirect("/list");
	}
}

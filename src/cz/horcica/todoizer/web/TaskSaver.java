package cz.horcica.todoizer.web;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import cz.horcica.todoizer.data.PMF;
import cz.horcica.todoizer.data.Task;
import cz.horcica.todoizer.logic.TaskRepository;

@SuppressWarnings("serial")
public class TaskSaver extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String name = req.getParameter("name");
		
		TaskRepository repository = new TaskRepository();
		repository.addTask(name);
		repository.close();
		
		resp.sendRedirect("/list");
	}
}

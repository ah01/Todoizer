package cz.horcica.todoizer.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.horcica.todoizer.data.Task;
import cz.horcica.todoizer.logic.TaskRepository;

@SuppressWarnings("serial")
public class TaskDeleter extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String id = req.getParameter("id");
		
		if(id != null){
		
			Long idd = Long.parseLong(id);
			
			TaskRepository repository = new TaskRepository();
			repository.deleteTaskById(idd);
			repository.close();
			
		}
		
		resp.sendRedirect("/list");
	}
}

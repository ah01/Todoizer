package cz.horcica.todoizer.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.horcica.todoizer.logic.TaskRepository;

/**
 * Date task by ID (get request)
 * 
 * @author Adam Horcica
 */
@SuppressWarnings("serial")
public class TaskDeleter extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String id = req.getParameter("id");
		
		if(id != null){
			Long idd = Long.parseLong(id); //TODO: uncatch exception
			
			TaskRepository repository = new TaskRepository();
			try{
				repository.deleteTaskById(idd);
			}finally{
				repository.close();
			}
			
		}		
		resp.sendRedirect("/list/");
	}
}

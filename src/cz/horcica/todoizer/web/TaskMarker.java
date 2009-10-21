package cz.horcica.todoizer.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.horcica.todoizer.logic.TaskRepository;

/**
 * Change state of task by ID (Get request)
 * 
 * @author Adam Horcica
 */
@SuppressWarnings("serial")
public class TaskMarker extends HttpServlet  {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String idString = req.getParameter("id");
		
		if(idString != null){
			Long id = Long.parseLong(idString); //TODO: uncatch exception
			
			TaskRepository repository = new TaskRepository();
			try{
				boolean state = repository.checkTask(id);
				resp.getWriter().print(state ? "1" : "0");
			}finally{
				repository.close();
			}
		}
	}
}

package cz.horcica.todoizer.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.horcica.todoizer.data.Task;
import cz.horcica.todoizer.logic.TaskRepository;

@SuppressWarnings("serial")
public class TaskMarker extends HttpServlet  {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String idString = req.getParameter("id");
		
		if(idString != null){
			Long id = Long.parseLong(idString);
			
			TaskRepository repository = new TaskRepository();
			
			boolean state = repository.checkTask(id);
			
			resp.getWriter().print(state ? "1" : "0");
			
			
		}
		
		
	}
}

package cz.horcica.todoizer.web;

import java.io.IOException;

import javax.servlet.http.*;

import cz.horcica.todoizer.logic.TaskRepository;

/**
 * Create new task (Post request)
 * 
 * @author Adam Horcica
 */
@SuppressWarnings("serial")
public class TaskCreater extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String name = req.getParameter("name");
		String labels = req.getParameter("labels");
		
		if(name != null){
			name = name.trim();
			if(!name.isEmpty()){
				TaskRepository repository = new TaskRepository();
				try{
					repository.addTaskWithLabels(name, labels);
				}finally{
					repository.close();
				}
			}
		}
		resp.sendRedirect("/list/");
	}
}

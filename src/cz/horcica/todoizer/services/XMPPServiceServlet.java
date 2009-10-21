package cz.horcica.todoizer.services;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

import cz.horcica.todoizer.data.Task;
import cz.horcica.todoizer.data.UserAccount;
import cz.horcica.todoizer.logic.TaskRepository;
import cz.horcica.todoizer.logic.UserAccountRepository;

/**
 * Handle incoming XMPP messages
 * 
 * @author Adam Horcica
 */
@SuppressWarnings("serial")
public class XMPPServiceServlet extends HttpServlet  {
		 
	private static final Logger log = Logger.getLogger(XMPPServiceServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		XMPPService xmpp = XMPPServiceFactory.getXMPPService();
		Message message = xmpp.parseMessage(req);

		JID fromJid = message.getFromJid();
		String userId = fromJid.getId().substring(0, fromJid.getId().indexOf("/")); // "user@domain/robot" <- remove the robot part
		String body = message.getBody().trim();

		log.info("Message form " + fromJid.getId() + " : " + body);
		
		UserAccountRepository users = new UserAccountRepository();
		UserAccount user = users.getUserAccountByEmail(userId);
		
		if(user == null){
			log.warning("Message from unknown user " + userId);
			sendMessage(fromJid, "Nejte uživatel aplikace todoizer.appspot.com");
		}else{
			
			TaskRepository repository = new TaskRepository( user.getId() );
			
			if(body.equals("?")){ // send list of tasks
				sendMessage(fromJid, getTasksMessage(repository));
			}else{
				addNewTask(body, repository); // add new tasks
				sendMessage(fromJid, "Úkol přidán do seznamu");
			}
			repository.close();
		}
		
	}

	/**
	 * Add new task to repository (first line of body)
	 * 
	 * @param body
	 * @param repository
	 */
	private static void addNewTask(String body, TaskRepository repository) {
		String task = body.trim();
		
		if(task.indexOf("\n") > -1){ // take first line
			task = task.substring(0, task.indexOf("\n"));
		}
		
		repository.addTask(task, null);
	}
	
	
	/**
	 * Prepare tasks message
	 * 
	 * @param repository
	 * @return
	 */
	private static String getTasksMessage(TaskRepository repository) {
		StringBuilder sb = new StringBuilder();
		sb.append("Váš seznam úkolů:\n");
		for(Task task : repository.getTasks()){
			if(task.getState()){
				sb.append(" ok ");
			}else{
				sb.append(" -- ");
			}
			
			if(task.getLabels() != null && !task.getLabels().isEmpty()){
				String separator = "";
				sb.append("{");
				for(String label : task.getLabels()){
					sb.append(separator);
					sb.append(label);
					separator=" ";
				}
				sb.append("} ");
			}
			sb.append(task.getName());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Send XMPP message
	 * 
	 * @param to
	 * @param message
	 * @return
	 */
	private boolean sendMessage(JID to, String message){
		        
        Message msg = new MessageBuilder()
            .withRecipientJids(to)
            .withBody(message)
            .build();
                
        boolean messageSent = false;
        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
        if (xmpp.getPresence(to).isAvailable()) {
            SendResponse status = xmpp.sendMessage(msg);
            messageSent = (status.getStatusMap().get(to) == SendResponse.Status.SUCCESS);
        }

        if(messageSent){
        	log.info("Sent message to " + to.getId());
        }else{
        	log.warning("Unable to sent msg to " + to.getId());
        }
        
		return messageSent;
	}
	
	
}

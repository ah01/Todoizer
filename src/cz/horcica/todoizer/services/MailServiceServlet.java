package cz.horcica.todoizer.services;

import java.io.IOException;
import java.util.logging.Logger;

import java.util.Properties; 

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session; 
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 
import javax.servlet.http.*;

import cz.horcica.todoizer.data.UserAccount;
import cz.horcica.todoizer.logic.TaskRepository;
import cz.horcica.todoizer.logic.UserAccountRepository;

/**
 * Handle incoming mail
 * 
 * @author Adam Horcica
 */
@SuppressWarnings("serial")
public class MailServiceServlet extends HttpServlet  {
		 
	private static final Logger log = Logger.getLogger(XMPPServiceServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		MimeMessage message;
		String from, subject;
		
		Properties props = new Properties(); 
        Session session = Session.getDefaultInstance(props, null);
        
        try {
			 message = new MimeMessage(session, req.getInputStream());
		
			 Address[] froms = message.getFrom();
			 if(froms.length < 1){
				 return;
			 }
			 InternetAddress address = new InternetAddress(froms[0].toString());
			 from = address.getAddress();
			 
			 subject = removeReAndFwd(message.getSubject());
			 
        } catch (MessagingException e) {
			log.severe(e.getMessage());
			return;
		}
        
		log.info("Message form " + from + " : " + subject);
		
		UserAccountRepository users = new UserAccountRepository();
		UserAccount user = users.getUserAccountByEmail(from);
		
		if(user == null){
			log.warning("Message from unknown user " + from);
		}else{
			TaskRepository repository = new TaskRepository( user.getId() );
			try{
				repository.addTask(subject, null);
				log.info("New task from " + from + " : " + subject);
			}finally{
				repository.close();
			}
		}
	}
	
	/**
	 * Remove "Re:" and "Fwd:" from begining of subject
	 * 
	 * @param subject
	 * @return
	 */
	private static String removeReAndFwd(String subject){
		while (true) {
			subject = subject.trim();
			if(subject.startsWith("Re:")){
				subject = subject.substring(3);
			}else if(subject.startsWith("Fwd:")){
				subject = subject.substring(4);
			}else{
				break;
			}
		}
		return subject;
	}
		
}

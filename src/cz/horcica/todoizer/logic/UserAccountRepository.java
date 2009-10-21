package cz.horcica.todoizer.logic;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.appengine.api.users.User;

import cz.horcica.todoizer.data.*;

/**
 * Handle working with UserAccounts
 * 
 * @author Adam Horcica
 */
public class UserAccountRepository {
	
	private static final Logger log = Logger.getLogger(UserAccountRepository.class.getName());
	
	private PersistenceManager pm;
	
	public UserAccountRepository() {
		pm = PMF.getPM();
	}
	
	
	/**
	 * Add new user to DB
	 * 
	 * @param user
	 */
	public void addUser(User user){
		log.info("Create new user " + user.getEmail() + " (" + user.getNickname() + ")");
		
		UserAccount ua = new UserAccount();
		
		ua.setId(user.getUserId());
		ua.setEmail(user.getEmail());
		
		pm.makePersistent(ua);
		pm.close();
	}
	
	
	/**
	 * Return UserAcount by EMail (check if user by email exists)
	 * 
	 * @param mail
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UserAccount getUserAccountByEmail(String mail){
		
		List<UserAccount> users = (List<UserAccount>) pm.newQuery("select from " + UserAccount.class.getName() + " where email == '" + mail + "'").execute();
		
		if(users.size() == 1){
			return users.get(0);
		} else {
			return null;
		}
		
	}
	
	
	/**
	 * Test if user by ID exists (check for new user in system)
	 * @param userId
	 * @return
	 */
	public boolean existsUser(String userId){
		try{
			UserAccount user = pm.getObjectById(UserAccount.class, userId);
			return user != null;
		}catch(JDOObjectNotFoundException e){
			return false;
		}
		
	}
	
}

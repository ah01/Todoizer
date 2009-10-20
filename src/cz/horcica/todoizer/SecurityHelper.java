package cz.horcica.todoizer;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SecurityHelper {
	
	/**
	 * Return logout URL address
	 * 
	 * @return logout URL
	 */
	public static String getLogoutLink(){
		return UserServiceFactory.getUserService().createLogoutURL("/");
	}
	
	/**
	 * Return login URL address
	 * 
	 * @return login URL
	 */
	public static String getLoginLink(){
		return UserServiceFactory.getUserService().createLoginURL("/list/");
	}
	
	public static String getUserName(){
		return getUser().getEmail();
	}
	
	/**
	 * Return logged user OR throw exception
	 * 
	 * @exception SecurityException
	 * @return logged user
	 */
	public static User getUser(){
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if(user == null){
			throw new SecurityException("No user is loged in.");
		}
		
		return user;
	}
}

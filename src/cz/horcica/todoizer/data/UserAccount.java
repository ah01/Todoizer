package cz.horcica.todoizer.data;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * UserAccount DTO
 * 
 * Handle user ID and Mail (most especially for XMPP and Mail services)
 * 
 * @author Adam Hořčica
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserAccount {
	
	@Persistent
	@PrimaryKey
	private String id;
	
	@Persistent
	private String email;

	// --- Setters & Getters ---
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

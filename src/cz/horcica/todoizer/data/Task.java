package cz.horcica.todoizer.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.users.User;

/**
 * Task DTO
 * 
 * @author Adam Hořčica
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Task {
	
	// --- Fields ---
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String name;
    
    @Persistent
    private boolean state;

    @Persistent
    private String ownerId;
    
    public Long getId() {
		return id;
	}
    
    // --- Getters and Setters ---
    
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}

package com.nagarro.model;

import java.util.List;




import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 * 
 * This class represents the user related information.
 */

@Entity
public class User {
 
	@Id
	private Integer id;
	private String username;
	private String password;
	@OneToMany(mappedBy ="username", cascade=CascadeType.ALL , fetch=FetchType.EAGER)
	List<ImageManagement> preview;
	
	public List<ImageManagement> getPreview() {
		return preview;
	}
	public void setPreview(List<ImageManagement> preview) {
		this.preview = preview;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(Integer id, String username, String password, List<ImageManagement> preview) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.preview = preview;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", preview=" + preview + "]";
	}
	
	

	
	
}

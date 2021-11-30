package com.nagarro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String emailId;
private String firstName;
private String lastName;
private String password;
public User() {
	super();
	
}
public User(int id, String emailId, String firstName, String lastName, String password) {
	super();
	this.id = id;
	this.emailId = emailId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.password = password;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}

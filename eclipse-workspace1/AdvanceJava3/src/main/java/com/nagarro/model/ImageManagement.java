package com.nagarro.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
/*
 * This class will create a table for storing image related information.
 * 
 */

public class ImageManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	long size;
	@Lob
	byte [] preview;
	
	@ManyToOne
	@JoinColumn
	User username;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUsername() {
		return username;
	}
	public void setUsername(User username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public byte[] getPreview() {
		return preview;
	}
	@Override
	public String toString() {
		return "ImageManagement [id=" + id + ", name=" + name + ", size=" + size + ", preview="
				+ Arrays.toString(preview) + ", username=" + username + "]";
	}
	public void setPreview(byte[] preview) {
		this.preview = preview;
	}
	public ImageManagement(int id, String name, long size, byte[] preview, User username) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.preview = preview;
		this.username = username;
	}
	public ImageManagement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}

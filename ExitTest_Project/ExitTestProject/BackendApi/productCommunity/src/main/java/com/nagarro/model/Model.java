package com.nagarro.model;

public class Model {
public long countUsers;
public long countComments;
public long countPosts;

public Model() {
	super();
	
}

public Model(long countUsers, long countComments, long countPosts) {
	super();
	this.countUsers = countUsers;
	this.countComments = countComments;
	this.countPosts = countPosts;
}


}

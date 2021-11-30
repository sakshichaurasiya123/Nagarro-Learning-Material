package com.nagarro.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comments {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int commentId;

@ManyToOne
@JoinColumn
@JsonIgnore
private Product product;
private String comment;
private String date;
private String userName;
private String heading;
public Comments() {
	super();
}
public Comments(int commentId, Product product, String comment, String date,
		String userName, String heading) {
	super();
	this.commentId = commentId;
	this.product = product;
	this.comment = comment;
	this.date = date;
	this.userName = userName;
	this.heading = heading;
}
public int getCommentId() {
	return commentId;
}
public void setCommentId(int commentId) {
	this.commentId = commentId;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}

public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getHeading() {
	return heading;
}
public void setHeading(String heading) {
	this.heading = heading;
}


}

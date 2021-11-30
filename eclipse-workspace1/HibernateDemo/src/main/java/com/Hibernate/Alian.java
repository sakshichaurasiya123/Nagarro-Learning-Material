package com.Hibernate;

import javax.persistence.*;

@Entity
public class Alian {
	@Id
	private int aid;
	private String aname;
	private String acolor;
	
	public void setAid(int aid)
	{ this.aid=aid;}
	
	public void setAname(String aname) {
		this.aname=aname;
	}
	
	public void setAcolor(String acolor) {
		this.acolor=acolor;
	}
	
	public int getAid() {
		return aid;
	}
	
	public String getAname() {
		return aname;
	}
	
	public String getColor() {
		return acolor;
	}

	
}

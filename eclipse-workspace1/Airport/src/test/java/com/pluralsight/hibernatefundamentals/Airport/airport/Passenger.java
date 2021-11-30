package com.pluralsight.hibernatefundamentals.Airport.airport;
 import javax.persistence.*;
 import java.util.*;
@Entity
@Table (name="PASSENGER")
public class Passenger {
	
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn (name="AIRPORT_ID")
	private Airport airport;
	
	@OneToMany(mappedBy="passenger")
	private List<Ticket> tickets=new ArrayList<Ticket>();
	
	public Passenger(int id,String name) {
		this.name=name;
		this.id=id;
	}
	
	public Passenger() {}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAirport(Airport airport) {
		this.airport=airport;
	}
	
	public Airport getAirport() {
		return airport;
	}
	
	public List<Ticket> getTickets(){
		return Collections.unmodifiableList(tickets);
	}
	
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
}

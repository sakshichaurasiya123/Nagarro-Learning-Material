package com.pluralsight.hibernatefundamentals.Airport.airport;

import java.util.*;
import java.util.Collections;

import javax.persistence.*;
@Entity
@Table(name="AIRPORT")
@Access(AccessType.FIELD)
public class Airport {
 @Id
 @Column(name="ID")
 private int id;
 
 @Column(name="NAME")
 private String name;
 @OneToMany (mappedBy="airport")
 List<Passenger> passengers=new ArrayList<Passenger>();
 
 public Airport(int id,String name) {
	 this.id=id;
	 this.name=name;
 }
 
 public Airport() {}
 public void setId(int id) {
	 this.id=id;
 }
 
 public List<Passenger> getPassenger(){
	 return Collections.unmodifiableList(passengers);
 }
 
 public void addPassenger(Passenger passenger) {
	 passengers.add(passenger);
 }
 
 
 public void setName(String name) {
	 this.name=name;
 }
 
 public String getName() {
	 return this.name;
 }
 
 public int getId() {
	 return this.id;
 }
 
}

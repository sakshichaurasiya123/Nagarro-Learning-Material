package com.pluralsight.hibernatefundamentals;
import javax.persistence.*;

import com.pluralsight.hibernatefundamentals.Airport.airport.Airport;
import com.pluralsight.hibernatefundamentals.Airport.airport.Passenger;
import com.pluralsight.hibernatefundamentals.Airport.airport.Ticket;
public class Main {
	public static void main(String ag[]) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hibernatefundamentals.m02.ex01");
		EntityManager em=emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Airport airport=new Airport( 1,"Henri coanada");
		Passenger john=new Passenger(1,"john smith");
		john.setAirport(airport);
		
		Passenger mike=new Passenger(2,"Michael Johnson");
		mike.setAirport(airport);
		airport.addPassenger(john);
		airport.addPassenger(mike);
		
		
		Ticket ticket1=new Ticket (1,"AA1234");
		ticket1.setPassenger(john);
		
		Ticket ticket2=new Ticket(2,"BB1234");
		ticket2.setPassenger(mike);
		
		john.addTicket(ticket1);
		john.addTicket(ticket2);
		
		Ticket ticket3=new Ticket(3,"CC093");
		ticket3.setPassenger(mike);
		mike.addTicket(ticket3);
		
		em.persist(airport);
		em.persist(mike);
		em.persist(john);
		em.persist(ticket1);
		em.persist(ticket2);
		em.persist(ticket3);
		
		
		em.getTransaction().commit();
		emf.close();
	}

}

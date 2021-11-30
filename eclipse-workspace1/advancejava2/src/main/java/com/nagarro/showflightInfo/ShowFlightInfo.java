package com.nagarro.showflightInfo;

import com.nagarro.flightDetails.*;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ShowFlightInfo {

	/**
	 * 
	 * This method will print the desired output on the screen
	 * 
	 * @author saumyaawasthi
	 */

	public static void showDetails(List<FlightInfo> list) {

		System.out.println("\n \t\t\t **** Flight Information ****");
		System.out.println();
		System.out.println(
				"Flight_No | Dept_Loc | Arrival_Loc | Valid_Till | Fare | Class | Seat_Availability | Flight_Dur");

		for (FlightInfo fd : list) {

			System.out.print(" " + fd.getFlight_no());
			System.out.print("       |   " + fd.getDep_loc());
			System.out.print("       |   " + fd.getArr_loc());
			System.out.print("       |   " + fd.getValid_till());
			if (fd.getCLASS().equals("EB")) {

				double increment = fd.getFare() * 0.4;
				System.out.print("     |  " + (fd.getFare() + increment));
			}

			else {
				System.out.print("     | " + fd.getFare());
			}

			System.out.print("         |  " + fd.getCLASS());
			System.out.print("         |  " + fd.getSeat_availability() + "   | ");
			System.out.printf("%.2f ", fd.getFlight_dur());
			System.out.println();
		}

		if (list.isEmpty()) {

			System.out.println("Flight is not available for the provided input");
		}
	}

	@SuppressWarnings("unchecked")
	public static List<FlightInfo> getvalue(String dept_loc, String arrival_loc, String date, String CLASS,
			int choice) {

		Configuration config = new Configuration().configure().addAnnotatedClass(FlightInfo.class);
		SessionFactory sessionfactory = config.buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		List<FlightInfo> q = null;

		if (choice == 1) {

			q = session
					.createQuery("from FlightInfo as  f where f.dep_loc =:v1 "
							+ " AND f.arr_loc=:v2 AND f.valid_till>=:v3 AND f.CLASS=:v4 ORDER BY f.fare")
					.setParameter("v1", dept_loc).setParameter("v2", arrival_loc).setParameter("v3", date)
					.setParameter("v4", CLASS).list();

		}

		else if (choice == 2) {

			q = session
					.createQuery("from FlightInfo as  f where f.dep_loc =:v1 "
							+ " AND f.arr_loc=:v2 AND f.valid_till>=:v3 AND f.CLASS=:v4 ORDER BY f.fare , f.flight_dur")
					.setParameter("v1", dept_loc).setParameter("v2", arrival_loc).setParameter("v3", date)
					.setParameter("v4", CLASS).list();

		}

		session.getTransaction();
		session.close();
		if (CLASS.equals("EB")) {
			for (FlightInfo fq : q) {

				double increment = fq.getFare() * 0.4;
				fq.setFare(increment + fq.getFare());
			}
		}
		System.out.println(q);
		showDetails(q);
		return q;
	}

}

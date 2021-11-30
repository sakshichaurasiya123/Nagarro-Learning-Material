package com.nagarro.flightHandle;

import com.nagarro.readfile.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.flightDetails.*;

public class FlightHandler {

	static ArrayList<String> sublist;
	FlightInfo fi = new FlightInfo();
	static ArrayList<FlightInfo> flightlist = new ArrayList<FlightInfo>();
	
	/***
	 * 
	 * This method stores all flight details of CSV file into ArrayList according to
	 * 
	 * @author saumyaawasthi
	 */


	public static void searchFlights() {

		for (String i : ReadFile.file_name) {

			String filepath = ReadFile.getabsolutepath() + "/" + i;

			try {

				Scanner sc = new Scanner(new File(filepath));
				sc.nextLine();
				while (sc.hasNext()) {

					String line = sc.nextLine().toUpperCase().toString();

					if (!line.isEmpty()) {

						StringTokenizer token = new StringTokenizer(line, "|");
						sublist = new ArrayList<String>(line.length());

						while (token.hasMoreTokens()) {
							sublist.add(token.nextToken());
						}

						FlightInfo li = new FlightInfo(sublist.get(0), sublist.get(1), sublist.get(2), sublist.get(3),
								sublist.get(4), Double.parseDouble(sublist.get(5)), Double.parseDouble(sublist.get(6)),
								sublist.get(7), sublist.get(8));

						flightlist.add(li);
					}

				}

			}

			catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		save(flightlist);
	}
	
	/***
	 * 
	 * This method saves all flight details of CSV file to the database
	 * 
	 * @author saumyaawasthi
	 */


	public static void save(ArrayList<FlightInfo> flightInfo) {

		Configuration config = new Configuration().configure().addAnnotatedClass(FlightInfo.class);
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		for (FlightInfo fi : flightlist) {

			session.save(fi);
		}

		session.getTransaction();
		session.close();
		sessionFactory.close();

	}

}

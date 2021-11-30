package com.nagarro.advancejava2;
import com.nagarro.threads.*;
import java.io.IOException;
import java.util.*;
import com.nagarro.validDateFormat.*;
import com.nagarro.flightDetails.FlightInfo;
import com.nagarro.showflightInfo.*;
public class App {
	// public static ArrayList<String> files = new ArrayList<String>();
	public static void main(String[] args) throws SecurityException, IOException {
		Threads.startThread();
		char choice = 'Y';
		while (choice == 'Y') {
			//FlightHandler f = new flightHandler();
			Scanner sc = new Scanner(System.in);
			int code;
			String dept_loc;
			String arrival_loc;
			String date;
			String Class;
			do {
				System.out.println("Please enter Departure Location");
				dept_loc = sc.nextLine().toUpperCase();
				try {
					if (dept_loc.length() != 3) {
						throw new Exception("Departure location code should be of 3 Length\n");
					}
					else if (dept_loc.matches(".*\\d.*")) {

						throw new Exception("Departure location should not contain digits");
					}
					else
						break;
				}

				catch (Exception e) {
					System.out.println(e);
				}
			} while (true);
			do {
				System.out.println("Please enter Arrival Location");
				arrival_loc = sc.nextLine().toUpperCase();
				try {
					if (arrival_loc.length() != 3) {
						throw new Exception("Arrival location code should be of 3 Length\n");
					}
					else if (arrival_loc.matches(".*\\d.*")) {
						throw new Exception("Arrival location should not contain digits");
					}
					else
						break;

				}
				catch (Exception e) {
					System.out.println(e);
				}
			} while (true);
			do {
				System.out.println("Please enter Date in Format(dd-mm-yyyy)");
				date = sc.nextLine().toUpperCase();
				try {

					if (ValidDateFormat.isvalid(date)) {
						break;
					}
					else {
						throw new Exception("Please enter the date in the specified format (dd-mm-yyyy)");
					}
				}
				catch (Exception e) {
					System.out.println(e);
				}
			} while (true);

			do {
				System.out.println("Enter Flight Class :: 'E' for Economic ,  'EB' for Business");
				Class = sc.nextLine().toUpperCase();
				try {

					if (Class.equalsIgnoreCase("E") || Class.equalsIgnoreCase("B") || Class.equalsIgnoreCase("EB")) {
						break;
					}
					else {
						throw new Exception("Please enter the class either 'E or 'EB' ");
					}
				}
				catch (Exception e) {
					System.out.println(e);
				}
			} while (true);
			do {
				System.out.println(
						"Please provide the output preference: 1 for fare 2 for Both fare and flight duration");
				code = sc.nextInt();
				try {

					if (code == 1 || code == 2)
						break;
					else
						throw new Exception("Please enter either 1 or 2");
				}

				catch (Exception e) {
					System.out.println(e);
				}
			} while (true);
			do {
				ShowFlightInfo.getvalue(dept_loc, arrival_loc, date, Class, code);
				System.out.println("Would you like to search more flights , enter y/n: ");
				choice = sc.next().charAt(0);

				try {

					if (choice == 'y' || choice == 'Y') {
						choice = 'Y';
						break;
					}

					else if (choice == 'n' || choice == 'N') {
						choice = 'N';
						System.out.println("***************Thankyou**************************");
						System.out.println("*************************************************");
						System.exit(0);
						break;
					}
					else {
						throw new Exception("Please enter either Y/N");
					}
				}
				catch (Exception e) {
					
					System.out.println(e);
				}
			} while (true);
		}
		Threads.stopThread();
	}
}

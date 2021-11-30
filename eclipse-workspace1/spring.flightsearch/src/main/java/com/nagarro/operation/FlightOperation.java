package com.nagarro.operation;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.nagarro.model.FlightModel;
import com.nagarro.readfiles.FileReadUsingThread;
import com.nagarro.service.FlightService;

/*
* Description:- Use a FlightOperation Class to perform a operation on flight and save a csv data in a database.
*/
public class FlightOperation {

	public static String formatDate(Date date) {
		String changedDate;
		DecimalFormat dayFormat = new DecimalFormat("00");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		changedDate = dayFormat.format(cal.get(Calendar.DATE)) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.YEAR);
		return changedDate;
	}

	static ArrayList<String> arr;
	FlightModel fm = new FlightModel();

	public static void addFlight(FlightService flightService) {
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		List<FlightModel> flightList = new ArrayList<FlightModel>();
		for (String s : FileReadUsingThread.filename) {
			String filePath = FileReadUsingThread.get_absolute_path() + "/" + s;

			Scanner sc;
			try {
				sc = new Scanner(new File(filePath));

				sc.nextLine();
				while (sc.hasNext()) {
					String line = sc.nextLine().toUpperCase().toString();
					if (!line.isEmpty()) {
						StringTokenizer token = new StringTokenizer(line, "|");
						arr = new ArrayList<String>(line.length());
						while (token.hasMoreTokens()) {
							arr.add(token.nextToken());
						}

						FlightModel model = new FlightModel();
						try {
							model.setFlightNo(arr.get(0));
							model.setDepLoc(arr.get(1));
							model.setArrLoc(arr.get(2));
							model.setValidTill(date.parse(arr.get(3)));
							model.setFlightTime(arr.get(4));
							model.setFlightDur(Double.parseDouble(arr.get(5)));
							model.setFare(Double.parseDouble(arr.get(6)));
							model.setSeatAvailability(arr.get(7));
							model.setCLASS(arr.get(8));

						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("exception operation");
							e.printStackTrace();
						}

						flightList.add(model);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (FlightModel flights : flightList) {
			System.out.println(flights);
			flightService.createFlight(flights);



		}

		
	}
}

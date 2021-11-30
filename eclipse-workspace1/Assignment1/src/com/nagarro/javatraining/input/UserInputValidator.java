package com.nagarro.javatraining.input;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

import com.nagarro.javatraining.controller.Utility;
import com.nagarro.javatraining.model.Constants;
import com.nagarro.javatraining.model.Flight;

public class UserInputValidator implements Constants {
	public static Date validateDate(String str)
	{
		dateformat.setLenient(false);
		Date validTill = null;
		try {
			validTill =  dateformat.parse(str);
		} catch (ParseException e) {
			System.err.print("Date not in appropriate(dd-MM-yyyy) format, Enter Again: ");
		}
		return validTill ;
	}
	public static String validateSource(String src) {
		synchronized(Utility.flightsInfo) 
		{
			for (HashSet<Flight> flightSet : Utility.flightsInfo.values())
			{
				for (Flight f : flightSet) {
					if (f.getDepLoc().equalsIgnoreCase(src))
						return f.getDepLoc() ;
				}
			}
		}
		System.err.print("Flights from no such stations found, Kindly Enter Again: ");
		return null;
	}

	public static String validateDestination(String destination) {
		synchronized(Utility.flightsInfo) 
		{
			for (HashSet<Flight> flightSet : Utility.flightsInfo.values())
			{
				for (Flight f : flightSet) {
					if (f.getArrLoc().equalsIgnoreCase(destination))
						return f.getArrLoc() ;
				}
			}
		}
		System.err.print("Flights to no such stations found, Kindly Enter Again: ");
		return null;
	}

	public static String validateFlightClass(String str) {
		if (str.equalsIgnoreCase("E")||str.equalsIgnoreCase("EB"))
			return str.toUpperCase() ;
		else
		{
			System.err.print("Flight Class entered Inappropriately, Enter Again :");
			return null;
		}
	}

	public static int validateOutputPreference(int i) {
		if((i==1)||(i==2))
			return i ;
		else
		{
			System.err.print("Output preference entered Inappropriately, Enter Again : ");
			return 0;
		}
	}

}
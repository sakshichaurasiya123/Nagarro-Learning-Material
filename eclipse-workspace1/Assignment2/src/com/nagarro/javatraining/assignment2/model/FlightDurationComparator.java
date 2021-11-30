package com.nagarro.javatraining.assignment2.model;

import java.util.Comparator;

public class FlightDurationComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight a, Flight b) {
		Double x  = a.getFlightDuration()-b.getFlightDuration() ;
		if (x<0)
		return -1;
		else if (x>0)
			return 1 ;
		else
			return 0;
			
	}

}

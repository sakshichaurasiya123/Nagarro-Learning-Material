/*


* Description:-  Use a FlightService Class to provide service to Flightdao class .
*/

package com.nagarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.FlightDao;
import com.nagarro.model.FlightModel;

@Service
public class FlightService {

	@Autowired
	private FlightDao flightDao;

	public void createFlight(FlightModel flight) {

		this.flightDao.createFlight(flight);
	}

	public void deleteFlight() {

		this.flightDao.deleteFlightProduct();
	}

	public FlightDao getFlightDao() {
		return flightDao;
	}

	public void setFlightDao(FlightDao flightDao) {
		this.flightDao = flightDao;
	}

}

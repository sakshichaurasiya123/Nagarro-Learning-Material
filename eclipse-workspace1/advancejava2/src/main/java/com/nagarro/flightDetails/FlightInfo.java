package com.nagarro.flightDetails;

import javax.persistence.*;
@Entity
public class FlightInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String flight_no;
	private String dep_loc;
	private String arr_loc;
	private String valid_till;
	private String flight_time;
	private Double flight_dur;
	private Double fare;
	private String seat_availability;
	private String CLASS;

	public FlightInfo() {

	}

	public FlightInfo(String flight_no, String dep_loc, String arr_loc, String valid_till, String flight_time,
			Double flight_dur, Double fare, String seat_availability, String cLASS) {

		this.flight_no = flight_no;
		this.dep_loc = dep_loc;
		this.arr_loc = arr_loc;
		this.valid_till = valid_till;
		this.flight_time = flight_time;
		this.flight_dur = flight_dur;
		this.fare = fare;
		this.seat_availability = seat_availability;
		CLASS = cLASS;
	}

	public int getId() {
		return id;
	}

	public String getFlight_no() {
		return flight_no;
	}

	public String getDep_loc() {
		return dep_loc;
	}

	public String getArr_loc() {
		return arr_loc;
	}

	public String getValid_till() {
		return valid_till;
	}

	public String getFlight_time() {
		return flight_time;
	}

	public Double getFlight_dur() {
		return flight_dur;
	}

	public Double getFare() {
		return fare;
	}

	public String getSeat_availability() {
		return seat_availability;
	}

	public String getCLASS() {
		return CLASS;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFlight_no(String flight_no) {
		this.flight_no = flight_no;
	}

	public void setDep_loc(String dep_loc) {
		this.dep_loc = dep_loc;
	}

	public void setArr_loc(String arr_loc) {
		this.arr_loc = arr_loc;
	}

	public void setValid_till(String valid_till) {
		this.valid_till = valid_till;
	}

	public void setFlight_time(String flight_time) {
		this.flight_time = flight_time;
	}

	public void setFlight_dur(Double flight_dur) {
		this.flight_dur = flight_dur;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public void setSeat_availability(String seat_availability) {
		this.seat_availability = seat_availability;
	}

	public void setCLASS(String cLASS) {
		CLASS = cLASS;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ReadInfo[flight_no=" + flight_no + ", dep_loc= " + dep_loc + ", arr_loc= " + arr_loc + ", valid_till"
				+ valid_till + ", flight_time" + flight_time + ", flight_dur" + flight_dur + ", Fare=" + fare
				+ ", seat_availability" + seat_availability + ", CLASS=" + CLASS + "]";
	}

}

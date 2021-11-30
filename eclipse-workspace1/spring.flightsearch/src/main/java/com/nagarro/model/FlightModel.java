/*

* Description:-  make a FlightModel Class as a Flight Entity.
*/


package com.nagarro.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(FlightId.class)
public class FlightModel {


	
   @Id
	private String flightNo;
	private String depLoc;
	private String arrLoc;
	private Date validTill;
	private String flightTime;
	@Id
	private double flightDur;
	private double fare;
	private String seatAvailability;
	private String CLASS;

	

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDepLoc() {
		return depLoc;
	}

	public void setDepLoc(String depLoc) {
		this.depLoc = depLoc;
	}

	public String getArrLoc() {
		return arrLoc;
	}

	public void setArrLoc(String arrLoc) {
		this.arrLoc = arrLoc;
	}

	

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public double getFlightDur() {
		return flightDur;
	}

	public void setFlightDur(double flightDur) {
		this.flightDur = flightDur;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public String getSeatAvailability() {
		return seatAvailability;
	}

	public void setSeatAvailability(String seatAvailability) {
		this.seatAvailability = seatAvailability;
	}

	public String getCLASS() {
		return CLASS;
	}

	public void setCLASS(String cLASS) {
		CLASS = cLASS;
	}

	

	@Override
	public String toString() {
		return "FlightModel [flightNo=" + flightNo + ", depLoc=" + depLoc + ", arrLoc=" + arrLoc
				+ ", validTill=" + validTill + ", flightTime=" + flightTime + ", flightDur=" + flightDur + ", fare="
				+ fare + ", seatAvailability=" + seatAvailability + ", CLASS=" + CLASS + "]";
	}

}

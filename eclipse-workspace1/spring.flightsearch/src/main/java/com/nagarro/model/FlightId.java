/*

* Description:-  make a FlightId Class to make a primary key as a combination of flightNo and flightdur 
*/

package com.nagarro.model;

import java.io.Serializable;

public class FlightId implements Serializable {

private static final long serialVersionUID = 1L;
    
    private String flightNo;
    private double flightDur;
    
    public FlightId() {
        
    }

	public FlightId(String flightNo, double flightDur) {
		super();
		this.flightNo = flightNo;
		this.flightDur = flightDur;
	}
    

 

   
}

package com.company.Business.AeroTaxiCompany;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.User.Flight;

import java.time.LocalDate;

public class CompanyFlight extends Flight {
    //private static int companyFlightCounter=0;
    //private int companyFlightId;
    private int flightPassengers;

    /*public CompanyFlight(){
        super();
        this.companyFlightCounter++;
        this.companyFlightId=this.companyFlightCounter;
        this.flightPassengers=0;
    }*/

    public CompanyFlight(String flightOrigin, String flightDestiny, Plane flightCategory, LocalDate flightDate, int flightPassengers){
        super(flightOrigin, flightDestiny, flightCategory, flightDate);
        //this.companyFlightCounter++;
        // this.companyFlightId=this.companyFlightCounter;
        this.flightPassengers=0;
    }

    public int getFlightPassengers() {
        return flightPassengers;
    }

    public void addFlightPassengers(int flightPassengers) {
        this.flightPassengers = this.flightPassengers+flightPassengers;
    }
}

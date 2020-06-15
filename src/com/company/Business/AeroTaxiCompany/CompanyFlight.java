package com.company.Business.AeroTaxiCompany;

import com.company.Business.User.Flight;

public class CompanyFlight extends Flight {
    private static int companyFlightCounter=0;
    private int companyFlightId;
    private int flightPassengers;

    public CompanyFlight(){
        super();
        this.companyFlightCounter++;
        this.companyFlightId=this.companyFlightCounter;
        this.flightPassengers=0;
    }

    public int getFlightPassengers() {
        return flightPassengers;
    }

    public void setFlightPassengers(int flightPassengers) {
        this.flightPassengers = flightPassengers;
    }
}

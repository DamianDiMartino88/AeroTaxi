package com.company.Business.AeroTaxiCompany;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.User.Flight;

import java.time.LocalDate;

public class CompanyFlight extends Flight {
    private int flightPassengers;

    public CompanyFlight(){
        super();
        this.flightPassengers=0;
    }


    public CompanyFlight(String flightOrigin, String flightDestiny, Plane flightCategory, String flightDate, int flightPassengers){
        super(flightOrigin, flightDestiny, flightCategory, flightDate);
        this.flightPassengers=0;
    }

    public int getFlightPassengers() {
        return flightPassengers;
    }

    //Agrega los pasajeros al vuelo
    public void addFlightPassengers(int flightPassengers) {
        this.flightPassengers = this.flightPassengers+flightPassengers;
    }

    //Sobreescritura ToString para mostrar los datos del CompanyFLight
    @Override
    public String toString() {
        return "Origin: '" + getFlightOrigin() + '\'' +
                ", Destiny: '" + getFlightDestiny()+ '\'' +
                ", Category: " + getFlightCategory() +
                ", Date: " + getFlightDate() +
                ", Passengers: " + flightPassengers;
    }
}

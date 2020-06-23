package com.company.Business.User;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.City;

import java.time.LocalDate;

public class UserFlight extends Flight {
    private static int userFlightCounter=0;
    private int userFlightId;
    private int flightCompanions;
    private double flightCost;

    public UserFlight(){
        super();
        this.flightCompanions=0;
        this.flightCost=0;
    }

    public UserFlight(String flightOrigin, String flightDestiny, Plane flightCategory, String flightDate, int flightCompanions){
        super(flightOrigin, flightDestiny, flightCategory, flightDate);
        this.flightCompanions=flightCompanions;
    }

    public int getFlightCompanions() {
        return flightCompanions;
    }

    public void setFlightCompanions(int flightCompanions) {
        this.flightCompanions = flightCompanions;
    }

    public double getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(double flightCost) {
        this.flightCost = flightCost;
    }

    //Sobreescritura Metodo ToString para mostrar los datos de UserFlight
    @Override
    public String toString() {
        return "Origin:'" + getFlightOrigin() + '\'' +
                ", Destiny:'" + getFlightDestiny()+ '\'' +
                ", Category:" + getFlightCategory() +
                ", Date:" + getFlightDate() +
                ", Companions:" + flightCompanions +
                ", Cost:" + flightCost;
    }
}

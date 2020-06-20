package com.company.Business.User;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.City;

import java.time.LocalDate;

public class UserFlight extends Flight {
    private static int userFlightCounter=0;
    private int userFlightId;
    private int flightCompanions;
    private double flightCost;

    /*public UserFlight(){
        super();
        this.userFlightCounter++;
        this.userFlightId=this.userFlightCounter;
        this.flightCompanions=0;
        this.flightCost=0;
    }*/

    public UserFlight(String flightOrigin, String flightDestiny, Plane flightCategory, LocalDate flightDate, int flightCompanions){
        super(flightOrigin, flightDestiny, flightCategory, flightDate);
        //this.userFlightCounter++;
        //this.userFlightId=this.userFlightCounter;
        this.flightCompanions=flightCompanions;
    }

    public int getFlightCompanions() {
        return flightCompanions;
    }

    public void setFlightCompanions(int flightCompanions) {
        this.flightCompanions = flightCompanions;
    }

    public static int getUserFlightCounter() {
        return userFlightCounter;
    }

    public static void setUserFlightCounter(int userFlightCounter) {
        UserFlight.userFlightCounter = userFlightCounter;
    }

    public int getUserFlightId() {
        return userFlightId;
    }

    public void setUserFlightId(int userFlightId) {
        this.userFlightId = userFlightId;
    }

    public double getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(double flightCost) {
        this.flightCost = flightCost;
    }

    @Override
    public String toString() {
        return "UserFlight{" +
                "flightOrigin='" + getFlightOrigin() + '\'' +
                ", flightDestiny='" + getFlightDestiny()+ '\'' +
                ", flightCategory=" + getFlightCategory() +
                ", flightDate=" + getFlightDate() +
                ", flightCompanions=" + flightCompanions +
                ", flightCost=" + flightCost +
                '}';
    }
}

package com.company.Business.User;

public class UserFlight extends Flight {
    private static int userFlightCounter=0;
    private int userFlightId;
    private int flightCompanions;
    private double flightCost;

    public UserFlight(){
        super();
        this.userFlightCounter++;
        this.userFlightId=this.userFlightCounter;
        this.flightCompanions=0;
        this.flightCost=0;
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
}

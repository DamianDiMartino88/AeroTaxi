package com.company.Business.User;

public class UserFlight extends Flight {
    private static int userFlightCounter=0;
    private int userFlightId;
    private int flightCompanions;

    public UserFlight(){
        super();
        this.userFlightCounter++;
        this.userFlightId=this.userFlightCounter;
        this.flightCompanions=0;
    }

    public int getFlightCompanions() {
        return flightCompanions;
    }

    public void setFlightCompanions(int flightCompanions) {
        this.flightCompanions = flightCompanions;
    }
}

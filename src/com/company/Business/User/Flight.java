package com.company.Business.User;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.AeroTaxiCompany.Plane.PropulsionType;
import com.company.Business.City;

import java.util.Date;
import java.util.HashSet;

public class Flight {
    private static int flightCounter=0;
    private int flightId;
    private String flightOrigin;
    private String flightDestiny;
    private int flightCompanions;
    private Plane flightCategory;
    private Date flightDate;

    public static int getFlightCounter() {
        return flightCounter;
    }

    public static void setFlightCounter(int flightCounter) {
        Flight.flightCounter = flightCounter;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFlightOrigin() {
        return flightOrigin;
    }

    public void setFlightOrigin(String flightOrigin) {
        this.flightOrigin = flightOrigin;
    }

    public String getFlightDestiny() {
        return flightDestiny;
    }

    public void setFlightDestiny(String flightDestiny) {
        this.flightDestiny = flightDestiny;
    }

    public int getFlightCompanions() {
        return flightCompanions;
    }

    public void setFlightCompanions(int flightCompanions) {
        this.flightCompanions = flightCompanions;
    }

    public Plane getFlightCategory() {
        return flightCategory;
    }

    public void setFlightCategory(Plane flightCategory) {
        this.flightCategory = flightCategory;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }
}

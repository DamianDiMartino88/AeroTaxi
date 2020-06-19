package com.company.Business.User;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.AeroTaxiCompany.Plane.PropulsionType;
import com.company.Business.City;

import java.util.Date;
import java.util.HashSet;

public class Flight {
    private String flightOrigin;
    private String flightDestiny;
    private Plane flightCategory;
    private Date flightDate;
    private double flightCost;

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

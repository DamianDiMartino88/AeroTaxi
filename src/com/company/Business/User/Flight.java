package com.company.Business.User;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.AeroTaxiCompany.Plane.PropulsionType;
import com.company.Business.City;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

public class Flight {
    private String flightOrigin;
    private String flightDestiny;
    private Plane flightCategory;
    private LocalDate flightDate;

    public Flight (){
        this.flightOrigin="";
        this.flightDestiny="";
        this.flightCategory=null;
        this.flightDate=null;
    }

    public Flight (String flightOrigin, String flightDestiny, Plane flightCategory, LocalDate flightDate){
        this.flightOrigin = flightOrigin;
        this.flightDestiny = flightDestiny;
        this.flightCategory = flightCategory;
        this.flightDate = flightDate;
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

    public Plane getFlightCategory() {
        return flightCategory;
    }

    public void setFlightCategory(Plane flightCategory) {
        this.flightCategory = flightCategory;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightOrigin='" + flightOrigin + '\'' +
                ", flightDestiny='" + flightDestiny + '\'' +
                ", flightCategory=" + flightCategory +
                ", flightDate=" + flightDate +
                '}';
    }
}

package com.company.Business.User;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.City;

import java.util.Date;
import java.util.HashSet;

public class Flight {
    private static int flightCounter=0;
    private int flightId;
    private HashSet<City> flightOrigin;
    private HashSet<City> flightDestiny;
    private int flightCompanions;
    private Plane flightCategory;
    private Date flightDate;

}

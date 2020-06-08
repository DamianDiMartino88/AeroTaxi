package com.company.Business.AeroTaxiCompany.Plane;

public class BronzePlane extends Plane {

    public BronzePlane(){
        super();
    }

    public BronzePlane(int fuelcapacity, double costperkm, int passengercapacity, double speed, PropulsionType propulsiontype){
        super(fuelcapacity, costperkm, passengercapacity,  speed, propulsiontype);
    }
}

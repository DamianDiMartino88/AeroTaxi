package com.company.Business.AeroTaxiCompany.Plane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Plane {
    private int fuelCapacity;
    private double costPerKM;
    private int passengerCapacity;
    private double speed;
    private PropulsionType propulsionType;
    List<Plane> planesList;

    public static HashSet<Plane>  addPlanes()
    {
        HashSet<Plane> planesList = new HashSet<>();
        planesList.add(new GoldPlane(PropulsionType.PropellerEngine));
        planesList.add(new GoldPlane(PropulsionType.PistonsEngine));
        planesList.add(new GoldPlane(PropulsionType.ReactionEngine));
        planesList.add(new SilverPlane(PropulsionType.PropellerEngine));
        planesList.add(new SilverPlane(PropulsionType.PistonsEngine));
        planesList.add(new SilverPlane(PropulsionType.ReactionEngine));
        planesList.add(new BronzePlane(PropulsionType.PropellerEngine));
        planesList.add(new BronzePlane(PropulsionType.PistonsEngine));
        planesList.add(new BronzePlane(PropulsionType.ReactionEngine));
        return planesList;
    }


    public Plane(){
        this.fuelCapacity=0;
        this.costPerKM=0;
        this.passengerCapacity=0;
        this.speed=0;
        this.propulsionType=null;
    }

    public Plane(int fuelcapacity, double costperkm, int passengercapacity, double speed, PropulsionType propulsiontype){
        this.fuelCapacity=fuelcapacity;
        this.costPerKM=costperkm;
        this.passengerCapacity=passengercapacity;
        this.speed=speed;
        this.propulsionType=propulsiontype;
    }



    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public double getCostPerKM() {
        return costPerKM;
    }

    public void setCostPerKM(double costPerKM) {
        this.costPerKM = costPerKM;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public PropulsionType getPropulsionType() {
        return propulsionType;
    }

    public void setPropulsionType(PropulsionType propulsionType) {
        this.propulsionType = propulsionType;
    }
}

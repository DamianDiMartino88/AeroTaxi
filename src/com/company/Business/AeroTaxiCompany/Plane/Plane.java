package com.company.Business.AeroTaxiCompany.Plane;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*Annotations para indicarle a Jackson las subclases q va a implementar al guardar listas de tipo "Plane"
las cuales van a tener dentro Objetos de tipo GoldPlane, SilverPlane, y BronzePlane*/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GoldPlane.class),
        @JsonSubTypes.Type(value = SilverPlane.class),
        @JsonSubTypes.Type(value = BronzePlane.class),
})
public class Plane {
    private int fuelCapacity;
    private double costPerKM;
    private int passengerCapacity;
    private double speed;
    private PropulsionType propulsionType;

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

    public double getCostPerKM() {
        return costPerKM;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public double getSpeed() {
        return speed;
    }

    public PropulsionType getPropulsionType() {
        return propulsionType;
    }

 //Sobreescritura ToString para mostrar los datos del Plane
    @Override
    public String toString() {
        return "Plane{" +
                "propulsionType=" + propulsionType +
                '}';
    }
}

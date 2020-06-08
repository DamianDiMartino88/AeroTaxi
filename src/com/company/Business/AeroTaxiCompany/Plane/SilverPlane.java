package com.company.Business.AeroTaxiCompany.Plane;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.AeroTaxiCompany.Plane.PropulsionType;

public class SilverPlane extends Plane implements ICatering {

    public SilverPlane(){
        super();
    }

    public SilverPlane(int fuelcapacity, double costperkm, int passengercapacity, double speed, PropulsionType propulsiontype){
        super(fuelcapacity, costperkm, passengercapacity,  speed, propulsiontype);
    }
    //metodo heredado de ICatering, devuelve " true " para los aviones tipo SILVER
    public boolean availableServices(){
        return this.hasCatering;
    }
}

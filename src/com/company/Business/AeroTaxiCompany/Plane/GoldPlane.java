package com.company.Business.AeroTaxiCompany.Plane;



public class GoldPlane extends Plane implements ICatering {
    private boolean hasWifi;
    public GoldPlane(){
        super();
        this.hasWifi=false;
    }

    public GoldPlane(int fuelcapacity, double costperkm, int passengercapacity, double speed, PropulsionType propulsiontype, boolean haswifi){
        super(fuelcapacity, costperkm, passengercapacity,  speed, propulsiontype);
        this.hasWifi=haswifi;
    }

    //metodo heredado de ICatering, devuelve " true " para los aviones tipo GOLD
    public boolean availableServices(){
        return this.hasCatering;
    }
}

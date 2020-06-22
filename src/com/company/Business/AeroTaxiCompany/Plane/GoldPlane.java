package com.company.Business.AeroTaxiCompany.Plane;



public class GoldPlane extends Plane implements ICatering {
    private boolean hasWifi;
    public GoldPlane(){
        super();
        this.hasWifi=false;
    }

    //se asignan los valores de capacidad de combustible, costo por kilometro, y velocidad
    //dependiendo del tipo de propulsion del avion
    public GoldPlane( PropulsionType propulsiontype){
        super( (propulsiontype.equals(PropulsionType.PROPELLERENGINE)?3000:(propulsiontype.equals(PropulsionType.PISTONSENGINE)? 10000 : 150000)),
                (propulsiontype.equals(PropulsionType.PROPELLERENGINE)?150:(propulsiontype.equals(PropulsionType.PISTONSENGINE)? 225 : 300))
                , 10,
                (propulsiontype.equals(PropulsionType.PROPELLERENGINE)?880:(propulsiontype.equals(PropulsionType.PISTONSENGINE)? 855 : 2180)),
                propulsiontype);
        this.hasWifi=(propulsiontype.equals(PropulsionType.PROPELLERENGINE)?false:(propulsiontype.equals(PropulsionType.PISTONSENGINE)? false : true));
    }

    //metodo heredado de ICatering, devuelve " true " para los aviones tipo GOLD
    public boolean availableServices(){
        return this.hasCatering;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof GoldPlane )) return false;
        GoldPlane glp = (GoldPlane)obj;
        return glp.hasWifi == hasWifi
                && glp.getCostPerKM() == getCostPerKM()
                && glp.getFuelCapacity() == getFuelCapacity()
                && glp.getPassengerCapacity() == getPassengerCapacity()
                && glp.getPropulsionType().equals(getPropulsionType())
                && glp.getSpeed() == getSpeed();
    }

    @Override
    public String toString() {
        return  "Gold Plane, Propulsion Type: "+getPropulsionType().getDenomination()+
                ", Capacity: "+getPassengerCapacity()+" Passengers, Fuel Capacity: "+getFuelCapacity()+
                ", Speed: "+getSpeed()+ ", Cost Per KM: "+getCostPerKM()+", Catering: "+hasCatering+", WIFI: "+hasWifi;
    }
}

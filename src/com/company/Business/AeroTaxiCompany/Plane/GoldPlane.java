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
        super( (propulsiontype.equals(PropulsionType.PropellerEngine)?3000:(propulsiontype.equals(PropulsionType.PistonsEngine)? 10000 : 150000)),
                (propulsiontype.equals(PropulsionType.PropellerEngine)?150:(propulsiontype.equals(PropulsionType.PistonsEngine)? 225 : 300))
                , 10,
                (propulsiontype.equals(PropulsionType.PropellerEngine)?880:(propulsiontype.equals(PropulsionType.PistonsEngine)? 855 : 2180)),
                propulsiontype);
        this.hasWifi=(propulsiontype.equals(PropulsionType.PropellerEngine)?false:(propulsiontype.equals(PropulsionType.PistonsEngine)? false : true));
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
                && glp.getPropulsionType() == getPropulsionType()
                && glp.getSpeed() == getSpeed();
    }
}

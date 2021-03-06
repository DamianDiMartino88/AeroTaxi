package com.company.Business.AeroTaxiCompany.Plane;

public class BronzePlane extends Plane {

    public BronzePlane(){
        super();
    }

    //se asignan los valores de capacidad de combustible, costo por kilometro, y velocidad
    //dependiendo del tipo de propulsion del avion
    public BronzePlane( PropulsionType propulsiontype){
        super( (propulsiontype.equals(PropulsionType.PROPELLERENGINE)?3000:(propulsiontype.equals(PropulsionType.PISTONSENGINE)? 10000 : 150000)),
                (propulsiontype.equals(PropulsionType.PROPELLERENGINE)?150:(propulsiontype.equals(PropulsionType.PISTONSENGINE)? 225 : 300))
                , (propulsiontype.equals(PropulsionType.PROPELLERENGINE)?7:(propulsiontype.equals(PropulsionType.PISTONSENGINE)? 15 : 25)),
                (propulsiontype.equals(PropulsionType.PROPELLERENGINE)?880:(propulsiontype.equals(PropulsionType.PISTONSENGINE)? 855 : 2180)),
                propulsiontype);
    }

    //Sobreescritura metodo Equals para comparar categoria de aviones
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof BronzePlane )) return false;
        BronzePlane bzp = (BronzePlane)obj;
        return  bzp.getCostPerKM() == getCostPerKM()
                && bzp.getFuelCapacity() == getFuelCapacity()
                && bzp.getPassengerCapacity() == getPassengerCapacity()
                && bzp.getPropulsionType() == getPropulsionType()
                && bzp.getSpeed() == getSpeed();
    }

    //Imprime los datos de la categoria Bronze
    @Override
    public String toString() {
        return  "Bronze Plane, Propulsion Type: "+getPropulsionType().getDenomination()+
                ", Capacity: "+getPassengerCapacity()+" Passengers, Fuel Capacity: "+getFuelCapacity()+
                ", Speed: "+getSpeed()+ ", Cost Per KM: "+getCostPerKM();
    }
}

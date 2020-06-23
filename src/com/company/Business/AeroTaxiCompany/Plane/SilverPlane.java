package com.company.Business.AeroTaxiCompany.Plane;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.AeroTaxiCompany.Plane.PropulsionType;

public class SilverPlane extends Plane implements ICatering {

    public SilverPlane(){
        super();
    }

    //se asignan los valores de capacidad de combustible, costo por kilometro, y velocidad
    //dependiendo del tipo de propulsion del avion
    public SilverPlane( PropulsionType propulsiontype){
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
        if(!(obj instanceof SilverPlane )) return false;
        SilverPlane slvp = (SilverPlane)obj;
        return  slvp.getCostPerKM() == getCostPerKM()
                && slvp.getFuelCapacity() == getFuelCapacity()
                && slvp.getPassengerCapacity() == getPassengerCapacity()
                && slvp.getPropulsionType() == getPropulsionType()
                && slvp.getSpeed() == getSpeed();
    }

    //metodo heredado de ICatering, devuelve " true " para los aviones tipo SILVER
    public boolean availableServices(){
        return this.hasCatering;
    }

    //Imprime los datos de la categoria Bronze
    @Override
    public String toString() {
        return  "Silver Plane, Propulsion Type: "+getPropulsionType().getDenomination()+
                " Capacity: "+getPassengerCapacity()+" Passengers, Fuel Capacity: "+getFuelCapacity()+
                " Speed: "+getSpeed()+ ", Cost Per KM: "+getCostPerKM()+",Catering: "+hasCatering;
    }
}

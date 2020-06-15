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
        super( (propulsiontype.equals(PropulsionType.PropellerEngine)?3000:(propulsiontype.equals(PropulsionType.PistonsEngine)? 10000 : 150000)),
                (propulsiontype.equals(PropulsionType.PropellerEngine)?150:(propulsiontype.equals(PropulsionType.PistonsEngine)? 225 : 300))
                , 10,
                (propulsiontype.equals(PropulsionType.PropellerEngine)?880:(propulsiontype.equals(PropulsionType.PistonsEngine)? 855 : 2180)),
                propulsiontype);
    }

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
}

package com.company.Business.AeroTaxiCompany.Plane;

public class BronzePlane extends Plane {

    public BronzePlane(){
        super();
    }

    //se asignan los valores de capacidad de combustible, costo por kilometro, y velocidad
    //dependiendo del tipo de propulsion del avion
    public BronzePlane( PropulsionType propulsiontype){
        super( (propulsiontype.equals(PropulsionType.PropellerEngine)?3000:(propulsiontype.equals(PropulsionType.PistonsEngine)? 10000 : 150000)),
                (propulsiontype.equals(PropulsionType.PropellerEngine)?150:(propulsiontype.equals(PropulsionType.PistonsEngine)? 225 : 300))
                , 20,
                (propulsiontype.equals(PropulsionType.PropellerEngine)?880:(propulsiontype.equals(PropulsionType.PistonsEngine)? 855 : 2180)),
                propulsiontype);
    }
}

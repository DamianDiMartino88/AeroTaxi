package com.company.DataAccess;

import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.BronzePlane;
import com.company.Business.AeroTaxiCompany.Plane.GoldPlane;
import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.AeroTaxiCompany.Plane.SilverPlane;
import com.company.Business.User.UserFlight;

public class DataAccessValidation {
    //validaciones necesarias en cuanto a guardado de informacion, datos etc

    public boolean companionsValidation(int companions){
        boolean isNumeric = String.valueOf(companions).chars().allMatch(x -> Character.isDigit(x));
        return (companions>=0&&isNumeric)? true : false;
    }

    public boolean flightCapacity(int companions, CompanyFlight flight){
        return ((flight.getFlightPassengers()+(companions+1))<=flight.getFlightCategory().getPassengerCapacity())? true : false;
    }

    public boolean flightType(UserFlight flight, Plane plane){
/*
        if(flight.getFlightCategory() instanceof GoldPlane){
            flight.setFlightCategory((GoldPlane)flight.getFlightCategory());
        }
        System.out.println(flight.getFlightCategory() instanceof GoldPlane);
        if(flight.getFlightCategory() instanceof SilverPlane){
            flight.setFlightCategory((SilverPlane)flight.getFlightCategory());
        }
        System.out.println(flight.getFlightCategory() instanceof SilverPlane);
        if(flight.getFlightCategory() instanceof BronzePlane){
            flight.setFlightCategory((BronzePlane)flight.getFlightCategory());
        }
        System.out.println(flight.getFlightCategory() instanceof BronzePlane);
        if(plane instanceof GoldPlane){
            plane=(GoldPlane)plane;
        }
        if(plane instanceof SilverPlane){
            plane=(SilverPlane)plane;
        }
        if(plane instanceof BronzePlane){
            plane=(BronzePlane)plane;
        }*/

        return (flight.getFlightCategory().equals(plane));

    }

    public boolean flightRout(UserFlight userFlight, CompanyFlight companyFlight){
        return (userFlight.getFlightOrigin().equals(companyFlight.getFlightOrigin())&&
                userFlight.getFlightDestiny().equals(companyFlight.getFlightDestiny()));
    }

    public boolean flightRoutToCancel(UserFlight userFlightConfirmed, UserFlight userFlightToCancel){
        return (userFlightConfirmed.getFlightOrigin().equals(userFlightToCancel.getFlightOrigin())&&
                userFlightConfirmed.getFlightDestiny().equals(userFlightToCancel.getFlightDestiny()));
    }

    public boolean flightDateToCancel(UserFlight userFlightConfirmed, UserFlight userFlightToCancel){
        return (userFlightConfirmed.getFlightDate().equals(userFlightToCancel.getFlightDate()));
    }

}



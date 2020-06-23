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

    public boolean flightDate(UserFlight userFlightConfirmed, UserFlight userFlightToCancel){
        return (userFlightConfirmed.getFlightDate().equals(userFlightToCancel.getFlightDate()));
    }

    public boolean flightDate(CompanyFlight companyFlightConfirmed, UserFlight userFlightConfirmed){
        return (companyFlightConfirmed.getFlightDate().equals(userFlightConfirmed.getFlightDate()));
    }

}



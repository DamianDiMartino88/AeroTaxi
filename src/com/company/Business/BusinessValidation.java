package com.company.Business;

import com.company.Business.AeroTaxiCompany.Company;
import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.User.Flight;
import com.company.Business.User.UserFlight;

public class BusinessValidation {
    //validaciones necesarias en el paso de informacion entre las capas de datos  y de user interface,
    // como por ejemplo en esta capa se van a hacer las validaciones de disponibilidad o capacidad de aviones

    public boolean cityValidation(String origin, String destiny){
        return (origin.equals(destiny))? false : true;
    }

    public boolean companionsValidation(int companions){
        boolean isNumeric = String.valueOf(companions).chars().allMatch(x -> Character.isDigit(x));
        return (companions>=0&&isNumeric)? true : false;
    }

    public boolean flightCapacity(int companions, CompanyFlight flight){
       return ((flight.getFlightPassengers()+companions)<=flight.getFlightCategory().getPassengerCapacity())? true : false;
    }

    public boolean flightType(CompanyFlight flight, Plane plane){
        return (flight.getFlightCategory().equals(plane));
    }

    public boolean flightRout(UserFlight userFlight, CompanyFlight companyFlight){
        return (userFlight.getFlightOrigin().equals(companyFlight.getFlightOrigin())&&
                userFlight.getFlightDestiny().equals(companyFlight.getFlightDestiny()));
    }


}

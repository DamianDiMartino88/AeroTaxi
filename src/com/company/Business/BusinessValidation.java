package com.company.Business;

import com.company.Business.AeroTaxiCompany.Company;
import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.User.Flight;
import com.company.Business.User.UserFlight;

public class BusinessValidation {

    //Verifica que las ciudades de origen y destino no sean iguales
    public boolean cityValidation(String origin, String destiny){
        return (origin.equals(destiny))? false : true;
    }

    //Verifica q el campo companions sea mayor o igual a 0 y sea numerico
    public boolean companionsValidation(int companions){
        boolean isNumeric = String.valueOf(companions).chars().allMatch(x -> Character.isDigit(x));
        return (companions>=0&&isNumeric)? true : false;
    }

    /*Verifica que la cantidad de acompañantes mas el comprador,
    mas los pasajeros del vuelo confirmados no superen la capacidad del vuelo*/
    public boolean flightCapacity(int companions, CompanyFlight flight){
       return ((flight.getFlightPassengers()+(companions+1))<=flight.getFlightCategory().getPassengerCapacity())? true : false;
    }

    //Verifica que la ruta sea la misma comparando origenes y destinos
    public boolean flightRout(UserFlight userFlight, CompanyFlight companyFlight){
        return (userFlight.getFlightOrigin().equals(companyFlight.getFlightOrigin())&&
                userFlight.getFlightDestiny().equals(companyFlight.getFlightDestiny()));
    }
}

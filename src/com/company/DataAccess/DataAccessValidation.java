package com.company.DataAccess;

import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.BronzePlane;
import com.company.Business.AeroTaxiCompany.Plane.GoldPlane;
import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.AeroTaxiCompany.Plane.SilverPlane;
import com.company.Business.User.UserFlight;

public class DataAccessValidation {

    /*Verifica que la cantidad de acompañantes mas el comprador,
    mas los pasajeros del vuelo confirmados no superen la capacidad del vuelo*/
    public boolean flightCapacity(int companions, CompanyFlight flight){
        return ((flight.getFlightPassengers()+(companions+1))<=flight.getFlightCategory().getPassengerCapacity())? true : false;
    }

    //verifico q el tipo de avion sea el mismo
    public boolean flightType(UserFlight flight, Plane plane){
        return (flight.getFlightCategory().equals(plane));
    }

    //Verifica que la ruta sea la misma comparando origenes y destinos
    public boolean flightRout(UserFlight userFlight, CompanyFlight companyFlight){
        return (userFlight.getFlightOrigin().equals(companyFlight.getFlightOrigin())&&
                userFlight.getFlightDestiny().equals(companyFlight.getFlightDestiny()));
    }

    //sobreescritura q Verifica que la ruta sea la misma comparando origenes y destinos  para cancelar un vuelo de usuario
    public boolean flightRoutToCancel(UserFlight userFlightConfirmed, UserFlight userFlightToCancel){
        return (userFlightConfirmed.getFlightOrigin().equals(userFlightToCancel.getFlightOrigin())&&
                userFlightConfirmed.getFlightDestiny().equals(userFlightToCancel.getFlightDestiny()));
    }

    //sobreescritura que Verifica las fechas del vuelo para cancelar
    public boolean flightDate(UserFlight userFlightConfirmed, UserFlight userFlightToCancel){
        return (userFlightConfirmed.getFlightDate().equals(userFlightToCancel.getFlightDate()));
    }

    //sobreescritura que Verifica las fechas del de UserFlight y CompanyFlight para confirmar q coincidan
    public boolean flightDate(CompanyFlight companyFlightConfirmed, UserFlight userFlightConfirmed){
        return (companyFlightConfirmed.getFlightDate().equals(userFlightConfirmed.getFlightDate()));
    }

}



package com.company.Business.Services;

import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.*;
import com.company.Business.BusinessValidation;
import com.company.Business.City;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.company.Business.User.UserFlight;
import com.company.DataAccess.Services.DataAccessService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.IOException;
import java.util.*;

public class BusinessService {
    BusinessValidation validations = new BusinessValidation();
    DataAccessService dataSearch = new DataAccessService();
    // En esta clase van a estar los metodos que comuniquen la informacion que el usuario ingrese
    // en la interfaz grafica, con la capa de datos
    //los requerimientos de datos primero van a pasar por aca, y luego se hace el pedido a la capa de datos
    /*
    public DEVOLUCIONDEDATO RequerimientoInformacion(requerimiento)
    {
           DATOS = llamadaAlMetodoDeDataAccess(requerimiento);
           //logaritmo de procesado de informacion para devolucion a la capa de User Interfaz
           return DATOS;
    }
     */

    private Map<String, Integer> flightDistance = new HashMap<>();
    private void setDistances(){
        this.flightDistance.put("Buenos Aires Cordoba",695);
        this.flightDistance.put("Buenos Aires Santiago de Chile",1400);
        this.flightDistance.put("Buenos Aires Montevideo",950);
        this.flightDistance.put("Cordoba Montevideo",1190);
        this.flightDistance.put("Cordoba Santiago de Chile",1050);
        this.flightDistance.put("Montevideo Santiago de Chile",2100);
    }

    public int flightCost(UserFlight flight){
        if(validations.cityValidation(flight.getFlightOrigin(), flight.getFlightDestiny())
            && validations.companionsValidation(flight.getFlightCompanions())) {
            int cost = (getDistance(flight.getFlightOrigin(), flight.getFlightDestiny()) * typeCost(flight.getFlightCategory().getPropulsionType()))
                    + (flight.getFlightCompanions() * 3500)
                    + categoryCost(flight.getFlightCategory());
            return cost;
        }
        else {
            return 0;
        }
    }

    private int getDistance(String origin, String destiny){
        int distance=((flightDistance.containsKey(origin+" "+destiny))? flightDistance.get(origin+" "+destiny) :
                (flightDistance.containsKey(destiny+" "+origin))? flightDistance.get(destiny+" "+origin) : 0);
        return distance;
    }

    private int typeCost(PropulsionType propulsionType){
        int cost= ((propulsionType.equals(PropulsionType.PROPELLERENGINE))?150 :
                (propulsionType.equals(PropulsionType.PISTONSENGINE)) ? 225 : 300);
        return cost;
    }

    private int categoryCost(Plane plane){
        int cost= ((plane instanceof GoldPlane))? 6000 :
                (plane instanceof SilverPlane) ? 4000 : 3000 ;
        return cost;
    }


    private HashSet<Plane> availablePlanes(UserFlight flight)
    {
        List<CompanyFlight> flights = new ArrayList<>(); // llamada al metodo de DataAccess
        HashSet<Plane> planesCategory = Plane.addPlanes();
        HashSet<Plane>freePlanes = new HashSet<>();
        if(flights==null){
           return planesCategory;
        }else {
            for (Plane plane :planesCategory) {
                for (CompanyFlight confirmedFlight : flights) {
                    if(!validations.flightType(confirmedFlight,plane)
                            ||(validations.flightRout(flight,confirmedFlight))
                            &&validations.flightCapacity(flight.getFlightCompanions(),confirmedFlight))
                    {
                        freePlanes.add(plane);
                    }
                }
            }
        }
        return freePlanes;
    }

    public void saveFlight(Flight flight) throws IOException {
        dataSearch.dataSaveFlight(flight);
    }

    public User searchUser(int document){
        User user = dataSearch.searchUserInData(document);
        return user;
    }




}

package com.company.Business.Services;

import com.company.Business.AeroTaxiCompany.Plane.*;
import com.company.Business.BusinessValidation;
import com.company.Business.City;
import com.company.Business.User.Client;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.company.DataAccess.Services.DataAccessService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

    public int flightCost(Flight flight){
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
        int cost= ((propulsionType.equals(PropulsionType.PropellerEngine))?150 :
                (propulsionType.equals(PropulsionType.PistonsEngine)) ? 225 : 300);
        return cost;
    }

    private int categoryCost(Plane plane){
        int cost= ((plane instanceof GoldPlane))? 6000 :
                (plane instanceof SilverPlane) ? 4000 : 3000 ;
        return cost;
    }

/*
    private List<Plane> availablePlanes(Date flightDate)
    {
        List<Flight> flights = new ArrayList<>();
        List<Plane> planesCategory = new ArrayList<>();
        List<PropulsionType> planesType = new ArrayList<>();
        planesCategory=Plane.addPlanes();
        if(flights==null){
           return planesCategory;
        }else {
            for (Plane plane :planesCategory) {
                for (Flight flight : flights) {
                    if(!pl)
                    {

                    }
                }
            }
        }

        planes =
    }
*/
    public User searchUser(User user){
        //user = dataSearch.searchUserInData(user);
        return user;
    }
    public int dataSave(Object data){
        int response=0;
        if(data instanceof User)
            response=saveUser((User)data);
        if(data instanceof Client)
            response=saveClient((Client)data);
        if(data instanceof Flight)
            response=saveFlight((Flight)data);
        if(data instanceof Plane)
            response=savePlane((Plane)data);
        return response;
    }
    private int saveUser(User user){
        int response=0;

        return response;
    }

    private int saveClient(Client client){
        int response=0;

        return response;
    }


    private int saveFlight(Flight flight){
        int response=0;

        return response;
    }

    private int savePlane(Plane plane){
        int response=0;

        return response;
    }

}

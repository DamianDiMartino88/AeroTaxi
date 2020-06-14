package com.company.Business.Services;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.BusinessValidation;
import com.company.Business.City;
import com.company.Business.User.Client;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.company.DataAccess.Services.DataAccessService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.HashMap;
import java.util.Map;

public class BusinessService {
    BusinessValidation Validations = new BusinessValidation();
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

    public User searchUser(User user){

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

package com.company.Business.Services;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.BusinessValidation;
import com.company.Business.City;
import com.company.Business.User.Client;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.company.DataAccess.Services.DataAccessService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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




    public int getDistance(String origen, String destino){
        int distance=0;
        switch (origen)
        {
            case "Buenos Aires":
            {
                distance=(destino.equals("Cordoba")?695:(destino.equals("Montevideo")?950:(destino.equals("Santiago del Estero")?1400:0)));
                break;
            }
            case "Cordoba":
            {
                distance=(destino.equals("Buenos Aires")?695:(destino.equals("Montevideo")?1190:(destino.equals("Santiago del Estero")?1050:0)));
                break;
            }
            case "Montevideo":
            {
                distance=(destino.equals("Cordoba")?1190:(destino.equals("Buenos Aires")?950:(destino.equals("Santiago del Estero")?2100
                        :0)));
                break;
            }
            case "Santiago del Estero":
            {
                distance=(destino.equals("Cordoba")?1050:(destino.equals("Montevideo")?2100:(destino.equals("Buenos Aires")?1400:0)));
                break;
            }
        }
        return distance;
    }
}

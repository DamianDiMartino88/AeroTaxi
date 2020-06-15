package com.company.DataAccess.Services;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.User.Client;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataAccessService {

    private static ObjectMapper mapper = new ObjectMapper();
    private File fileUser = new File("User.json");
    private File fileClient = new File("Client.json");
    private File filePlane = new File("Plane.json");
    private File Fileflinght = new File("Flight.json");


    public DataAccessService(){};

    public void writeUserFile(User user ) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(fileUser,user);
    }

    public User readUserFile() throws JsonParseException, JsonMappingException, IOException {
        User user = mapper.readValue(fileUser, User.class);
        System.out.println(user.toString());
        return user;
    }
    public void writeListUserFile(List<User> userList ) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(fileUser,userList);
    }
    public List<User> readListUserFile() throws JsonParseException, JsonMappingException, IOException {
        List<User> users =mapper.readValue(fileUser, List.class);
        return users;
    }

    private void writeClientFile(Client client) throws JsonGenerationException, JsonMappingException, IOException{
        mapper.writerWithDefaultPrettyPrinter().writeValue(fileClient,client);
    }

    public Client readClientFile() throws JsonParseException, JsonMappingException, IOException {
        Client client= mapper.readValue(fileClient,Client.class);
        System.out.println(client.toString());
        return client;
    }
    private void writePlaneFile(Plane plane) throws JsonGenerationException, JsonMappingException, IOException{
        mapper.writerWithDefaultPrettyPrinter().writeValue(filePlane,plane);
    }

    public Plane readPlaneFile() throws JsonParseException, JsonMappingException, IOException {
        Plane plane = mapper.readValue(filePlane, Plane.class);
        System.out.println(plane.toString());
        return plane;
    }
    public void writeflightFile(Flight flight) throws JsonGenerationException, JsonMappingException, IOException{
        mapper.writerWithDefaultPrettyPrinter().writeValue(Fileflinght,flight);
    }

    public Flight readFlightFile() throws JsonParseException, JsonMappingException, IOException {
        Flight flight = mapper.readValue(Fileflinght, Flight.class);
        System.out.println(flight.toString());
        return flight;
    }
    //en esta clase se van a incluir todos los metodos q reciban los pedidos de informacion de la capa businessService,
    // hagan el request a la base de datos, y devuelvan la informacion a la capa solicitante

    /*
    public DEVOLUCIONDEDATO RequerimientoInformacion(requerimiento)
    {
           DATOS = BusquedaDeInformacionEnBaseDeDatos(requerimiento);
           //logaritmo de procesado de informacion para devolucion a la capa de Business
           return DATOS;
    }


    public DEVOLUCIONDEDATO BusquedaDeInformacionEnBaseDeDatos(requerimiento)
    {
           //logaritmo de busqueda de informacion para devolucion al metodo " RequerimientoInformacion"
           return DATOS;
    }
     */


}

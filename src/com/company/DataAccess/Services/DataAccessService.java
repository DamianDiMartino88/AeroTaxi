package com.company.DataAccess.Services;

import com.company.Business.AeroTaxiCompany.Company;
import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.City;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.company.Business.User.UserFlight;
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
    private File filePlane = new File("Plane.json");
    private File FileUserflight = new File("UserFlight.json"); //change dejalo pero quizas no lo usemos
    private File FileCompanyflight = new File("CompanyFlight.json"); //change dejalo pero quizas no lo usemos
    private File FileCompany = new File("Company.json"); //change


    public DataAccessService(){};
    //------------------------- Change

    //Con este metodo traigo un objeto de tipo Flight, si es UserFlight, llama al metodo de escritura de usuarios
    //si es tipo CompanyFlight, llama al metodo de escritura de datos en compañia
    public void dataSaveFlight(Flight flight) throws IOException {
        if (flight instanceof UserFlight)
        {
            writeUserFlightFile(flight);
            addNewFlightToUser((UserFlight)flight);
        }
        if (flight instanceof CompanyFlight)
        {
            writeCompanyFile(flight);
        }
    }

    //metodo de datos de compañia, recibe un tipo OBJECT se busca en la base de datos la compañia
    //luego se ve q tipo de objeto es y se agrega a la lista correspondiente de la compañia
    private void writeCompanyFile(Object obj) throws JsonGenerationException, JsonMappingException, IOException{
        Company company = readCompanyFile();
        if(obj instanceof Flight){
            company.addCompanyFlights((CompanyFlight) obj);
        }
        if(obj instanceof User){
            company.addUsers((User)obj);
        }
        if(obj instanceof City){
            company.addCitys((City)obj);
        }
        if(obj instanceof Plane){
            company.addPlanes((Plane)obj);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(FileCompany,company);
    }


    private Company readCompanyFile() throws JsonParseException, JsonMappingException, IOException {
        Company company = mapper.readValue(FileCompany, Company.class);
        System.out.println(company.toString());
        return company;
    }

    private void writeUserFlightFile(Flight flight) throws JsonGenerationException, JsonMappingException, IOException{
        mapper.writerWithDefaultPrettyPrinter().writeValue(FileUserflight,flight);
    }

    private Flight readUserFlightFile() throws JsonParseException, JsonMappingException, IOException {
        Flight flight = mapper.readValue(FileUserflight, Flight.class);
        System.out.println(flight.toString());
        return flight;
    }
    //trae al nuevo usuario, lo agrega a la lista de usuarios de la compañia, lo agrega en el archivo de usuarios
    public void saveNewUser(User newUser) throws IOException {
        writeCompanyFile(newUser);

        writeUserFile(newUser);
    }

    //metodo para agregar un vuelo a la lista de vuelos de un usuario
    public void addNewFlightToUser(UserFlight flight) throws IOException {
        User user = readUserFile();

        user.addFlight(flight);

        writeUserFile(user);

    }

    //-------------------------------------- Change


    private void writeUserFile(User user ) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(fileUser,user);
    }

    private User readUserFile() throws JsonParseException, JsonMappingException, IOException {
        User user = mapper.readValue(fileUser, User.class);
        System.out.println(user.toString());
        return user;
    }
    private void writeListUserFile(List<User> userList ) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(fileUser,userList);
    }
    private List<User> readListUserFile() throws JsonParseException, JsonMappingException, IOException {
        List<User> users =mapper.readValue(fileUser, List.class);
        return users;
    }

    private void writePlaneFile(Plane plane) throws JsonGenerationException, JsonMappingException, IOException{
        mapper.writerWithDefaultPrettyPrinter().writeValue(filePlane,plane);
    }

    private Plane readPlaneFile() throws JsonParseException, JsonMappingException, IOException {
        Plane plane = mapper.readValue(filePlane, Plane.class);
        System.out.println(plane.toString());
        return plane;
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

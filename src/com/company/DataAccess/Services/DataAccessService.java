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
    private File FileCompany = new File("Company.json");


    public DataAccessService(){};
    //------------------------- Change
    //trae la lista de usuarios, compara por documento los usuarios en la lista,
    //si encuenta coincidencia devuelve el usuariocon sus datos, sino devuelve el objeto con el documento asignado
    //para crear el nuevousario
    public User searchUserInData(int document){
        List<User> userList =readUserFile();
        User newUser = new User(document);
        newUser=checkExistence(userList,newUser);
        return newUser;
    }

    //Compara los usuariios por documentos
    private User checkExistence(List<User> userList, User newUser){
        for (User savedUser : userList) {
            if(newUser.getUserDocument()==savedUser.getUserDocument()){
                newUser=savedUser;
            }
        }
        return newUser;
    }

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
        mapper.writeValue(FileCompany,company);
    }

    private Company readCompanyFile() throws JsonParseException, JsonMappingException, IOException {
        Company company = mapper.readValue(FileCompany, Company.class);
        System.out.println(company.toString());
        return company;
    }

    private void writeUserFlightFile(Flight flight) throws JsonGenerationException, JsonMappingException, IOException{
        mapper.writeValue(FileUserflight,flight);
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
    }//ojo con esto

    //metodo para agregar un vuelo a la lista de vuelos de un usuario
    public void addNewFlightToUser(UserFlight flight) throws IOException {
        User user = readUserFile();

        user.addFlight(flight);

        writeUserFile(user);

    }

   // se llama despues de  cargado un usuario en una lista se le pasa la lista y escribe en json
    private void writeListUserFile(List<User> userList ) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writeValue(fileUser,userList);
    }
    //lee lista en json y la devuelve cuando la devuelve se puede buscar un usuario en esa lista QUE devuelve
    private List<User> readListUserFile() throws JsonParseException, JsonMappingException, IOException {
        List<User> users =mapper.readValue(fileUser, List.class);
        System.out.println(users.toString());
        return users;
    }
    // se le pasa el Hastset de Plane y crea el archivo json de plane , escribe el hastset de plane en json
    private void writePlaneFile(HashSet<Plane> planeHashset) throws JsonGenerationException, JsonMappingException, IOException{
        mapper.writeValue(filePlane,planeHashset);
    }
    // lee el filePLANE
    private HashSet<Plane> readPlaneFile() throws JsonParseException, JsonMappingException, IOException {
        HashSet<Plane> planeHashSet = mapper.readValue(filePlane, HashSet.class);
        System.out.println();//hay que pasarle el tostring 
        return planeHashSet;
    }
    // necesitas buscar un plane?


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

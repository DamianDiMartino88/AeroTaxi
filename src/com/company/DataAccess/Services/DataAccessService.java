package com.company.DataAccess.Services;

import com.company.Business.AeroTaxiCompany.Company;
import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.City;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.company.Business.User.UserFlight;
import com.company.DataAccess.DataAccessValidation;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class DataAccessService <T> {

    private static ObjectMapper mapper = new ObjectMapper();
    private String fileUser;
    private String filePlane;
    private File FileCompany = new File("Company.json");

    private File FileUserflight = new File("UserFlight.json"); //change dejalo pero quizas no lo usemos
    private File FileCompanyflight = new File("CompanyFlight.json"); //change dejalo pero quizas no lo usemos

    //constructor
    public DataAccessService() {
    }

    ;

    // getnamefile
    public String getNameFileUser() {
        return "User.json";
    }

    public String getNameFilePlane() {
        return "Plane.json";
    }

    // guarda lista de cualquier tipo en el json pasado por parametro
    private void writeListJSON(List<T> type, String json) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writeValue(new File(json), type);
    }

    //lee una lista de cualquier tipo en el json que le pases por parametro
    private List<T> readListFileJSON(String json) throws JsonParseException, JsonMappingException, IOException {
        List<T> type = mapper.readValue(new File(json), List.class);
        System.out.println(type.toString());
        return type;
    }

    //lee una hashSet de cualquier tipo de un json pasado por parametro
    private HashSet<T> readHasSet(String json) throws JsonParseException, JsonMappingException, IOException {
        HashSet<T> type = mapper.readValue(new File(json), HashSet.class);
        System.out.println(type.toString());
        return type;
    }

    //escribe un hashset en un json de cualquier tipo
    private void writeHashSet(HashSet<T> type, String json) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writeValue(new File(json), type);
    }
    //------------------------- Change
    //trae la lista de usuarios, compara por documento los usuarios en la lista,
    //si encuenta coincidencia devuelve el usuariocon sus datos, sino devuelve el objeto con el documento asignado
    //para crear el nuevousario

    //estos dos metodos ya  los adapte a la clase dataAccessService generica

    public User searchUserInData(int document) throws IOException {

        DataAccessService<User> userDataAccessService = new DataAccessService<>();
        List<User> userList = userDataAccessService.readListFileJSON(getNameFileUser());
        User newUser = new User(document);
        newUser = checkExistence(userList, newUser);
        return newUser;
    }

    //Compara los usuariios por documentos
    private User checkExistence(List<User> userList, User newUser) {
        for (User savedUser : userList) {
            if (newUser.getUserDocument() == savedUser.getUserDocument()) {
                newUser = savedUser;
            }
        }
        return newUser;
    }

    // esto es lo que habia antes::

   /* public User searchUserInData(int document) throws IOException {
        List<User> userList =readListUserFile();
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
    }*/

    //metodo de datos de compañia, recibe un tipo OBJECT se busca en la base de datos la compañia
    //luego se ve q tipo de objeto es y se agrega a la lista correspondiente de la compañia
    //este metodo ya lo adapte a la clase para llamarla se inicialisa un DataAccessService <T> pasasandole un tipo y lugo llamas escribirCompanyF

    private void writeCompanyFile(T obj) throws JsonGenerationException, JsonMappingException, IOException {
        Company company = readCompanyFile();
        if (obj instanceof Flight) {
            company.addCompanyFlights((CompanyFlight) obj);
        }
        if (obj instanceof User) {
            company.addUsers((User) obj);
        }
        if (obj instanceof City) {
            company.addCitys((City) obj);
        }
        if (obj instanceof Plane) {
            company.addPlanes((Plane) obj);
        }
        mapper.writeValue(FileCompany, company);
    }

    /* private void writeCompanyFile(Object obj) throws JsonGenerationException, JsonMappingException, IOException{
        Company company = readCompanyFile();
        if(obj instanceof CompanyFlight){
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
    }*/

    private Company readCompanyFile() throws JsonParseException, JsonMappingException, IOException {
        Company company = mapper.readValue(FileCompany, Company.class);
        System.out.println(company.toString());
        return company;
    }

    //trae al nuevo usuario, lo agrega a la lista de usuarios de la compañia, lo agrega en el archivo de usuarios
    // adaptada a la clase
    public void saveNewUser(User newUser) throws IOException {
        writeCompanyFile(newUser);
        DataAccessService<User> userDataAccessService = new DataAccessService<User>();
        List<User> userList = userDataAccessService.readListFileJSON(this.getNameFileUser());
        userList.add(newUser);
        userDataAccessService.writeListJSON(userList, this.getNameFileUser());
    }

    // lo que habia ::
    /*public void saveNewUser(User newUser) throws IOException {
        writeCompanyFile(newUser);

        List<User> userList = readListUserFile();
        userList.add(newUser);
        writeListUserFile(userList);
    }*/

    //metodo para agregar un vuelo a la lista de vuelos de un usuario
    //adaptada a la clase
     public void addNewFlightToUser(User user, UserFlight flight) throws IOException {
         DataAccessService<User> userDataAccessService = new DataAccessService<User>();
        List<User> usersList = userDataAccessService.readListFileJSON(this.getNameFileUser());
        for (User userToAdd : usersList) {
            if(userToAdd.getUserDocument()==user.getUserDocument())
            {
                userToAdd.addFlight(flight);
            }
        }
        userDataAccessService.writeListJSON(usersList,this.getNameFileUser());
    }
   // lo que habia
    /*public void addNewFlightToUser(User user, UserFlight flight) throws IOException {
        List<User> usersList = readListUserFile();
        for (User userToAdd : usersList) {
            if(userToAdd.getUserDocument()==user.getUserDocument())
            {
                userToAdd.addFlight(flight);
            }
        }
        writeListUserFile(usersList);
    }*/
    public CompanyFlight searchFlightInData(UserFlight userFlight) throws IOException {
        Company company = readCompanyFile();
        CompanyFlight newFlight = checkFlightExistence(company.getCompanyFlightsList(),userFlight);
        return  newFlight;
    }

    private CompanyFlight checkFlightExistence(List<CompanyFlight> flightsList, UserFlight userFlight) {
        CompanyFlight newFlight=new CompanyFlight();
        DataAccessValidation validations = new DataAccessValidation();
        for (CompanyFlight flights : flightsList) {
            if (validations.flightType(userFlight, flights.getFlightCategory())
                    && (validations.flightRout(userFlight, flights)))
            {
                newFlight=flights;
            }
        }
        return newFlight;
    }

} 
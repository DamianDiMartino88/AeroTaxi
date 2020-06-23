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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.net.UnknownServiceException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DataAccessService <T> {

    private static ObjectMapper mapper = new ObjectMapper();
    private String fileUser;
    private String fileCompany;
    DataAccessValidation validations = new DataAccessValidation();
    Company company = new Company();
    //private File FileCompany = new File("Company.json");

    //constructor
    public DataAccessService() {
    }

    // getnamefile
    private String getNameFileUser() {
        return "User.json";
    }

    private String getNameFileCompany() {
        return "Company.json";
    }

    // guarda lista de cualquier tipo en el json pasado por parametro
    private void writeListJSON(List<User> type, String json) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(json), type);
    }

    //lee una lista de cualquier tipo en el json que le pases por parametro
    private List<User> readListFileJSON(String json) throws JsonParseException, JsonMappingException, IOException {
        //List<T> type = mapper.readValue(new File(json), List.class);
        List<User> type =mapper.readValue(new File(json), new TypeReference<List<User>>(){});
        return type;
    }

    public List<User> getUsersList() throws IOException {
        return readListFileJSON(this.getNameFileUser());
    }

    public Company getCompany() throws IOException {
        return readCompanyFile();
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
        for (User savedUser : (List<User>)userList) {
            if (newUser.getUserDocument() == savedUser.getUserDocument()) {
                newUser = savedUser;
            }
        }
        return newUser;
    }

    //trae al nuevo usuario, lo agrega a la lista de usuarios lo agrega en el archivo de usuarios
    public void saveNewUser(User user) throws IOException {
        List<User> userList = readListFileJSON(this.getNameFileUser());
        userList.add(user);
        writeListJSON(userList,this.getNameFileUser());
    }

   //Con este metodo traigo un objeto de tipo Flight, si es UserFlight, llama al metodo de escritura de usuarios
   //si es tipo CompanyFlight, llama al metodo de escritura de datos en compañia
    public void dataSaveFlight(User user, T flight) throws IOException {
        SaveFlight(user,flight);
    }
   private void SaveFlight(User user, T flight) throws IOException {
       if (flight instanceof UserFlight)
       {
           addNewFlightToUser(user, (UserFlight)flight);
       }
       if (flight instanceof CompanyFlight)
       {
           addInfoCompanyFile(flight);
       }
   }

    public void cancelFlight(User user, UserFlight userFlightToCancel) throws IOException {
        List<User> userList = readListFileJSON(this.getNameFileUser());
        for (User users: userList) {
            if(user.getUserDocument()==users.getUserDocument()){
                users=changeState(users, userFlightToCancel);
            }
        }
        writeListJSON(userList,this.getNameFileUser());
    }

    private User changeState(User user, UserFlight userFlightToCancel){
        for (UserFlight userFlight: user.getFlightsList()) {
            if(validations.flightRoutToCancel(userFlight,userFlightToCancel)&&
                    validations.flightDate(userFlight,userFlightToCancel))
            {
                userFlight.cancelFlight();
            }
        }
        return user;
    }

    //metodo de datos de compañia, recibe un tipo OBJECT se busca en la base de datos la compañia
    //luego se ve q tipo de objeto es y se agrega a la lista correspondiente de la compañia
    //este metodo ya lo adapte a la clase para llamarla se inicialisa un DataAccessService <T> pasasandole un tipo y lugo llamas escribirCompanyF

    private void addInfoCompanyFile(T obj) throws JsonGenerationException, JsonMappingException, IOException {
        Company company = readCompanyFile();
        if (obj instanceof Flight) {
            company.addCompanyFlights((CompanyFlight) obj);
        }
        if (obj instanceof City) {
            company.addCitys((City) obj);
        }
        if (obj instanceof Plane) {
            company.addPlanes((Plane) obj);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.getNameFileCompany()), company);
    }
    public void writeCompanyFile(Company company) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.getNameFileCompany()), company);
    }

    public void writeUser (List<User> userList) throws  IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.getNameFileUser()), userList);
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
        Company company = mapper.readValue(new File(this.getNameFileCompany()), Company.class);
        System.out.println(company.toString());
        return company;
    }

    //metodo para agregar un vuelo a la lista de vuelos de un usuario
    //adaptada a la clase
     public void addNewFlightToUser(User user, UserFlight flight) throws IOException {
         flight.confirmFlight();
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
    public boolean searchFlightInData(UserFlight userFlight) throws IOException {
        boolean response = false;
        Company company = readCompanyFile();
        response = checkFlightExistence(userFlight);
        return  response;
    }

    private boolean checkFlightExistence( UserFlight userFlight) throws IOException {
        boolean response = false;
        Company company = readCompanyFile();
        for (CompanyFlight flights : company.getCompanyFlightsList()) {
            if (validations.flightType(userFlight, flights.getFlightCategory())
                    && (validations.flightRout(userFlight, flights))
                    &&validations.flightDate(flights,userFlight))
            {
                flights.addFlightPassengers(userFlight.getFlightCompanions()+1);
                response=true;
            }
        }
       writeCompanyFile(company);
        return response;
    }

} 
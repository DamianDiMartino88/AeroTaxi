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

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class DataAccessService <T> {

    private static ObjectMapper mapper = new ObjectMapper();
    private String fileUser;
    private String fileCompany;
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
    private void writeListJSON(List<T> type, String json) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writeValue(new File(json), type);
    }

    //lee una lista de cualquier tipo en el json que le pases por parametro
    private List<T> readListFileJSON(String json) throws JsonParseException, JsonMappingException, IOException {
        //List<T> type = mapper.readValue(new File(json), List.class);
        List<T> type = mapper.convertValue(new File(json),  new TypeReference<List<T>>(){});
        System.out.println(type.toString());
        return type;
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
           writeCompanyFile(flight);
       }
   }

    //metodo de datos de compañia, recibe un tipo OBJECT se busca en la base de datos la compañia
    //luego se ve q tipo de objeto es y se agrega a la lista correspondiente de la compañia
    //este metodo ya lo adapte a la clase para llamarla se inicialisa un DataAccessService <T> pasasandole un tipo y lugo llamas escribirCompanyF

    private void writeCompanyFile(T obj) throws JsonGenerationException, JsonMappingException, IOException {
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
        mapper.writeValue(new File(this.getNameFileCompany()), company);
    }

    public void writeComp (Company company) throws IOException {
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

    //trae al nuevo usuario, lo agrega a la lista de usuarios de la compañia, lo agrega en el archivo de usuarios
    // adaptada a la clase
    public void saveNewUser(T newUser) throws IOException {
        writeCompanyFile(newUser);
        DataAccessService<User> userDataAccessService = new DataAccessService<User>();
        List<User> userList = userDataAccessService.readListFileJSON(this.getNameFileUser());
        userList.add((User)newUser);
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
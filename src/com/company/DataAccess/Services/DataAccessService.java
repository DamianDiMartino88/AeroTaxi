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
import java.util.HashSet;
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
    public User searchUserInData(int document) throws IOException {
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
    }

    //Con este metodo traigo un objeto de tipo Flight, si es UserFlight, llama al metodo de escritura de usuarios
    //si es tipo CompanyFlight, llama al metodo de escritura de datos en compañia
    public void dataSaveFlight(User user, Flight flight) throws IOException {
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
    private void writeCompanyFile(Object obj) throws JsonGenerationException, JsonMappingException, IOException{
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
    }

    private Company readCompanyFile() throws JsonParseException, JsonMappingException, IOException {
        Company company = mapper.readValue(FileCompany, Company.class);
        System.out.println(company.toString());
        return company;
    }

    //trae al nuevo usuario, lo agrega a la lista de usuarios de la compañia, lo agrega en el archivo de usuarios
    public void saveNewUser(User newUser) throws IOException {
        writeCompanyFile(newUser);

        List<User> userList = readListUserFile();
        userList.add(newUser);
        writeListUserFile(userList);
    }

    //metodo para agregar un vuelo a la lista de vuelos de un usuario
    public void addNewFlightToUser(User user, UserFlight flight) throws IOException {
        List<User> usersList = readListUserFile();
        for (User userToAdd : usersList) {
            if(userToAdd.getUserDocument()==user.getUserDocument())
            {
                userToAdd.addFlight(flight);
            }
        }
        writeListUserFile(usersList);
    }

   // guarda la lista de usuarios
    private void writeListUserFile(List<User> userList ) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writeValue(fileUser,userList);
    }

    //lee lista en json y la devuelve cuando la devuelve se puede buscar un usuario en esa lista QUE devuelve
    private List<User> readListUserFile() throws JsonParseException, JsonMappingException, IOException {
        List<User> users =mapper.readValue(fileUser, List.class);
        System.out.println(users.toString());
        return users;
    }
   }

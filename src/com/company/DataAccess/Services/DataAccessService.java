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
    DataAccessValidation validations = new DataAccessValidation();
    Company company = new Company();


    public DataAccessService() {
    }

    // Retorna el nombre del archivo
    private String getNameFileUser() {
        return "User.json";
    }

    private String getNameFileCompany() {
        return "Company.json";
    }

    //metodopara guardar la lista de usuarios
    private void writeListJSON(List<User> type, String json) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(json), type);
    }

    //lee el archivo de usuario y retorna una lista de User
    private List<User> readListFileJSON(String json) throws JsonParseException, JsonMappingException, IOException {
        List<User> type =mapper.readValue(new File(json), new TypeReference<List<User>>(){});
        return type;
    }

    //accedo al metodo privado para devolver la lista de usuarios de manera q sea accesible solodesde dentro de la clase
    public List<User> getUsersList() throws IOException {
        return readListFileJSON(this.getNameFileUser());
    }

    //accedo al metodo privado para devolver la Company de manera q sea accesible solodesde dentro de la clase
    public Company getCompany() throws IOException {
        return readCompanyFile();
    }

    //trae la lista de usuarios, compara por documento los usuarios en la lista,
    //si encuenta coincidencia devuelve el usuariocon sus datos, sino devuelve el objeto con el documento asignado
    //para crear el nuevousario

    /*Recibo un numero de documento llama al metodo checkexistence enviandole la lista de usuarios y devuelvo
     el usuario buscado, o uno nuevo si no hay coincidencias*/
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

    //trae al nuevo usuario, lo agrega a la lista de usuarios y guarda la lista en el archivo de usuarios
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

    //Si es instanceof UserFlight, le asigno elnuevo vuelo alusuario, si es CompanyFlightlo agrrego a la lista de Company
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

   /*Traigo la lista de usuarios y la recorro hasta encontrar el usuario q va a cancelar su vuelo
    luego llamo al metodo changeState enviandole elusuario y el vuelo*/
    public void cancelFlight(User user, UserFlight userFlightToCancel) throws IOException {
        List<User> userList = readListFileJSON(this.getNameFileUser());
        for (User users: userList) {
            if(user.getUserDocument()==users.getUserDocument()){
                users=changeState(users, userFlightToCancel);
            }
        }
        writeListJSON(userList,this.getNameFileUser());
    }

    //Recibe usuario y vuelo ycambia el estado del mismo
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

    /*metodo de datos de compañia, recibe un tipo OBJECT se busca en la base de datos la compañia
    luego se ve q tipo de objeto es y se agrega a la lista correspondiente de la compañia
    por ultimo vuelvo a guardar a Company en el archivo*/
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

    //recibe un objeto Company y lo guarda en el archivo
    public void writeCompanyFile(Company company) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.getNameFileCompany()), company);
    }

    //Lee el archivo y devuelve el objeto de Company
    private Company readCompanyFile() throws JsonParseException, JsonMappingException, IOException {
        Company company = mapper.readValue(new File(this.getNameFileCompany()), Company.class);
        System.out.println(company.toString());
        return company;
    }

    /*primero confirma el vuelo ( cambia el estado )  luego recibe la lista de usuarios busca el usuario q compro el vuelo
    y le agrega el vuelo a su lista*/
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

    //recibe un userflight y devuelve un boolean, dependiendo si el vueloexiste o no
    public boolean searchFlightInData(UserFlight userFlight) throws IOException {
        boolean response = false;
        Company company = readCompanyFile();
        response = checkFlightExistence(userFlight);
        return  response;
    }

    /*recibe la lista de vuelos de searchFlightInData y compara tipo de avion, fecha y ruta para verificar q se trate delmismovuelo
    si el vuelo existe le agrega los pasajeros al vuelo*/
    private boolean checkFlightExistence( UserFlight userFlight) throws IOException {
        boolean response = false;
        Company company = readCompanyFile();
        for (CompanyFlight flights : company.getCompanyFlightsList()) {
            if (validations.flightType(userFlight, flights.getFlightCategory())
                    && (validations.flightRout(userFlight, flights))
                    &&validations.flightDate(flights,userFlight)
                    &&validations.flightCapacity(userFlight.getFlightCompanions()+1,flights))
            {
                flights.addFlightPassengers(userFlight.getFlightCompanions()+1);
                response=true;
            }
        }
       writeCompanyFile(company);
        return response;
    }

} 
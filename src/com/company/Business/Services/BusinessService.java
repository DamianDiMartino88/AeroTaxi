package com.company.Business.Services;

import com.company.Business.AeroTaxiCompany.Company;
import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.*;
import com.company.Business.BusinessValidation;
import com.company.Business.City;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.company.Business.User.UserFlight;
import com.company.DataAccess.Services.DataAccessService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BusinessService {
    BusinessValidation validations = new BusinessValidation();
    DataAccessService dataSearch = new DataAccessService();
    Company company = new Company();

    public BusinessService(){};

    //Seteo las Distancias con un Hashmap para resumir las posibilidades utilizando las Clave-Valor
    private Map<String, Integer> flightDistance = new HashMap<>();
    private void setDistances(){
        this.flightDistance.put("Buenos Aires Cordoba",695);
        this.flightDistance.put("Buenos Aires Santiago de Chile",1400);
        this.flightDistance.put("Buenos Aires Montevideo",950);
        this.flightDistance.put("Cordoba Montevideo",1190);
        this.flightDistance.put("Cordoba Santiago de Chile",1050);
        this.flightDistance.put("Montevideo Santiago de Chile",2100);
    }

    //Calculo de costo del vuelo
    public int flightCost(UserFlight flight){
        if(validations.cityValidation(flight.getFlightOrigin(), flight.getFlightDestiny())
            && validations.companionsValidation(flight.getFlightCompanions())) {
            int dist = getDistance(flight.getFlightOrigin(), flight.getFlightDestiny());
            int type = typeCost(flight.getFlightCategory().getPropulsionType());
            int comp =(flight.getFlightCompanions() * 3500);
            int cat= categoryCost(flight.getFlightCategory());

            int cost = (getDistance(flight.getFlightOrigin(), flight.getFlightDestiny()) * typeCost(flight.getFlightCategory().getPropulsionType()))
                    + (flight.getFlightCompanions() * 3500)
                    + categoryCost(flight.getFlightCategory());
            return cost;
        }
        else {
            return 0;
        }
    }

    /*Metodo llamado por flightCost, devuelve la distancia seteada en el HashMap combinando
    las posibilidades para encontrar la correcta y devolver la distancia */
    private int getDistance(String origin, String destiny){
        this.setDistances();
        int distance=((flightDistance.containsKey(origin+" "+destiny))? flightDistance.get(origin+" "+destiny) :
                (flightDistance.containsKey(destiny+" "+origin))? flightDistance.get(destiny+" "+origin) : 0);
        return distance;
    }

    //Metodo llamado por flightCost, devuelve el valor por km segun tipo de avion
    private int typeCost(PropulsionType propulsionType){
        int cost= ((propulsionType.equals(PropulsionType.PROPELLERENGINE))?150 :
                (propulsionType.equals(PropulsionType.PISTONSENGINE)) ? 225 : 300);
        return cost;
    }

    //Metodo llamado por categoryCost, Recibo un Plane devuelve el Valor por categoria de Avion
    private int categoryCost(Plane plane){
        int cost= ((plane instanceof GoldPlane))? 6000 :
                (plane instanceof SilverPlane) ? 4000 : 3000 ;
        return cost;
    }

    //Recibo por parametro el vuelo q necesita el usuario, pido la lista de vuelos del dia
    //si no hay ninguno devuelvo la lista completa, sino aplico los filtros necesarios y devuelvo la lista de vuelos disponibles
    public  HashSet<Plane> availablePlanes(UserFlight flight) throws IOException {
        List<CompanyFlight> flights = flightOfTheDay(flight.getFlightDate());
      //  flights = flightOfTheDay(flight.getFlightDate()); // llamada al metodo de DataAccess
        HashSet<Plane> planesCategory = getPlanesList();
        List<Plane> unavailableList = new ArrayList<>();
        HashSet<Plane> unavailableList2 = new HashSet<>();
        HashSet<Plane>freePlanes = new HashSet<>();
        if(flights.size()==0){
           return planesCategory;
        }else {
            /*Funcion lambda que cruza los Plane de las dos listas para verificar cuales son
             los aviones libres para determinada fecha*/
            freePlanes = planesCategory.stream()
                    .filter(e -> (flights.stream()
                            .filter(d -> d.getFlightCategory().equals(e))
                            .count())<1)
                    .collect(Collectors.toCollection(HashSet::new));
            System.out.println(unavailableList);
            /*Luego compruebo de los aviones de los vuelos confirmados si hay alguna coincidencia
              en tanto que el vuelotenga la misma ruta y la cantidad de lugares libres
              sea suficiente para los pasajes q se desean comprar*/
            for (CompanyFlight confirmedFlight: flights) {
                if((validations.flightRout(flight,confirmedFlight))
                        &&validations.flightCapacity(flight.getFlightCompanions(),confirmedFlight))
                {
                    freePlanes.add(confirmedFlight.getFlightCategory());
                }
            }
        }
        return freePlanes;
    }

    //Metodo que recibe una fecha y devuelve la lista de vuelos para dicha fecha
    public List<CompanyFlight> flightOfTheDay (String date) throws IOException {
        List<CompanyFlight> companyFlightList = new ArrayList<>();
        List<CompanyFlight> companyFlightListReturn = new ArrayList<>();
        companyFlightList = getCompanyFlightsList();
        for (CompanyFlight company: companyFlightList) {
            if (date.equals(company.getFlightDate())){
                companyFlightListReturn.add(company);
            }
        }return companyFlightListReturn;
    }

    //envio el usuario y el vuelo(puede ser UserFlight o CompanyFlight) para ser guardado
    //en caso de CompanyFlight el User sera "null"
    public void saveFlight(User user, Flight flight) throws IOException {
        dataSearch.dataSaveFlight(user, flight);
    }

    //Devuelvo la lista de Ciudades de Company
    public List<City> getCitysList() throws IOException {
        Company company = getCompanyInfo();
        return company.getCitysList();
    }

    //Devuelvo la lista de Vuelos de Company
    public List<CompanyFlight> getCompanyFlightsList() throws IOException {
        Company company = getCompanyInfo();
        return company.getCompanyFlightsList();
    }

    //Devuelvo la lista de aviones de Company
    public HashSet<Plane> getPlanesList() throws IOException {
        Company company = getCompanyInfo();
        return company.getPlanesList();
    }

    //Recupero La informacion de Company del archivo
    private Company getCompanyInfo() throws IOException {
       Company company = dataSearch.getCompany();
       return company;
    }

    //Llamada al metodo para guardar usuario de DataAccesService
    public void saveNewUser(User user) throws IOException {
        dataSearch.saveNewUser(user);
    }

    //Llamada al metodo para Cancelar Vuelo de DataAccesService
    public void cancelFlight(User user, UserFlight userFlight) throws IOException {
        dataSearch.cancelFlight(user,userFlight);
    }

    //llama de buscar usuario en Data, y devuelve un objeto con el usuario buscado si hay coincidencia
    //si el usuario no existe crea y devuelve un objeto User con el Documento ya asignado
    public User searchUser(int document) throws IOException {
        User user = dataSearch.searchUserInData(document);
        return user;
    }

    /*se agregan los acompañantes mas el user a la cantidad de pasajeros de CompanyFlight,
      Luego se guarda CompanyFlight en el archivo,
      tambien se utiliza para restar los pasajeros cuando un vuelo es cancelado por un usuario*/
    public void addPassengers(UserFlight userFlight, CompanyFlight companyFlight) throws IOException {
        if((userFlight.getFlightCompanions()+1)>0){
            companyFlight.addFlightPassengers(userFlight.getFlightCompanions() + 1);
            saveFlight(null, companyFlight);
        }else {
            companyFlight.addFlightPassengers(userFlight.getFlightCompanions()-1);
        }
    }

    //Llamada al metodo privado para checkear la contraseña
    public boolean tryPassword(String pass){
        return (checkPassWord(pass));
    }

    //Verifica la contraseña
    private boolean checkPassWord(String pass){
      return (pass.equals("LaContraseña.123"));
    }

    //Llamada que devuelve un Boolean en base a la existencia o no de un vuelo
    public boolean searchFlight(UserFlight userFlight) throws IOException {
        boolean response = dataSearch.searchFlightInData(userFlight);
        return response;
    }

    //pide a la clase DataAccess la lista de usuarios
    public List<User> getUsersList() throws IOException {
        List<User> userList = dataSearch.getUsersList();
        return userList;
    }

    //recibo un usuario, recorre su lista de vuelos, y devuelve el valor total gastado en vuelos
    public double totalFlightsCost(User user){
        double totalCost=0;
        for (UserFlight flight : user.getFlightsList())
        {
            totalCost=totalCost+flight.getFlightCost();
        }
        return totalCost;
    }

    /*recibo un usuario, recorre su lista de vuelos, y determina su mayor categoria utilizada
    utilizando unHashMap para establecer valores a cada categoria*/
    public String bestCategory(User user){
        String bestPlane =(user.getFlightsList().size()==0)? "No Flights Found":"Bronze Plane";
        String plane = "";
        HashMap<String,Integer> categoryValues = new HashMap<>();
        categoryValues.put("Gold Plane",1);
        categoryValues.put("Silver Plane",2);
        categoryValues.put("Bronze Plane",3);
        for (UserFlight flight : user.getFlightsList())
        {
           plane = (flight.getFlightCategory() instanceof GoldPlane)? "Gold Plane"
                   : (flight.getFlightCategory() instanceof GoldPlane) ? "Silver Plane" : "Bronze Plane";
            bestPlane=(categoryValues.get(bestPlane)>=categoryValues.get(plane))?plane : bestPlane ;

        }
        return bestPlane;
    }





}

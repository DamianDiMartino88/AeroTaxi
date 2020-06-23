package com.company.Business.AeroTaxiCompany;

import com.company.Business.AeroTaxiCompany.Plane.*;
import com.company.Business.City;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Company {
    //public JSONArray
    private HashSet<Plane> planesList;
    private List<City> citysList;
    private List<CompanyFlight> companyFlightsList;

    //La Lista de aviones ya esta inicializada dentro la informacion de "Company" a partir de esta lista
    //se va a checkear la disponibilidad para los vuelos.

    public Company(){
        this.planesList=addPlanes();
        this.citysList=addCitys();
        this.companyFlightsList=new ArrayList<>();
    }

    /*Annotations para indicarle a Jackson las subclases q va a implementar al guardar listas de tipo "Plane"
    las cuales van a tener dentro Objetos de tipo GoldPlane, SilverPlane, y BronzePlane*/
    //Metodo llamado en el constructor de Company que crea los Planes y los asigna a un hashset de Plane para q nose repita la categoria
    @JsonSetter
    private static HashSet<Plane> addPlanes()
    {
        HashSet<Plane> planesList = new HashSet<>();
        planesList.add(new GoldPlane(PropulsionType.PROPELLERENGINE));
        planesList.add(new GoldPlane(PropulsionType.PISTONSENGINE));
        planesList.add(new GoldPlane(PropulsionType.REACTIONENGINE));
        planesList.add(new SilverPlane(PropulsionType.PROPELLERENGINE));
        planesList.add(new SilverPlane(PropulsionType.PISTONSENGINE));
        planesList.add(new SilverPlane(PropulsionType.REACTIONENGINE));
        planesList.add(new BronzePlane(PropulsionType.PROPELLERENGINE));
        planesList.add(new BronzePlane(PropulsionType.PISTONSENGINE));
        planesList.add(new BronzePlane(PropulsionType.REACTIONENGINE));

        return planesList;
    }

    public HashSet<Plane> getPlanesList() {
        return planesList;
    }

    public void addPlanes(Plane plane) {
        this.planesList.add(plane);
    }

    public List<City> getCitysList() {
        return citysList;
    }

    //Agrega una ciudad a la lista
    public void addCitys(City city) {
        this.citysList.add(city);
    }

    //Metodo llamado en el constructor de la clase Company, crea una list de City y le agrega todas las ciudades disponibles
    private List<City> addCitys() {
        this.citysList = new ArrayList<>();
        this.citysList.add(City.Buenos_Aires);
        this.citysList.add(City.Cordoba);
        this.citysList.add(City.Santiago_de_Chile);
        this.citysList.add(City.Montevideo);

        return citysList;
    }

    public List<CompanyFlight> getCompanyFlightsList() {
        return companyFlightsList;
    }

    //Agrega un vuelo a la lista de vuelos de Company
    public void addCompanyFlights(CompanyFlight companyFlight) {
        this.companyFlightsList.add(companyFlight);
    }

    //Sobreescritura deToString para imprimir la informacion de Company
    @Override
    public String toString() {
        return "AeroTaxi Company: " +
                "Planes Fleet: " + planesList +
                ", Destinys List: " + citysList +
                ", Flights List: " + companyFlightsList +
                '}';
    }
}

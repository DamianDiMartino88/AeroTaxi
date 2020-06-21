package com.company.Business.AeroTaxiCompany;

import com.company.Business.AeroTaxiCompany.Plane.*;
import com.company.Business.City;
import com.company.Business.User.Flight;
import com.company.Business.User.User;

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

    public void addCitys(City city) {
        this.citysList.add(city);
    }

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

    public void addCompanyFlights(CompanyFlight companyFlight) {
        this.companyFlightsList.add(companyFlight);
    }
}

package com.company;

import com.company.Business.AeroTaxiCompany.Plane.GoldPlane;
import com.company.Business.City;
import com.company.Business.Services.BusinessService;
import com.company.Business.User.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class Main {

    public static void main(String[] args) {
        //JSONPObject

        GoldPlane gold=new GoldPlane();
        System.out.println(BusinessService.getDistance("Buenos Aires","Cordoba"));

        Flight vuelo = new Flight();
                City.Buenos_Aires.getDenomination();
    }

}

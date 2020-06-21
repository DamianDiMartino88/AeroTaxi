package com.company.UserInterface.Services;

import com.company.Business.City;
import com.company.Business.Services.BusinessService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CityComboBox {

        ArrayList<ObservableList<String>> statesTo = new ArrayList<>();
        BusinessService businessService = new BusinessService();





        public List<City> setStatesTo(String origin ) throws IOException {
            List<City> citysList = businessService.getCitysList();
            List<City> CitysTo = new ArrayList<>();
            for (City city: citysList) {
                if(!origin.equals(city.getDenomination())){
                    CitysTo.add(city);
                }
            }
            return CitysTo;
        }

    ObservableList<String> states = FXCollections.observableArrayList(
                "Buenos Aires",
                "Cordoba",
                "Montevideo",
                "Santiago De Chile");
        ObservableList<String> buenosAiresTo = FXCollections.observableArrayList(
                "Cordoba",
                "Montevideo",
                "Santiago De Chile");
        ObservableList<String> cordobaTo = FXCollections.observableArrayList(
                "Buenos Aires",
                "Montevideo",
                "Santiago De Chile");
        ObservableList<String> montevideoTo = FXCollections.observableArrayList(
                "Buenos Aires",
                "Cordoba",
                "Santiago De Chile");
        ObservableList<String> santiagoTo = FXCollections.observableArrayList(
                "Buenos Aires",
                "Cordoba",
                "Montevideo");

        public CityComboBox() throws IOException {
            statesTo.add(buenosAiresTo);
            statesTo.add(cordobaTo);
            statesTo.add(montevideoTo);
            statesTo.add(santiagoTo);

        }



        public ObservableList<String> getStates() {
            return states;
        }

        public String getStates(int index){
            return states.get(index);
        }

        public int getSizeStates(){
            return states.size();
        }

        public ObservableList<String> getStatesTo(int index) {
            return statesTo.get(index);
        }


}

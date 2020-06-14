package com.company.UserInterface.Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;


public class CityComboBox {

        ArrayList<ObservableList<String>> statesTo = new ArrayList<>();

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

        public CityComboBox() {
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

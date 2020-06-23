package com.company.Business.User;

import com.company.Business.AeroTaxiCompany.Plane.Plane;
import com.company.Business.AeroTaxiCompany.Plane.PropulsionType;
import com.company.Business.City;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

public class Flight {
    private int flightState=0;
    private String flightOrigin;
    private String flightDestiny;
    private Plane flightCategory;
    private String flightDate;

    public Flight (){
        this.flightState=0;
        this.flightOrigin="";
        this.flightDestiny="";
        this.flightCategory=null;
        this.flightDate=null;
    }

    public Flight (String flightOrigin, String flightDestiny, Plane flightCategory, String flightDate){
        this.flightState=0;
        this.flightOrigin = flightOrigin;
        this.flightDestiny = flightDestiny;
        this.flightCategory = flightCategory;
        this.flightDate = flightDate;
    }

    public int getFlightState() {
        return flightState;
    }

    /*Utilizando borrados logicos para poder obtener estadisticas los metodos cancelFlight u confirmFlight
    cambian el atriburo flightState a 0 cuando esta cancelado, u a 1 cuando esta confirmado
     se inicializa en 0,y una vez q el usuario compra un vuelo al momento de guardarlo en la lista
     se cambia el estado a 1*/
    public void cancelFlight() {
        this.flightState = 0;
    }

    public void confirmFlight() {this.flightState = 1; }

    public String getFlightOrigin() {
        return flightOrigin;
    }

    public void setFlightOrigin(String flightOrigin) {
        this.flightOrigin = flightOrigin;
    }

    public String getFlightDestiny() {
        return flightDestiny;
    }

    public void setFlightDestiny(String flightDestiny) {
        this.flightDestiny = flightDestiny;
    }

    public Plane getFlightCategory() {
        return flightCategory;
    }

    public void setFlightCategory(Plane flightCategory) {
        this.flightCategory = flightCategory;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    //Sobreescritura ToString para mostrar los datos de Flight
    @Override
    public String toString() {
        return "Origin: '" + flightOrigin + '\'' +
                ", Destiny: '" + flightDestiny + '\'' +
                ", Category:" + flightCategory +
                ", Date: " + flightDate;
    }
}

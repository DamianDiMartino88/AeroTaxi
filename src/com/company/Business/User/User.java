package com.company.Business.User;

import com.company.Business.AeroTaxiCompany.Plane.SilverPlane;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String userLastName;
    private int userDocument;
    private int userAge;
    private List<UserFlight> flightsList;

    public User(){
        this.userName="";
        this.userLastName="";
        this.userDocument=0;
        this.userAge=0;
        this.flightsList=new ArrayList<>();
    }

    public User(int userdocument){
        this.userName="";
        this.userLastName="";
        this.userDocument=userdocument;
        this.userAge=0;
        this.flightsList=new ArrayList<>();
    }

    public User(String username, String userlastname, int userdocument, int userage){
        this.userName=username;
        this.userLastName=userlastname;
        this.userDocument=userdocument;
        this.userAge=userage;
        this.flightsList=new ArrayList<>();
    }

    public void addFlight(UserFlight userFlight){
        this.flightsList.add(userFlight);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getUserDocument() {
        return userDocument;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public List<UserFlight> getFlightsList() {
        return flightsList;
    }

    //Sobreescritura de ToString para mostar los datos del usuario
    @Override
    public String toString() {
        return "Name:'" + userName + '\'' +
                ", LastName: '" + userLastName + '\'' +
                ", Document: " + userDocument +
                ", Age: " + userAge +
                ", List of Flights: " + flightsList;
    }
}

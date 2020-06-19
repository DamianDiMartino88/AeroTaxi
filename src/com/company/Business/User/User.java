package com.company.Business.User;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int userCounter=0;
    private int userId;
    private String userName;
    private String userLastName;
    private int userDocument;
    private int userAge;
    private List<UserFlight> flightsList;

    public User()
    {
        this.userCounter++;
        this.userId=userCounter;
        this.userName="";
        this.userLastName="";
        this.userDocument=0;
        this.userAge=0;
        this.flightsList=new ArrayList<>();
    }

    public User(int userdocument)
    {
        this.userCounter++;
        this.userId=userCounter;
        this.userName="";
        this.userLastName="";
        this.userDocument=userdocument;
        this.userAge=0;
        this.flightsList=new ArrayList<>();
    }

    public User(String username, String userlastname, int userdocument, int userage)
    {
        this.userCounter++;
        this.userId=userCounter;
        this.userName=username;
        this.userLastName=userlastname;
        this.userDocument=userdocument;
        this.userAge=userage;
        this.flightsList=new ArrayList<>();
    }

    public void addFlight(UserFlight userFlight){
        this.flightsList.add(userFlight);
    }

    public static int getUserCounter() {
        return userCounter;
    }

    public static void setUserCounter(int userCounter) {
        User.userCounter = userCounter;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setUserDocument(int userDocument) {
        this.userDocument = userDocument;
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

    public void setFlightsList(List<UserFlight> flightsList) {
        this.flightsList = flightsList;
    }
}

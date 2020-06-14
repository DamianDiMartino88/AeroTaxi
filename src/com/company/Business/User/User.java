package com.company.Business.User;

public class User {
    private static int userCounter=0;
    private int userId;
    private String userName;
    private String userLastName;
    private int userDocument;
    private int userAge;
    private Client userFlightsData;

    public User(Client client)
    {
        this.userCounter++;
        this.userId=userCounter;
        this.userName="";
        this.userLastName="";
        this.userDocument=0;
        this.userAge=0;
        this.userFlightsData=client;
    }

    public User(int userdocument)
    {
        this.userCounter++;
        this.userId=userCounter;
        this.userName="";
        this.userLastName="";
        this.userDocument=userdocument;
        this.userAge=0;
        this.userFlightsData=null;
    }

    public User(String username, String userlastname, int userdocument, int userage, Client client)
    {
        this.userCounter++;
        this.userId=userCounter;
        this.userName=username;
        this.userLastName=userlastname;
        this.userDocument=userdocument;
        this.userAge=userage;
        this.userFlightsData=client;
    }
}

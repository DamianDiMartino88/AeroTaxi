package com.company.UserInterface;

public class UserInterfaceValidations {
    //validaciones necesarias de ingreso de datos de clientes

    public boolean checkDocumentLenght(int document){
        return ((String.valueOf(document).length()>8)? false : true);
    }

    public boolean checkCharacters(int document){
        boolean isNumeric = String.valueOf(document).chars().allMatch(x -> Character.isDigit(x));
        return((String.valueOf(document).isEmpty())?true :false);
    }

    public boolean checkAge (int age){
        return ((age>120)? false : true);
    }

}

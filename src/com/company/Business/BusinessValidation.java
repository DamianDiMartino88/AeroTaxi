package com.company.Business;

public class BusinessValidation {
    //validaciones necesarias en el paso de informacion entre las capas de datos  y de user interface,
    // como por ejemplo en esta capa se van a hacer las validaciones de disponibilidad o capacidad de aviones

    public boolean cityValidation(String origin, String destiny){
        return (origin.equals(destiny))? false : true;
    }

    public boolean companionsValidation(int companions){
        boolean isNumeric = String.valueOf(companions).chars().allMatch(x -> Character.isDigit(x));
        return (companions>0&&isNumeric)? true : false;
    }


}

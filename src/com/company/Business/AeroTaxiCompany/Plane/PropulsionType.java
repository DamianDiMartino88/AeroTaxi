package com.company.Business.AeroTaxiCompany.Plane;

// Este enum fue creado solo para asignar el tipo de propulsion de manera mas eficiente
public enum PropulsionType {
    REACTIONENGINE("Reaction Engine"),
    PROPELLERENGINE("Propeller Engine"),
    PISTONSENGINE("Pistons Engine");

    private String denomination;

    //Constructor del Enum creado para asignar Denomination y leer mas amigablemente cada tipo
    private PropulsionType(String denomination) {
        this.denomination = denomination;
    };

    //Metodo para retornar la Denomination
    public String getDenomination(){
        return this.denomination;
    }
}

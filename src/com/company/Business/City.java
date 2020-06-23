package com.company.Business;

public enum City {

        Buenos_Aires("Buenos Aires"),
        Cordoba("Cordoba"),
        Santiago_de_Chile("Santiago de Chile"),
        Montevideo("Montevideo");

        private String denomination;

        //Constructor del Enum creado para asignar Denomination y leer mas amigablemente cada ciudad
        private City(String denomination) {
                    this.denomination = denomination;
                };

        //Metodo para retornar la Denomination
        public String getDenomination(){
                    return this.denomination;
                }
}


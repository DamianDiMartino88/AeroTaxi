package com.company.Business;

public enum City {

        Buenos_Aires("Buenos Aires"),
        Cordoba("Cordoba"),
        Santiago_de_Chile("Santiago de Chile"),
        Montevideo("Montevideo");

        private String denomination;

        private City(String denomination) {
            this.denomination = denomination;
        };

        public String getDenomination(){
            return this.denomination;
        }
}


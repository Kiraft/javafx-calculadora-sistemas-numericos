package com.example.calculadoraconversion.utils;

public class CheckType {

    public static String verificarTipoDato(String numero) {

        if (numero.matches("[01]+")) {
            return "2";
        }


        if (numero.matches("[0-7]+")) {
            return "8";
        }


        if (numero.matches("[0-9]+")) {
            return "10";
        }


        if (numero.matches("[0-9A-Fa-f]+")) {
            return "16";
        }

        return "desconocido";
    }
}

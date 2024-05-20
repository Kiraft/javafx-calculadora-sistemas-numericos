package com.example.calculadoraconversion.calculadoras;

import javafx.util.Pair;

import java.util.ArrayList;

public class Binario {

    public static String binarioAHexadecimal(String binario) {
        return Integer.toHexString(Integer.parseInt(binario, 2)).toUpperCase();
    }

    public static ArrayList<String> conversionPasoAPaso(String binario) {
        ArrayList<String> pasos = new ArrayList<>();

        int longitud = binario.length();

        // Asegurarse de que la longitud sea un múltiplo de 4
        while (longitud % 4 != 0) {
            binario = "0" + binario; // Agregar ceros a la izquierda si es necesario
            longitud++;
        }
        // Dividir en grupos de 4 bits y convertir a hexadecimal
        int grupoCount = 1;
        String division = "AGRUPACION DE BITS\n";
        for (int i = longitud - 1; i >= 0; i -= 4) {
            String grupo = binario.substring(Math.max(0, i - 3), i + 1);
            String hexadecimal = binarioAHexadecimal(grupo);
            division += grupo + " | ";
            pasos.add("Paso " + grupoCount + ": Convertir " + grupo + " a " + hexadecimal);
            grupoCount++;
        }
        // Eliminar el último "|"
        division = division.substring(0, division.length() - 2);
        pasos.add(0, division); // Agregar la división de bits al inicio de la lista de pasos
        // Agregar el paso de conversión final
        pasos.add("El numero binario " + binario + " se convierte \na hexadecimal: " + binarioAHexadecimal(binario));
        return pasos;
    }



    // Método para convertir un número binario a octal
    public static String binarioAOctal(String binario) {
        return Integer.toOctalString(Integer.parseInt(binario, 2));
    }

    // Método para dividir el número binario en grupos de 3 bits y mostrar su valor octal
    public static ArrayList<String> conversionPasoAPasoOctal(String binario) {
        ArrayList<String> pasos = new ArrayList<>();
        int longitud = binario.length();
        // Asegurarse de que la longitud sea un múltiplo de 3
        while (longitud % 3 != 0) {
            binario = "0" + binario; // Agregar ceros a la izquierda si es necesario
            longitud++;
        }
        // Dividir en grupos de 3 bits y convertir a octal
        int grupoCount = 1;
        String division = "DIVISIÓN DE BITS\n";
        for (int i = longitud - 1; i >= 0; i -= 3) {
            String grupo = binario.substring(Math.max(0, i - 2), i + 1);
            String octal = binarioAOctal(grupo);
            division += grupo + " | ";
            pasos.add("Paso " + grupoCount + ": Convertir " + grupo + " a " + octal);
            grupoCount++;
        }
        // Eliminar el último "|"
        division = division.substring(0, division.length() - 2);
        pasos.add(0, division); // Agregar la división de bits al inicio de la lista de pasos
        // Agregar el paso de conversión final
        pasos.add("El numero binario " + binario + " se convierte a octal: " + binarioAOctal(binario));
        return pasos;
    }



    // Método para convertir un número binario a decimal
    public static int binarioADecimal(String binario) {
        int decimal = 0;
        int longitud = binario.length();
        for (int i = 0; i < longitud; i++) {
            int digito = Character.getNumericValue(binario.charAt(i));
            decimal += digito * Math.pow(2, longitud - i - 1);
        }
        return decimal;
    }

    // Método para mostrar los pasos de la conversión de binario a decimal
    public static ArrayList<String> conversionPasoAPasoDec(String binario) {
        ArrayList<String> pasos = new ArrayList<>();
        int longitud = binario.length();
        int decimal = 0;
        String paso = "PASOS\n";
        for (int i = 0; i < longitud; i++) {
            int digito = Character.getNumericValue(binario.charAt(i));
            int potencia = longitud - i - 1;
            int resultado = digito * (int) Math.pow(2, potencia);
            paso += digito + " * 2^" + potencia + " = " + resultado + "\n";
            decimal += resultado;
        }
        pasos.add(paso);
        pasos.add("El numero binario " + binario + " se convierte a decimal: " + decimal);
        return pasos;
    }
}

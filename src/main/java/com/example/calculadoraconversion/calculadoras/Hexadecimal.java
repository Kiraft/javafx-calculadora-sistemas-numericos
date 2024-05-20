package com.example.calculadoraconversion.calculadoras;

import java.util.ArrayList;

public class Hexadecimal {

    public static int valorHexadecimal(char hex) {
        if (hex >= '0' && hex <= '9') {
            return hex - '0';
        } else if (hex >= 'A' && hex <= 'F') {
            return 10 + (hex - 'A');
        } else if (hex >= 'a' && hex <= 'f') {
            return 10 + (hex - 'a');
        } else {
            throw new IllegalArgumentException("Dígito hexadecimal inválido: " + hex);
        }
    }


    // Método para mostrar los pasos de la conversión de hexadecimal a decimal
    public static ArrayList<String> conversionPasoAPaso(String hexadecimal) {
        ArrayList<String> pasos = new ArrayList<>();
        int longitud = hexadecimal.length();
        int decimal = 0;
        String paso = "PASOS\n";
        for (int i = 0; i < longitud; i++) {
            char digitoHex = hexadecimal.charAt(i);
            int valor = valorHexadecimal(digitoHex);
            int potencia = longitud - i - 1;
            int resultado = valor * (int) Math.pow(16, potencia);
            paso += digitoHex + " * 16^" + potencia + " = " + resultado + "\n";
            decimal += resultado;
        }
        pasos.add(paso);
        pasos.add("El número hexadecimal " + hexadecimal + " se convierte a decimal: " + decimal);
        return pasos;
    }

    // Método para convertir un dígito hexadecimal a su valor binario
    public static String hexadecimalABinario(char hex) {
        switch (Character.toUpperCase(hex)) {
            case '0': return "0000";
            case '1': return "0001";
            case '2': return "0010";
            case '3': return "0011";
            case '4': return "0100";
            case '5': return "0101";
            case '6': return "0110";
            case '7': return "0111";
            case '8': return "1000";
            case '9': return "1009";
            case 'A': return "1010";
            case 'B': return "1011";
            case 'C': return "1100";
            case 'D': return "1101";
            case 'E': return "1110";
            case 'F': return "1111";
            default: throw new IllegalArgumentException("Dígito hexadecimal inválido: " + hex);
        }
    }

    // Método para convertir un número hexadecimal a binario
    public static String hexadecimalABinario(String hexadecimal) {
        StringBuilder binario = new StringBuilder();
        for (char hex : hexadecimal.toCharArray()) {
            binario.append(hexadecimalABinario(hex));
        }
        return binario.toString();
    }

    // Método para convertir un número binario a octal
    public static String binarioAOctal(String binario) {
        int decimal = Integer.parseInt(binario, 2);
        return Integer.toOctalString(decimal);
    }

    // Método para mostrar los pasos de la conversión de hexadecimal a octal
    public static ArrayList<String> conversionPasoAPasoHexToOctal(String hexadecimal) {
        ArrayList<String> pasos = new ArrayList<>();

        // Paso 1: Mostrar la conversión de cada dígito hexadecimal a binario
        StringBuilder conversiones = new StringBuilder("Paso 1: Conversión de cada dígito hexadecimal a binario:\n");
        for (char hex : hexadecimal.toCharArray()) {
            conversiones.append(hex).append(" = ").append(hexadecimalABinario(hex)).append("\n");
        }
        pasos.add(conversiones.toString().trim());

        // Paso 2: Convertir todo el número hexadecimal a binario
        String binario = hexadecimalABinario(hexadecimal);
        pasos.add("Paso 2: Convertir " + hexadecimal + " a binario: " + binario);

        // Paso 3: Agrupar el binario en grupos de 4 bits
        StringBuilder agrupacion4Bits = new StringBuilder("Agrupación en grupos de 4 bits:\n");
        for (int i = 0; i < binario.length(); i += 4) {
            agrupacion4Bits.append(binario.substring(i, Math.min(i + 4, binario.length()))).append(" ");
        }
        pasos.add(agrupacion4Bits.toString().trim());

        // Paso 4: Asegurarse de que la longitud sea un múltiplo de 3 para agrupar en bits de 3
        int longitud = binario.length();
        while (longitud % 3 != 0) {
            binario = "0" + binario;
            longitud++;
        }

        // Paso 5: Agrupar el binario en grupos de 3 bits de derecha a izquierda
        StringBuilder division = new StringBuilder("DIVISIÓN DE BITS\n");
        for (int i = 0; i < longitud; i += 3) {
            division.append(binario.substring(i, i + 3)).append(" | ");
        }
        pasos.add(division.substring(0, division.length() - 2)); // Eliminar el último "|"

        // Paso 6: Convertir cada grupo de 3 bits a octal
        int grupoCount = 1;
        StringBuilder octal = new StringBuilder();
        for (int i = 0; i < longitud; i += 3) {
            String grupo = binario.substring(i, i + 3);
            String valorOctal = binarioAOctal(grupo);
            pasos.add("Paso " + grupoCount + ": Convertir " + grupo + " a " + valorOctal);
            octal.append(valorOctal);
            grupoCount++;
        }

        // Paso final: Mostrar el resultado de la conversión
        pasos.add("El número hexadecimal " + hexadecimal + " se convierte a octal: " + octal.toString());

        return pasos;
    }

    public static ArrayList<String> conversionPasoAPasoHexToBin(String hexadecimal) {
        ArrayList<String> pasos = new ArrayList<>();

        // Paso 1: Mostrar la conversión de cada dígito hexadecimal a binario
        StringBuilder conversiones = new StringBuilder("Paso 1: Conversión de cada dígito hexadecimal a binario:\n");
        StringBuilder binario = new StringBuilder();
        for (char hex : hexadecimal.toCharArray()) {
            String binarioDígito = hexadecimalABinario(hex);
            conversiones.append(hex).append(" = ").append(binarioDígito).append("\n");
            binario.append(binarioDígito);
        }
        pasos.add(conversiones.toString().trim());

        // Paso 2: Agrupar el binario en grupos de 4 bits
        StringBuilder agrupacion4Bits = new StringBuilder("Paso 2: Agrupación en grupos de 4 bits:\n");
        for (int i = 0; i < binario.length(); i += 4) {
            agrupacion4Bits.append(binario.substring(i, Math.min(i + 4, binario.length()))).append(" ");
        }
        pasos.add(agrupacion4Bits.toString().trim());

        // Paso 3: Mostrar el resultado final
        pasos.add("Paso 3: El número hexadecimal " + hexadecimal + " se convierte a binario: " + binario.toString());

        return pasos;
    }

}

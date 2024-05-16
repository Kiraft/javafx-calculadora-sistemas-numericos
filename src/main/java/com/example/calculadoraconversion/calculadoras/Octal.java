package com.example.calculadoraconversion.calculadoras;

import java.util.ArrayList;

public class Octal {
    public StringBuilder valor = new StringBuilder();

    public void octalToBinary(String octalNumber, ArrayList<Integer> binaryList, ArrayList<String> pasosOctalToBinary) {
        StringBuilder binaryValue = new StringBuilder();
        int length = octalNumber.length();
        for (int i = 0; i < length; i++) {
            char digitChar = octalNumber.charAt(i);
            int digit = Character.getNumericValue(digitChar);
            binaryValue.append(String.format("%03d", Integer.parseInt(Integer.toBinaryString(digit))));
        }
        binaryList.add(Integer.parseInt(binaryValue.toString()));
        pasosOctalToBinary.add("El número octal " + octalNumber + " es equivalente a " + binaryValue + " en binario.");
    }



    public void octalToDecimal(String octalNumber, ArrayList<Integer> decimalList, ArrayList<String> pasosOctalToDecimal) {
        int decimalNumber = 0;
        int length = octalNumber.length();
        for (int i = 0; i < length; i++) {
            char digitChar = octalNumber.charAt(length - i - 1);
            int digit = Character.getNumericValue(digitChar);
            decimalNumber += digit * Math.pow(8, i);
        }
        decimalList.add(decimalNumber);
        pasosOctalToDecimal.add("El número octal " + octalNumber + " es equivalente a " + decimalNumber + " en decimal.");
    }

    public void octalToHexadecimal(String octalNumber, ArrayList<String> hexadecimalList, ArrayList<String> pasosOctalToHex) {
        StringBuilder hexValue = new StringBuilder();
        int length = octalNumber.length();
        for (int i = 0; i < length; i++) {
            char digitChar = octalNumber.charAt(i);
            int digit = Character.getNumericValue(digitChar);
            hexValue.append(Integer.toHexString(digit));
        }
        hexadecimalList.add(hexValue.toString());
        pasosOctalToHex.add("El número octal " + octalNumber + " es equivalente a " + hexValue + " en hexadecimal.");
    }
}

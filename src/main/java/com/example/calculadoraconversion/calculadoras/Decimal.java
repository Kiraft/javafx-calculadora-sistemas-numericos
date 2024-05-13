package com.example.calculadoraconversion.calculadoras;

import java.util.ArrayList;

public class Decimal {
    public StringBuilder valor = new StringBuilder();

    public void decimalToBinary(int decimalNumber, ArrayList<Integer> binaryList, ArrayList<Double> resideoList, ArrayList<Integer> divicionList, ArrayList<String> pasosDecimalToBinary) {
        if (decimalNumber == 0) {

            binaryList.add(0);
            divicionList.add(decimalNumber);
            valor.append(0);

            pasosDecimalToBinary.add("Dividimos " + decimalNumber + "/2 \nSi nuestro resultado no es un numero " +
                    "entero agregamos un uno \nen otro caso sera cero \nen esta caso no tenemos resideo por ende tenemos " + 0 + "\nStack de valores: " + valor.toString() );
        } else if (decimalNumber == 1) {
            double resideo = (double) decimalNumber / 2;

            binaryList.add(1);
            divicionList.add(decimalNumber);
            resideoList.add(resideo);
            valor.append(1);

            pasosDecimalToBinary.add("Dividimos " + decimalNumber + "/2 \nSi nuestro resultado no es un numero " +
                    "entero agregamos un uno \nen otro caso sera cero \nen esta caso tenemos " + resideo + " por ende tenemos " + 1 + "\nStack de valores: " + valor.toString());
        } else {
            int remainder = decimalNumber % 2;
            int quotient = decimalNumber / 2;
            double resideo = (double) decimalNumber / 2;

            valor.append(remainder);
            binaryList.add(remainder);
            resideoList.add(resideo);
            divicionList.add(decimalNumber);

            pasosDecimalToBinary.add("Dividimos " + decimalNumber + "/2 \nSi nuestro resultado no es un numero " +
                    "entero agregamos un uno \nen otro caso sera cero \nen esta caso tenemos " + resideo + " por ende tenemos " + remainder + "\nStack de valores: " + valor.toString());

            decimalToBinary(quotient, binaryList, resideoList, divicionList, pasosDecimalToBinary);

        }
    }

    public void decimalToOctal(int decimalNumber, ArrayList<Integer> octalList, ArrayList<Double> resideoList, ArrayList<Integer> divicionList, ArrayList<String> pasosDecimalToOctal ) {
        if (decimalNumber == 0) {
            octalList.add(0);
            divicionList.add(decimalNumber);
        } else {
            while (decimalNumber != 0) {
                int remainder = decimalNumber % 8;
                int quotient = decimalNumber / 8;
                double resideo = (double) decimalNumber / 8;
                octalList.add(remainder);
                resideoList.add(resideo);
                divicionList.add(decimalNumber);
                decimalNumber = quotient;
            }
        }
    }

    public void decimalToHexadecimal(int decimalNumber, ArrayList<String> hexadecimalList, ArrayList<Double> resideoList, ArrayList<Integer> divicionList, ArrayList<String> pasosDecimalToHex) {
        if (decimalNumber == 0) {
            hexadecimalList.add("0");
            divicionList.add(decimalNumber);
        } else {
            while (decimalNumber != 0) {
                int remainder = decimalNumber % 16;
                int quotient = decimalNumber / 16;
                double resideo = (double) decimalNumber / 16;

                // Convertir los valores mayores a 9 a caracteres hexadecimales
                String hexChar = (remainder < 10) ? String.valueOf(remainder) : Character.toString((char) ('A' + remainder - 10));

                hexadecimalList.add(hexChar);
                resideoList.add(resideo);
                divicionList.add(decimalNumber);
                decimalNumber = quotient;
            }
        }
    }
}

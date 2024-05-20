package com.example.calculadoraconversion.calculadoras;


import java.util.ArrayList;

public class Octal {
    // Método para convertir un número octal a decimal y mostrar los pasos
    public static ArrayList<String> conversionPasoAPasoOctalDec(String octal) {
        ArrayList<String> pasos = new ArrayList<>();
        int longitud = octal.length();
        int decimal = 0;

        // Paso 1: Mostrar la conversión de cada dígito octal a decimal
        pasos.add("Paso 1: Conversión de cada dígito octal a decimal:");
        for (int i = 0; i < longitud; i++) {
            char digitoChar = octal.charAt(i);
            int digito = Character.getNumericValue(digitoChar);
            int exponente = longitud - 1 - i;
            int valor = digito * (int) Math.pow(8, exponente);
            decimal += valor;
            pasos.add("  " + digito + " x 8^" + exponente + " = " + valor);
        }

        // Paso 2: Sumar todos los valores
        pasos.add("Paso 2: Sumar todos los valores obtenidos:");
        StringBuilder suma = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            char digitoChar = octal.charAt(i);
            int digito = Character.getNumericValue(digitoChar);
            int exponente = longitud - 1 - i;
            int valor = digito * (int) Math.pow(8, exponente);
            suma.append(valor);
            if (i < longitud - 1) {
                suma.append(" + ");
            }
        }
        suma.append(" = ").append(decimal);
        pasos.add("  " + suma.toString());

        // Paso 3: Mostrar el resultado final
        pasos.add("Paso 3: El número octal " + octal + " en decimal es: " + decimal);

        return pasos;
    }


    // Método para convertir un dígito octal a su valor binario
    public static String octalABinario(char oct) {
        switch (oct) {
            case '0': return "000";
            case '1': return "001";
            case '2': return "010";
            case '3': return "011";
            case '4': return "100";
            case '5': return "101";
            case '6': return "110";
            case '7': return "111";
            default: throw new IllegalArgumentException("Dígito octal inválido: " + oct);
        }
    }

    // Método para convertir un número octal a binario
    public static String octalABinario(String octal) {
        StringBuilder binario = new StringBuilder();
        for (char oct : octal.toCharArray()) {
            binario.append(octalABinario(oct));
        }
        return binario.toString();
    }

    // Método para convertir un dígito binario (4 bits) a su valor hexadecimal
    public static String binarioAHexadecimal(String binario) {
        int decimal = Integer.parseInt(binario, 2);
        return Integer.toHexString(decimal).toUpperCase();
    }


    // Método para mostrar los pasos de la conversión de octal a hexadecimal
    public static ArrayList<String> conversionPasoAPasoOctaltoHex(String octal) {
        ArrayList<String> pasos = new ArrayList<>();

        // Paso 1: Mostrar la conversión de cada dígito octal a binario
        StringBuilder conversiones = new StringBuilder("Paso 1: Conversión de cada dígito octal a binario:\n");
        for (char oct : octal.toCharArray()) {
            conversiones.append(oct).append(" = ").append(octalABinario(oct)).append("\n");
        }
        pasos.add(conversiones.toString().trim());

        // Paso 2: Convertir todo el número octal a binario
        String binario = octalABinario(octal);
        pasos.add("Paso 2: Convertir " + octal + " a binario: " + binario);

        // Nota: Agrupación de los bits
        pasos.add("Nota: La agrupación en grupos de 4 bits se hace de derecha a izquierda. Si el último grupo no completa los 4 dígitos, se agregan ceros a la izquierda.");

        // Paso 3: Agrupar el binario en grupos de 4 bits (de derecha a izquierda)
        StringBuilder agrupacion4Bits = new StringBuilder("Paso 3: Agrupación en grupos de 4 bits:\n");
        int longitud = binario.length();
        while (longitud % 4 != 0) {
            binario = "0" + binario; // Agregar ceros a la izquierda si es necesario
            longitud++;
        }
        for (int i = 0; i < longitud; i += 4) {
            agrupacion4Bits.append(binario.substring(i, i + 4)).append(" ");
        }
        pasos.add(agrupacion4Bits.toString().trim());

        // Paso 4: Convertir cada grupo de 4 bits a hexadecimal
        int grupoCount = 1;
        StringBuilder hexadecimal = new StringBuilder();
        for (int i = 0; i < longitud; i += 4) {
            String grupo = binario.substring(i, i + 4);
            String valorHexadecimal = binarioAHexadecimal(grupo);
            pasos.add("Paso " + grupoCount + ": Convertir " + grupo + " a " + valorHexadecimal);
            hexadecimal.append(valorHexadecimal);
            grupoCount++;
        }

        // Paso final: Mostrar el resultado de la conversión
        pasos.add("El número octal " + octal + " se convierte a hexadecimal: " + hexadecimal.toString());

        return pasos;
    }





    // Método para eliminar los ceros a la izquierda de un número binario
    public static String eliminarCerosIzquierda(String binario) {
        int indicePrimerUno = binario.indexOf('1');
        return (indicePrimerUno == -1) ? "0" : binario.substring(indicePrimerUno);
    }

    // Método para mostrar los pasos de la conversión de octal a binario
    public static ArrayList<String> conversionPasoAPaso(String octal) {
        ArrayList<String> pasos = new ArrayList<>();

        // Paso 1: Mostrar la conversión de cada dígito octal a binario
        StringBuilder conversiones = new StringBuilder("Paso 1: Conversión de cada dígito octal a binario:\n");
        for (char oct : octal.toCharArray()) {
            conversiones.append(oct).append(" = ").append(octalABinario(oct)).append("\n");
        }
        pasos.add(conversiones.toString().trim());

        // Paso 2: Convertir todo el número octal a binario
        String binario = octalABinario(octal);
        pasos.add("Paso 2: Convertir " + octal + " a binario: " + binario);

        // Paso 3: Eliminar los ceros a la izquierda del número binario
        String binarioSinCerosIzquierda = eliminarCerosIzquierda(binario);
        pasos.add("Paso 3: Eliminar los ceros a la izquierda: " + binarioSinCerosIzquierda);

        // Nota sobre la eliminación de ceros a la izquierda
        pasos.add("Nota: Al eliminar los ceros a la izquierda, tomamos el número binario desde el primer '1' hasta el final.");

        // Paso final: Mostrar el resultado de la conversión
        pasos.add("El número octal " + octal + " se convierte a binario: " + binarioSinCerosIzquierda);

        return pasos;
    }
}

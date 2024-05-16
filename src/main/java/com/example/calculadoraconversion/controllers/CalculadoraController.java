package com.example.calculadoraconversion.controllers;

import com.example.calculadoraconversion.App;
import com.example.calculadoraconversion.calculadoras.Binario;
import com.example.calculadoraconversion.calculadoras.Octal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.example.calculadoraconversion.calculadoras.Decimal;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;


public class CalculadoraController implements Initializable {

    @FXML
    private ComboBox<?> boxConversion;

    @FXML
    private ComboBox<?> boxBase;

    @FXML
    private TextField txtDato;

    @FXML
    private Label labelValueBin;

    @FXML
    private Label labelValueDec;

    @FXML
    private Label labelValueHex;

    @FXML
    private Label labelValueOctal;

    @FXML
    private VBox pasosContainer;


    // INYECCION DE DEPENDENCIAS
    Decimal calculadoraDecimal = new Decimal();

    @FXML
    void baseAction(ActionEvent event) {
        if(boxBase.getSelectionModel().getSelectedItem().equals("10")){
            boxConversion.getItems().clear();
            ObservableList observableListConvertir = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Binario", "Octal", "Hexadecimal")));
            boxConversion.setItems(observableListConvertir);
        }else if (boxBase.getSelectionModel().getSelectedItem().equals("8")){
            boxConversion.getItems().clear();
            ObservableList observableListConvertir = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Binario", "Decimal", "Hexadecimal")));
            boxConversion.setItems(observableListConvertir);
        }else if (boxBase.getSelectionModel().getSelectedItem().equals("16")){
            boxConversion.getItems().clear();
            ObservableList observableListConvertir = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Binario", "Octal", "Decimal")));
            boxConversion.setItems(observableListConvertir);
        }else if (boxBase.getSelectionModel().getSelectedItem().equals("2")){
            boxConversion.getItems().clear();
            ObservableList observableListConvertir = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Decimal", "Octal", "Hexadecimal")));
            boxConversion.setItems(observableListConvertir);
        }
    }


    @FXML
    void convertValue(ActionEvent event) {
        pasosContainer.getChildren().clear();
        calculadoraDecimal.valor.setLength(0);
        if (!txtDato.getText().isEmpty()) {
            if(boxBase.getSelectionModel().getSelectedItem().equals("10")){
                if(boxConversion.getSelectionModel().getSelectedItem().equals("Binario")){

                    ArrayList<String> pasosDecimalToBinary = new ArrayList<>();
                    decimalToBinary(pasosDecimalToBinary);

                    for (String value : pasosDecimalToBinary) {
                        Text paso = new Text(value);
                        pasosContainer.getChildren().add(paso);
                    }

                } else if(boxConversion.getSelectionModel().getSelectedItem().equals("Octal")){
                    ArrayList<String> pasosDecimalToOctal = new ArrayList<>();
                    decimalToOctal(pasosDecimalToOctal);

                    for (String value : pasosDecimalToOctal) {
                        Text paso = new Text(value);
                        pasosContainer.getChildren().add(paso);
                    }
                } else if(boxConversion.getSelectionModel().getSelectedItem().equals("Hexadecimal")){
                    ArrayList<String> pasosDecimalToHex = new ArrayList<>();
                    decimalToHex(pasosDecimalToHex);

                    for (String value : pasosDecimalToHex) {
                        Text paso = new Text(value);
                        pasosContainer.getChildren().add(paso);
                    }
                }
            }else if(boxBase.getSelectionModel().getSelectedItem().equals("2")) {
                if(boxConversion.getSelectionModel().getSelectedItem().equals("Hexadecimal")){
                    ArrayList<String> pasos = Binario.conversionPasoAPaso(txtDato.getText());
                    Text primerPaso = new Text("Dividir el binario en grupo de 4 de derecha a izquierda, donde \ncada grupo representa un numero hexadecimal\nsi hay grupos incompletos se agregaran ceros a la izquierda");
                    primerPaso.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(primerPaso);
                    try {
                        FXMLLoader xdLoader = new FXMLLoader(App.class.getResource("binario.fxml"));
                        Parent xdRoot = xdLoader.load();
                        pasosContainer.getChildren().add(xdRoot);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    addStepsToContainerToBinarioTo(pasos);
                }else if (boxConversion.getSelectionModel().getSelectedItem().equals("Octal")){
                    ArrayList<String> pasos = Binario.conversionPasoAPasoOctal(txtDato.getText());
                    Text primerPasoOctal = new Text("Dividir el binario en grupo de 3 de derecha a izquierda, donde \ncada grupo representa un numero Octal\nsi hay grupos incompletos se agregaran ceros a la izquierda");
                    primerPasoOctal.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(primerPasoOctal);
                    try {
                        FXMLLoader xdLoader = new FXMLLoader(App.class.getResource("binario.fxml"));
                        Parent xdRoot = xdLoader.load();
                        pasosContainer.getChildren().add(xdRoot);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    addStepsToContainerToBinarioTo(pasos);
                }else if (boxConversion.getSelectionModel().getSelectedItem().equals("Decimal")){
                    ArrayList<String> pasos = Binario.conversionPasoAPasoDec(txtDato.getText());

                    addStepsToContainerToBinarioTo(pasos);
                }
            }


        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje");
            alert.setHeaderText(null);
            alert.setContentText("Ingresa un valor antes de continuar");

            alert.showAndWait();
        }
    }

    private void addStepsToContainerToBinarioTo(ArrayList<String> pasos) {
        for (String value : pasos) {
            Text paso = new Text(value);
            paso.setStyle("-fx-font-size: 18px;");
            pasosContainer.getChildren().add(paso);
        }
        labelValueBin.setText(txtDato.getText());
        labelValueHex.setText(Integer.toHexString(Integer.parseInt(txtDato.getText(), 2)).toUpperCase());
        labelValueDec.setText(Integer.toString(Integer.parseInt(txtDato.getText(), 2)));
        labelValueOctal.setText(Integer.toOctalString(Integer.parseInt(txtDato.getText(), 2)));
    }

    private void decimalToHex(ArrayList<String> pasosDecimalToHex) {

        ArrayList<String> resultadosHexList = new ArrayList<>();
        calculadoraDecimal.decimalToHexadecimal(Integer.parseInt(txtDato.getText()), resultadosHexList,  pasosDecimalToHex);
        StringBuilder stringBuilderHex = new StringBuilder();
        for (String value : resultadosHexList) {
            stringBuilderHex.append(value);
        }
        labelValueHex.setText(stringBuilderHex.reverse().toString());

        ArrayList<String> pasosDecimalToOctal = new ArrayList<>();
        ArrayList<Integer> resultadosOctalList = new ArrayList<>();
        calculadoraDecimal.decimalToOctal(Integer.parseInt(txtDato.getText()), resultadosOctalList, pasosDecimalToOctal);
        StringBuilder stringBuilderOctal = new StringBuilder();
        for (int value : resultadosOctalList) {
            stringBuilderOctal.append(value);
        }
        labelValueOctal.setText(stringBuilderOctal.toString());


        ArrayList<String> pasosDecimalToBinary = new ArrayList<>();
        ArrayList<Integer> resultadosBinaryList = new ArrayList<>();
        calculadoraDecimal.decimalToBinary(Integer.parseInt(txtDato.getText()), resultadosBinaryList, pasosDecimalToBinary);
        StringBuilder stringBuilderBinary = new StringBuilder();
        for (int value : resultadosBinaryList) {
            stringBuilderBinary.append(value);
        }
        labelValueBin.setText(stringBuilderBinary.reverse().toString());



        labelValueDec.setText(txtDato.getText());
    }

    private void decimalToOctal(ArrayList<String> pasosDecimalToOctal) {

        ArrayList<Integer> resultadosOctalList = new ArrayList<>();
        calculadoraDecimal.decimalToOctal(Integer.parseInt(txtDato.getText()), resultadosOctalList, pasosDecimalToOctal);
        StringBuilder stringBuilderOctal = new StringBuilder();
        for (int value : resultadosOctalList) {
            stringBuilderOctal.append(value);
        }
        labelValueOctal.setText(stringBuilderOctal.toString());


        ArrayList<String> pasosDecimalToBinary = new ArrayList<>();
        ArrayList<Integer> resultadosBinaryList = new ArrayList<>();
        calculadoraDecimal.decimalToBinary(Integer.parseInt(txtDato.getText()), resultadosBinaryList, pasosDecimalToBinary);
        StringBuilder stringBuilderBinary = new StringBuilder();
        for (int value : resultadosBinaryList) {
            stringBuilderBinary.append(value);
        }
        labelValueBin.setText(stringBuilderBinary.reverse().toString());


        ArrayList<String> pasosDecimalToHex = new ArrayList<>();
        ArrayList<String> resultadosHexList = new ArrayList<>();
        calculadoraDecimal.decimalToHexadecimal(Integer.parseInt(txtDato.getText()), resultadosHexList,  pasosDecimalToHex);
        StringBuilder stringBuilderHex = new StringBuilder();
        for (String value : resultadosHexList) {
            stringBuilderHex.append(value);
        }
        labelValueHex.setText(stringBuilderHex.reverse().toString());


        labelValueDec.setText(txtDato.getText());
    }

    private void decimalToBinary(ArrayList<String> pasosDecimalToBinary) {
        ArrayList<Integer> resultadosBinaryList = new ArrayList<>();
        calculadoraDecimal.decimalToBinary(Integer.parseInt(txtDato.getText()), resultadosBinaryList, pasosDecimalToBinary);
        StringBuilder stringBuilderBinary = new StringBuilder();
        for (int value : resultadosBinaryList) {
            stringBuilderBinary.append(value);
        }
        labelValueBin.setText(stringBuilderBinary.reverse().toString());


        ArrayList<Integer> resultadosOctalList = new ArrayList<>();
        calculadoraDecimal.decimalToOctal(Integer.parseInt(txtDato.getText()), resultadosOctalList);
        StringBuilder stringBuilderOctal = new StringBuilder();
        for (int value : resultadosOctalList) {
            stringBuilderOctal.append(value);
        }
        labelValueOctal.setText(stringBuilderOctal.toString());


        ArrayList<String> resultadosHexList = new ArrayList<>();
        calculadoraDecimal.decimalToHexadecimal(Integer.parseInt(txtDato.getText()), resultadosHexList);
        StringBuilder stringBuilderHex = new StringBuilder();
        for (String value : resultadosHexList) {
            stringBuilderHex.append(value);
        }
        labelValueHex.setText(stringBuilderHex.reverse().toString());


        labelValueDec.setText(txtDato.getText());
    }


    public void loadBasesInComboBox(){
        ObservableList observableListBase = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("8", "10", "2", "16")));
        ObservableList observableListConvertir = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Binario", "Decimal", "Octal", "Hexadecimal")));
        boxConversion.setItems(observableListConvertir);
        boxBase.setItems(observableListBase);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                loadBasesInComboBox();
            });
        });

        hilo.start();
    }
}

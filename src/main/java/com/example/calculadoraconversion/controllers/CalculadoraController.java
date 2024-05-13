package com.example.calculadoraconversion.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

import com.example.calculadoraconversion.calculadoras.Decimal;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


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
                    decimalToBinario(pasosDecimalToBinary);

                    for (String value : pasosDecimalToBinary) {
                        Text paso = new Text(value);
                        pasosContainer.getChildren().add(paso);
                    }

                } else if(boxConversion.getSelectionModel().getSelectedItem().equals("Octal")){

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



    private void decimalToBinario(ArrayList<String> pasosBinarytoDecimal) {
        ArrayList<Integer> resultadosBinaryList = new ArrayList<>();
        calculadoraDecimal.decimalToBinary(Integer.parseInt(txtDato.getText()), resultadosBinaryList, pasosBinarytoDecimal);
        StringBuilder stringBuilderBinary = new StringBuilder();
        for (int value : resultadosBinaryList) {
            stringBuilderBinary.append(value);
        }
        labelValueBin.setText(stringBuilderBinary.reverse().toString());


        ArrayList<String> pasosOctaltoDecimal = new ArrayList<>();
        ArrayList<Integer> resultadosOctalList = new ArrayList<>();
        calculadoraDecimal.decimalToOctal(Integer.parseInt(txtDato.getText()), resultadosOctalList, pasosOctaltoDecimal);
        StringBuilder stringBuilderOctal = new StringBuilder();
        for (int value : resultadosOctalList) {
            stringBuilderOctal.append(value);
        }
        labelValueOctal.setText(stringBuilderOctal.toString());


        ArrayList<String> pasosHextoDecimal = new ArrayList<>();
        ArrayList<String> resultadosHexList = new ArrayList<>();
        calculadoraDecimal.decimalToHexadecimal(Integer.parseInt(txtDato.getText()), resultadosHexList,  pasosHextoDecimal);
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

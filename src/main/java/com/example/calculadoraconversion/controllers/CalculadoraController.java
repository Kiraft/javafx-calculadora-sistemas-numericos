package com.example.calculadoraconversion.controllers;

import com.example.calculadoraconversion.rowsModels.BinaryRow;
import com.example.calculadoraconversion.rowsModels.HexRow;
import com.example.calculadoraconversion.rowsModels.OctalRow;
import com.example.calculadoraconversion.utils.CheckType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import com.example.calculadoraconversion.calculadoras.Decimal;

import javafx.application.Platform;


public class CalculadoraController implements Initializable {

    @FXML
    private ComboBox<?> boxConversion;

    @FXML
    private Button btnConvertir;

    @FXML
    private TableColumn<BinaryRow, String> columnResultado;

    @FXML
    private TableColumn<BinaryRow, String> columnaDivision;

    @FXML
    private TableColumn<BinaryRow, Double> columnaResideo;

    @FXML
    private TableView<BinaryRow> tableBinary;

    @FXML
    private TableColumn<OctalRow, String> columnResultadoOctal;

    @FXML
    private TableColumn<OctalRow, String> columnaDivisionOctal;

    @FXML
    private TableColumn<OctalRow, String> columnaResideoOctal;

    @FXML
    private TableView<OctalRow> tableOctal;

    @FXML
    private TableColumn<HexRow, String> columnResultadoHex;

    @FXML
    private TableColumn<HexRow, String> columnaDivisionHex;

    @FXML
    private TableColumn<HexRow, String> columnaResideoHex;

    @FXML
    private TableView<HexRow> tableHex;

    @FXML
    private Label labelBase;

    @FXML
    private TextField txtDato;


    // INYECCION DE DEPENDENCIAS
    Decimal calculadoraDecimal = new Decimal();
    public static ObservableList<BinaryRow> binaryRows = FXCollections.observableArrayList();
    public static ObservableList<OctalRow> octalRows = FXCollections.observableArrayList();
//    public static ObservableList<HexadecimalRow> hexadecimalRows = FXCollections.observableArrayList();


    @FXML
    void detectType(KeyEvent event) {
        String texto = txtDato.getText();
        labelBase.setText(CheckType.verificarTipoDato(texto));
    }


    @FXML
    void convertData(ActionEvent event) {
        showDecimaltoBinary();
        showDecimaltoOctal();
    }

    public void showDecimaltoBinary(){
        String texto = txtDato.getText().trim();
        int decimalNumber = Integer.parseInt(texto);

        ArrayList<Integer> binaryResultadosList = new ArrayList<>();
        ArrayList<Double> binaryResideosList = new ArrayList<>();
        ArrayList<Integer> binaryDivisionesList = new ArrayList<>();


        calculadoraDecimal.decimalToBinary(decimalNumber, binaryResultadosList, binaryResideosList, binaryDivisionesList);
        Collections.reverse(binaryDivisionesList);
        Collections.reverse(binaryResideosList);
        Collections.reverse(binaryResultadosList);


        for (int i = 0; i < binaryResultadosList.size(); i++) {
            binaryRows.add(new BinaryRow(binaryResultadosList.get(i).toString(), binaryDivisionesList.get(i).toString(), binaryResideosList.get(i).toString()));
        }

        tableBinary.setItems(binaryRows);
    }

    public void showDecimaltoOctal(){
        String texto = txtDato.getText().trim();
        int decimalNumber = Integer.parseInt(texto);

        ArrayList<Integer> octalResultadosList = new ArrayList<>();
        ArrayList<Double> octalResideosList = new ArrayList<>();
        ArrayList<Integer> octalDivisionesList = new ArrayList<>();
        calculadoraDecimal.decimalToOctal(decimalNumber, octalResultadosList, octalResideosList, octalDivisionesList);

        for (int i = 0; i < octalResultadosList.size(); i++) {
            octalRows.add(new OctalRow(octalResultadosList.get(i).toString(), octalDivisionesList.get(i).toString(), octalResideosList.get(i).toString()));
        }

        tableOctal.setItems(octalRows);


    }



    public void loadBasesInComboBox(){
        ObservableList observableListCarrera = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("8", "10", "2", "16")));
        boxConversion.setItems(observableListCarrera);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnaDivision.setCellValueFactory(new PropertyValueFactory<>("divisionValue"));
        columnaResideo.setCellValueFactory(new PropertyValueFactory<>("resideoValue"));
        columnResultado.setCellValueFactory(new PropertyValueFactory<>("binaryValue"));

        columnaDivisionOctal.setCellValueFactory(new PropertyValueFactory<>("divisionValue"));
        columnaResideoOctal.setCellValueFactory(new PropertyValueFactory<>("resideoValue"));
        columnResultadoOctal.setCellValueFactory(new PropertyValueFactory<>("octalValue"));


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

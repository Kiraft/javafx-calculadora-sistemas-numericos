package com.example.calculadoraconversion.controllers;

import com.example.calculadoraconversion.App;
import com.example.calculadoraconversion.calculadoras.Binario;
import com.example.calculadoraconversion.calculadoras.Hexadecimal;
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
    private RadioButton dieciseis;

    @FXML
    private RadioButton diez;

    @FXML
    private RadioButton dos;

    @FXML
    private ToggleGroup group1;

    @FXML
    private ToggleGroup group2;

    @FXML
    private Label labelValueBin;

    @FXML
    private Label labelValueDec;

    @FXML
    private Label labelValueHex;

    @FXML
    private Label labelValueOctal;

    @FXML
    private RadioButton ocho;

    @FXML
    private VBox pasosContainer;

    @FXML
    private RadioButton radioBinario;

    @FXML
    private RadioButton radioDecimal;

    @FXML
    private RadioButton radioHexadecimal;

    @FXML
    private RadioButton radioOctal;

    @FXML
    private TextField txtDato;

    // INYECCION DE DEPENDENCIAS
    Decimal calculadoraDecimal = new Decimal();


    @FXML
    void diesicesDisable(ActionEvent event) {
        radioDisables();
        radioHexadecimal.setDisable(true);
    }

    @FXML
    void diezDisable(ActionEvent event) {
        radioDisables();
        radioDecimal.setDisable(true);
    }

    @FXML
    void dosDisable(ActionEvent event) {
        radioDisables();
        radioBinario.setDisable(true);
    }

    @FXML
    void ochoDisable(ActionEvent event) {
        radioDisables();

        radioOctal.setDisable(true);
    }

    private void radioDisables() {
        radioBinario.setDisable(false);
        radioHexadecimal.setDisable(false);
        radioDecimal.setDisable(false);
        radioOctal.setDisable(false);
    }

    @FXML
    void toConvert(ActionEvent event) {
        pasosContainer.getChildren().clear();
        calculadoraDecimal.valor.setLength(0);
        if (!txtDato.getText().isEmpty()) {
            if(diez.isSelected()){
                if(radioBinario.isSelected()){

                    ArrayList<String> pasosDecimalToBinary = new ArrayList<>();
                    calculadoraDecimal.decimalToBinary(Integer.parseInt(txtDato.getText()), pasosDecimalToBinary);
                    for (String value : pasosDecimalToBinary) {
                        Text paso = new Text(value);
                        pasosContainer.getChildren().add(paso);
                    }
                    addStepsToContainerToDecTo();

                } else if(radioOctal.isSelected()){
                    ArrayList<String> pasosDecimalToOctal = new ArrayList<>();
                    calculadoraDecimal.decimalToOctal(Integer.parseInt(txtDato.getText()), pasosDecimalToOctal);

                    for (String value : pasosDecimalToOctal) {
                        Text paso = new Text(value);
                        pasosContainer.getChildren().add(paso);
                    }
                    addStepsToContainerToDecTo();
                    Text ultimoPaso = new Text("Paso Final: Le damos la vuelta al numero Octal: " +  Integer.toOctalString(Integer.parseInt(txtDato.getText())));
                    ultimoPaso.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(ultimoPaso);
                } else if(radioHexadecimal.isSelected()){
                    ArrayList<String> pasosDecimalToHex = new ArrayList<>();
                    calculadoraDecimal.decimalToHexadecimal(Integer.parseInt(txtDato.getText()),  pasosDecimalToHex);

                    for (String value : pasosDecimalToHex) {
                        Text paso = new Text(value);
                        pasosContainer.getChildren().add(paso);
                    }
                    addStepsToContainerToDecTo();
                    Text ultimoPaso = new Text("Paso Final: Unimos el los digitos de derecha a izquieda: " +  Integer.toHexString(Integer.parseInt(txtDato.getText())).toUpperCase());
                    ultimoPaso.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(ultimoPaso);
                }
            }else if(dos.isSelected()) {
                if(radioHexadecimal.isSelected()){
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
                }else if (radioOctal.isSelected()){
                    ArrayList<String> pasos = Binario.conversionPasoAPasoOctal(txtDato.getText());
                    Text primerPasoOctal = new Text("Dividir el binario en grupo de 3 de derecha a izquierda, donde \ncada grupo representa un numero Octal\nsi hay grupos incompletos se agregaran ceros a la izquierda");
                    primerPasoOctal.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(primerPasoOctal);
                    try {
                        FXMLLoader xdLoader = new FXMLLoader(App.class.getResource("octal.fxml"));
                        Parent xdRoot = xdLoader.load();
                        pasosContainer.getChildren().add(xdRoot);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    addStepsToContainerToBinarioTo(pasos);
                }else if (radioDecimal.isSelected()){
                    ArrayList<String> pasos = Binario.conversionPasoAPasoDec(txtDato.getText());

                    addStepsToContainerToBinarioTo(pasos);
                }
            }else if (dieciseis.isSelected()){
                if(radioDecimal.isSelected()){
                    ArrayList<String> pasos = Hexadecimal.conversionPasoAPaso(txtDato.getText());
                    Text primerPaso = new Text("Primero por cada digito Hexadecimal vamos a multiplicarlo por 16 con un exponente \ndonde se sumara 1 por cada digito hexadecimnal\nUsando una tabla como pivote para Hexadecimal a Decimal para las sumas");
                    primerPaso.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(primerPaso);

                    try {
                        FXMLLoader xdLoader = new FXMLLoader(App.class.getResource("hextodec.fxml"));
                        Parent xdRoot = xdLoader.load();
                        pasosContainer.getChildren().add(xdRoot);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    addStepsToContainerToHexTo(pasos);

                }else if(radioBinario.isSelected()){
                    ArrayList<String> pasos = Hexadecimal.conversionPasoAPasoHexToBin(txtDato.getText());
                    Text primerPaso = new Text("Primero vamos a obtener nuestra tabla de conversion de Hexadecimal a Binario que usaremos como pivote");
                    primerPaso.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(primerPaso);

                    try {
                        FXMLLoader xdLoader = new FXMLLoader(App.class.getResource("binario.fxml"));
                        Parent xdRoot = xdLoader.load();
                        pasosContainer.getChildren().add(xdRoot);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    addStepsToContainerToHexTo(pasos);
                    Text ultimoPaso = new Text("Paso 4: Le damos la vuelta al numero binario: " + Integer.toBinaryString(Integer.parseInt(txtDato.getText(), 16)).toUpperCase());
                    ultimoPaso.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(ultimoPaso);

                }else if(radioOctal.isSelected()){
                    ArrayList<String> pasos = Hexadecimal.conversionPasoAPasoHexToOctal(txtDato.getText());
                    Text primerPaso = new Text("Primero vamos a obtener nuestra tabla de conversion de Hexadecimal a Binario que usaremos como pivote \nAl igual que Binario a Octal");
                    primerPaso.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(primerPaso);
                    try {
                        FXMLLoader xdLoader = new FXMLLoader(App.class.getResource("binario.fxml"));
                        Parent xdRoot = xdLoader.load();
                        pasosContainer.getChildren().add(xdRoot);

                        FXMLLoader xdLoader2 = new FXMLLoader(App.class.getResource("octal.fxml"));
                        Parent xdRoot2 = xdLoader2.load();
                        pasosContainer.getChildren().add(xdRoot2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    addStepsToContainerToHexTo(pasos);


                }
            }else if (ocho.isSelected()){
                if(radioDecimal.isSelected()){
                    ArrayList<String> pasos = Octal.conversionPasoAPasoOctalDec(txtDato.getText());

                    addStepsToContainerToOctalTo(pasos);
                }else if (radioHexadecimal.isSelected()){
                    ArrayList<String> pasos = Octal.conversionPasoAPasoOctaltoHex(txtDato.getText());
                    Text primerPaso = new Text("Primero vamos a obtener nuestra tabla de conversion de Octal a Binario que usaremos como pivote \nAl igual que Binario a Hexadecimal");
                    primerPaso.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(primerPaso);
                    try {
                        FXMLLoader xdLoader = new FXMLLoader(App.class.getResource("octal.fxml"));
                        Parent xdRoot = xdLoader.load();
                        pasosContainer.getChildren().add(xdRoot);

                        FXMLLoader xdLoader2 = new FXMLLoader(App.class.getResource("binario.fxml"));
                        Parent xdRoot2 = xdLoader2.load();
                        pasosContainer.getChildren().add(xdRoot2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    addStepsToContainerToOctalTo(pasos);
                }else if (radioBinario.isSelected()){
                    ArrayList<String> pasos = Octal.conversionPasoAPaso(txtDato.getText());
                    Text primerPaso = new Text("Primero vamos a obtener nuestra tabla de conversion de Binario a Octal que usaremos como pivote");
                    primerPaso.setStyle("-fx-font-size: 18px;");
                    pasosContainer.getChildren().add(primerPaso);
                    try {
                        FXMLLoader xdLoader = new FXMLLoader(App.class.getResource("octal.fxml"));
                        Parent xdRoot = xdLoader.load();
                        pasosContainer.getChildren().add(xdRoot);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    addStepsToContainerToOctalTo(pasos);
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

    private void addStepsToContainerToOctalTo(ArrayList<String> pasos) {
        for (String value : pasos) {
            Text paso = new Text(value);
            paso.setStyle("-fx-font-size: 18px;");
            pasosContainer.getChildren().add(paso);
        }

        labelValueOctal.setText(txtDato.getText());
        labelValueBin.setText(Integer.toBinaryString(Integer.parseInt(txtDato.getText(), 8)).toUpperCase());
        labelValueDec.setText(Integer.toString(Integer.parseInt(txtDato.getText(), 8)));
        labelValueHex.setText(Integer.toHexString(Integer.parseInt(txtDato.getText(), 8)));
    }

    private void addStepsToContainerToHexTo(ArrayList<String> pasos) {
        for (String value : pasos) {
            Text paso = new Text(value);
            paso.setStyle("-fx-font-size: 18px;");
            pasosContainer.getChildren().add(paso);
        }

        labelValueHex.setText(txtDato.getText());
        labelValueBin.setText(Integer.toBinaryString(Integer.parseInt(txtDato.getText(), 16)).toUpperCase());
        labelValueDec.setText(Integer.toString(Integer.parseInt(txtDato.getText(), 16)));
        labelValueOctal.setText(Integer.toOctalString(Integer.parseInt(txtDato.getText(), 16)));
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

    private void addStepsToContainerToDecTo() {

        labelValueDec.setText(txtDato.getText());
        labelValueHex.setText(Integer.toHexString(Integer.parseInt(txtDato.getText())).toUpperCase());
        labelValueOctal.setText(Integer.toOctalString(Integer.parseInt(txtDato.getText())));
        labelValueBin.setText(Integer.toBinaryString(Integer.parseInt(txtDato.getText())));

    }

    public void loadBasesInComboBox(){
        ObservableList observableListBase = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("8", "10", "2", "16")));
        ObservableList observableListConvertir = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Binario", "Decimal", "Octal", "Hexadecimal")));

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

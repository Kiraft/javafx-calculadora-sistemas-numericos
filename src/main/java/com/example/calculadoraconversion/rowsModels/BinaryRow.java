package com.example.calculadoraconversion.rowsModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BinaryRow {
    private final StringProperty binaryValue;
    private final StringProperty divisionValue;
    private final StringProperty resideoValue;

    public BinaryRow(String binaryValue, String divisionValue, String resideoValue) {
        this.binaryValue = new SimpleStringProperty(binaryValue);
        this.divisionValue = new SimpleStringProperty(divisionValue + "/2");
        this.resideoValue = new SimpleStringProperty(resideoValue);
    }

    public StringProperty binaryValueProperty() {
        return binaryValue;
    }

    public StringProperty divisionValueProperty() {
        return divisionValue;
    }

    public StringProperty resideoValueProperty() {
        return resideoValue;
    }
}

package com.example.calculadoraconversion.rowsModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OctalRow {

    private final StringProperty octalValue;
    private final StringProperty divisionValue;
    private final StringProperty resideoValue;

    public OctalRow(String octalValue, String divisionValue, String resideoValue) {
        this.octalValue = new SimpleStringProperty(octalValue);
        this.divisionValue = new SimpleStringProperty(divisionValue + "/8");
        this.resideoValue = new SimpleStringProperty(resideoValue);
    }

    public StringProperty octalValueProperty() {
        return octalValue;
    }

    public StringProperty divisionValueProperty() {
        return divisionValue;
    }

    public StringProperty resideoValueProperty() {
        return resideoValue;
    }
}

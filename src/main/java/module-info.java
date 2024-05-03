module com.example.calculadoraconversion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.calculadoraconversion to javafx.fxml;
    exports com.example.calculadoraconversion;
    exports com.example.calculadoraconversion.controllers;
    opens com.example.calculadoraconversion.controllers to javafx.fxml;

    opens com.example.calculadoraconversion.rowsModels to javafx.base;
}
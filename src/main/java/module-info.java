module com.example.papasoftclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;

    opens com.example.papasoftclient to javafx.fxml;
    exports com.example.papasoftclient;
}
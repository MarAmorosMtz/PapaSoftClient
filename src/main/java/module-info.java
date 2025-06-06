module com.example.papasoftclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.sql;
    requires java.desktop;
    requires java.net.http;

    opens com.example.papasoftclient to javafx.fxml;
    opens com.example.papasoftclient.controllers.add to javafx.fxml;
    opens com.example.papasoftclient.controllers.delete to javafx.fxml;
    opens com.example.papasoftclient.controllers.edit to javafx.fxml;
    opens com.example.papasoftclient.controllers.main to javafx.fxml;
    opens com.example.papasoftclient.models to javafx.base,com.fasterxml.jackson.databind;
    opens com.example.papasoftclient.controllers.Utils to javafx.fxml;

    exports com.example.papasoftclient;
    exports com.example.papasoftclient.controllers.add;
    exports com.example.papasoftclient.controllers.delete;
    exports com.example.papasoftclient.controllers.edit;
    exports com.example.papasoftclient.controllers.main;
    exports com.example.papasoftclient.models;
}
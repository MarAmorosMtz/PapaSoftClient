module com.example.papasoftclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.httpclient;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires java.sql;

    opens com.example.papasoftclient to javafx.fxml;
    opens com.example.papasoftclient.controllers.add to javafx.fxml;
    opens com.example.papasoftclient.controllers.delete to javafx.fxml;
    opens com.example.papasoftclient.controllers.edit to javafx.fxml;
    opens com.example.papasoftclient.controllers.main to javafx.fxml;
    opens com.example.papasoftclient.models to javafx.base,com.fasterxml.jackson.databind;

    exports com.example.papasoftclient;
    exports com.example.papasoftclient.controllers.add;
    exports com.example.papasoftclient.controllers.delete;
    exports com.example.papasoftclient.controllers.edit;
    exports com.example.papasoftclient.controllers.main;
    exports com.example.papasoftclient.models;
}
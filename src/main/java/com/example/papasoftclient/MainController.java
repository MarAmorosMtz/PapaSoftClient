package com.example.papasoftclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane vista;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();

        String fxml = getViewForButton(clickedButton);
        if (fxml != null) {
            loadView(fxml);
        }
    }

    private String getViewForButton(Button button) {
        switch (button.getText()) {
            case "pene":
                return "";
            case "Maestros":
                return "";
            case "Materias":
                return "Materia/Materia";
            case "Asesores":
                return "";
            case "Asesorados":
                return "";
            case "Carreras":
                return "Carrera/Carrera";
            case "Asesorias":
                return "";
            default:
                return null;
        }
    }


    private void loadView(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        Parent nuevaVista = loader.load();

        vista.setCenter(nuevaVista);
    }
}
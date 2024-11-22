package com.example.papasoftclient.controllers.main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class BackupController {

    @FXML
    TextField txtPath;

    @FXML
    private void seleccionarArchivo() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar archivo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));

        Stage stage = new Stage();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            txtPath.setText(archivoSeleccionado.getAbsolutePath());
        } else {
            txtPath.setText("");
        }
    }

    @FXML
    private void crearCopia(){}

}

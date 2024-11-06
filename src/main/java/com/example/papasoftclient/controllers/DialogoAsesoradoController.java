package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.AsesoradoModel;
import com.example.papasoftclient.models.MateriaModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DialogoAsesoradoController {
    private Stage stage;
    private AsesoradoModel asesorado;

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesorado/vistaEditarAsesorado.fxml"));
        Parent parent = loader.load();


        EditAsesoradoController editController = loader.getController();
        editController.setModel(asesorado);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        closeStage();
    }

    @FXML
    private void delete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/materia/vistaConfirmacionAsesorado.fxml"));
        Parent parent = loader.load();

        ConfirmacionAsesoradoController confirmacionController = loader.getController();
        confirmacionController.setAsesorado(asesorado);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        closeStage();
    }




    public void setModel(AsesoradoModel asesorado){
        this.asesorado = asesorado;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void closeStage() {
        if (stage != null) {
            stage.close();
        }
    }
}

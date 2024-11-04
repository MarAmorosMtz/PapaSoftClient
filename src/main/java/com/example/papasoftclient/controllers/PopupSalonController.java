package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.models.SalonModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PopupSalonController {

    private Stage stage;
    private SalonModel salon;

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/materia/vistaEditarMateria.fxml"));
        Parent parent = loader.load();


        EditSalonController editController = loader.getController();
        editController.setModel(salon);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/materia/vistaConfirmacionMateria.fxml"));
        Parent parent = loader.load();

        ConfirmacionSalonController confirmacionController = loader.getController();
        confirmacionController.setSalon(salon);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        closeStage();
    }




    public void setModel(SalonModel materia){
        this.salon = materia;
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

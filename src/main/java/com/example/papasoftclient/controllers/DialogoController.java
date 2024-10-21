package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.models.CarreraModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DialogoController {
    private Stage stage;
    private CarreraModel carrera;
    private  CarreraBase carreraBase;

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Carrera/EditarCarrera.fxml"));
        Parent parent = loader.load();


        EditCarreraController editController = loader.getController();
        editController.setBase(carreraBase);
        editController.setModel(carrera);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Carrera/ConfirmacionCarrera.fxml"));
        Parent parent = loader.load();

        ConfirmacionController confirmacionController = loader.getController();
        confirmacionController.setCarrera(carrera);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        closeStage();
    }




    public void setModel(CarreraModel carrera){
        this.carrera = carrera;
    }

    public void setBase(CarreraBase base){
        this.carreraBase = base;
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

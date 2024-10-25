package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.MaestroBase;
import com.example.papasoftclient.models.MaestroModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DialogoMaestroController {
    private Stage stage;
    private MaestroModel maestro;
    private MaestroBase maestroBase;

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Maestro/EditarMaestro.fxml"));
        Parent parent = loader.load();


        EditMaestroController editController = loader.getController();
        editController.setBase(maestroBase);
        editController.setModel(maestro);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Maestro/ConfirmacionMaestro.fxml"));
        Parent parent = loader.load();

        ConfirmacionMaestroController confirmacionController = loader.getController();
        confirmacionController.setMaestro(maestro);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        closeStage();
    }




    public void setModel(MaestroModel maestro){
        this.maestro = maestro;
    }

    public void setBase(MaestroBase base){
        this.maestroBase = base;
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

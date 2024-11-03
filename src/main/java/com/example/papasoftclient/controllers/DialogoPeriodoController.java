package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DialogoPeriodoController {
    private Stage stage;
    private PeriodoModel periodo;
    private PeriodoBase periodoBase;

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/periodo/vistaEditarPeriodo.fxml"));
        Parent parent = loader.load();


        EditPeriodoController editController = loader.getController();
        editController.setBase(periodoBase);
        editController.setModel(periodo);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/periodo/vistaConfirmacionPeriodo.fxml"));
        Parent parent = loader.load();

        ConfirmacionPeriodoController confirmacionController = loader.getController();
        confirmacionController.setPeriodo(periodo);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        closeStage();
    }




    public void setModel(PeriodoModel periodo){
        this.periodo = periodo;
    }

    public void setBase(PeriodoBase base){
        this.periodoBase = base;
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

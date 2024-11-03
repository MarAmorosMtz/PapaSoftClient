package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.*;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DialogoMateriaController extends Observable {
    private Stage stage;
    private MateriaModel materia;
    private MateriaModel materiaBase;

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/materia/vistaEditarMateria.fxml"));
        Parent parent = loader.load();


        EditMateriaController editController = loader.getController();
        editController.setBase(materiaBase);
        editController.setModel(materia);

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

        ConfirmacionMateriaController confirmacionController = loader.getController();
        confirmacionController.setMateria(materia);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        closeStage();
    }




    public void setModel(MateriaModel materia){
        this.materia = materia;
    }

    public void setBase(MateriaModel base){
        this.materiaBase = base;
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

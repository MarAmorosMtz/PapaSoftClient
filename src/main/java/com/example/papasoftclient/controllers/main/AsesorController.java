package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.controllers.add.AddAsesorController;
import com.example.papasoftclient.controllers.delete.ConfirmacionAsesorController;
import com.example.papasoftclient.controllers.edit.EditAsesorController;
import com.example.papasoftclient.models.*;
import com.example.papasoftclient.utils.Observador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AsesorController implements Observador {

    @FXML
    AnchorPane anchorDisplay;
    @FXML
    Button swapButton;

    private boolean vistaTabla = false;

    private ScrollPaneAsesorController scrollController;
    private TableViewAsesorController tableController;

    @FXML
    public void initialize() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaScrollPane.fxml"));
            AnchorPane loadedAnchorPane = loader.load();
            scrollController = loader.getController();

            AnchorPane.setTopAnchor(loadedAnchorPane, 0.0);
            AnchorPane.setLeftAnchor(loadedAnchorPane, 0.0);
            AnchorPane.setRightAnchor(loadedAnchorPane, 0.0);
            AnchorPane.setBottomAnchor(loadedAnchorPane, 0.0);

            anchorDisplay.getChildren().add(loadedAnchorPane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaAgregarAsesor.fxml"));
        Parent parent = loader.load();
        AddAsesorController controller = loader.getController();
        controller.agregarObservador(this);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.setScene(scene);

        stage.setOnHiding(windowEvent -> {
            if(!vistaTabla){
                scrollController.actualizar();
            }else{
                tableController.actualizar();
            }
        });

        stage.show();
    }

    @FXML
    private void edit() throws IOException {
        AsesorModel asesorModel;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaEditarAsesor.fxml"));
        Parent parent = loader.load();
        if(!vistaTabla){
            asesorModel = scrollController.getSelectedAsesor();
        }else{
            asesorModel = tableController.getSelectedAsesor();
        }
        boolean isActive = "Activo".equalsIgnoreCase(asesorModel.getActivoTexto());
        asesorModel.setActivo(isActive);

        EditAsesorController controller = loader.getController();
        controller.agregarObservador(this);
        controller.setModel(asesorModel);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(true);

        stage.setOnHiding(windowEvent -> {
            if(!vistaTabla){
                scrollController.actualizar();
            }else{
                tableController.actualizar();
            }
        });

        stage.show();
    }

    @FXML
    private void delete() throws IOException {

        AsesorModel asesorModel;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaConfirmacionAsesor.fxml"));
        Parent parent = loader.load();
        if(!vistaTabla){
            asesorModel = scrollController.getSelectedAsesor();
        }else{
            asesorModel = tableController.getSelectedAsesor();
            tableController.actualizar();
        }
        ConfirmacionAsesorController confirmacionController = loader.getController();
        confirmacionController.agregarObservador(this);
        confirmacionController.setAsesor(asesorModel);

        GaussianBlur blurEffect = new GaussianBlur();
        blurEffect.setRadius(10);

        Stage mainStage = (Stage) anchorDisplay.getScene().getWindow();
        mainStage.getScene().getRoot().setEffect(blurEffect);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setOnHiding(event -> {
            mainStage.getScene().getRoot().setEffect(null);
            if(!vistaTabla){
                scrollController.actualizar();
            }else{
                tableController.actualizar();
            }
        });

        stage.show();
    }

    @FXML
    private void switchView() throws IOException {
        AnchorPane loadedAnchorPane;
        if(vistaTabla){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaScrollPane.fxml"));
            loadedAnchorPane = loader.load();

            scrollController = loader.getController();

            swapButton.setText("Vista de Tabla");

            vistaTabla = false;
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaTableView.fxml"));
            loadedAnchorPane = loader.load();

            tableController = loader.getController();

            swapButton.setText("Vista de Tarjetas");

            vistaTabla = true;
        }
        AnchorPane.setTopAnchor(loadedAnchorPane, 0.0);
        AnchorPane.setLeftAnchor(loadedAnchorPane, 0.0);
        AnchorPane.setRightAnchor(loadedAnchorPane, 0.0);
        AnchorPane.setBottomAnchor(loadedAnchorPane, 0.0);

        anchorDisplay.getChildren().clear();
        anchorDisplay.getChildren().add(loadedAnchorPane);

        tableController.actualizar();
        scrollController.actualizar();
    }


    @Override
    public void actualizar() { }

}

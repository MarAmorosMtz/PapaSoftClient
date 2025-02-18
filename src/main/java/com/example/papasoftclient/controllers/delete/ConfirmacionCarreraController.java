package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.repositories.CarreraRepository;

import com.example.papasoftclient.utils.Observable;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ConfirmacionCarreraController extends Observable {
    @FXML
    Button close;

    CarreraModel carrera;

    private CarreraRepository carreraRepository;

    public ConfirmacionCarreraController() {
        carreraRepository = new CarreraRepository();
    }

    @FXML
    private void confirmar(){
        boolean status =carreraRepository.remove(carrera.getId());
        if(!status){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Se ha producido un error");
            alerta.setContentText("No se ha podido eliminar. Compruebe si la entrada es referenciada. Compruebe la conexion");

            alerta.showAndWait();
        }
        cancelar();
        this.notificar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setCarrera(CarreraModel carrera){
        this.carrera = carrera;
    }
}

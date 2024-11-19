package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.SalonModel;
import com.example.papasoftclient.repositories.SalonRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmacionSalonController extends Observable {
    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    private SalonModel salon;

    private SalonRepository salonRepository;

    @FXML
    private void confirmar(){
        salonRepository = new SalonRepository();
        boolean status = salonRepository.remove(salon.getId());
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
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void setSalon(SalonModel salonModel){
        this.salon = salonModel;
    }
}
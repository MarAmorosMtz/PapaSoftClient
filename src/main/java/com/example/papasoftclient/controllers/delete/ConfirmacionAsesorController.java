package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.AsesorModel;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.Observable;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class ConfirmacionAsesorController extends Observable {

    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    private AsesorModel asesor;

    private AsesorRepository asesorRepository;

    public ConfirmacionAsesorController() {
        this.asesorRepository = new AsesorRepository();;
    }
    @FXML
    private void confirmar(){
        boolean status = asesorRepository.remove(asesor.getId());
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

    public void setAsesor(AsesorModel asesor){
        this.asesor = asesor;
    }

}

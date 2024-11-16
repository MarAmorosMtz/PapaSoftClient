package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.AsesorModel;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class ConfirmacionAsesorController {

    @FXML
    Button close;

    AsesorModel asesor;

    private CloseableHttpClient httpClient;
    private AsesorRepository asesorRepository;
    private ObjectMapper mapper;

    @FXML
    private void confirmar(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        asesorRepository = new AsesorRepository(httpClient, mapper , RestAPI.ASESORES_ENDPOINT);
        if(asesorRepository.remove(asesor.getId())){
            asesorRepository.remove(asesor.getId());
        }else{

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Se ha producido un error");
            alerta.setContentText("No se ha podido eliminar. Compruebe si la entrada es referenciada. Compruebe la conexion");

            alerta.showAndWait();
        }
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setAsesor(AsesorModel asesor){
        this.asesor = asesor;
    }

}

package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class ConfirmacionCarreraController {
    @FXML
    Button close;

    CarreraModel carrera;

    private CloseableHttpClient httpClient;
    private CarreraRepository carreraRepository;
    private ObjectMapper mapper;

    @FXML
    private void confirmar(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        carreraRepository = new CarreraRepository(httpClient, mapper , RestAPI.CARRERAS_ENDPOINT);
        if(carreraRepository.remove(carrera.getId())){
            carreraRepository.remove(carrera.getId());
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

    public void setCarrera(CarreraModel carrera){
        this.carrera = carrera;
    }
}

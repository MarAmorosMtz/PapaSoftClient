package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class ConfirmacionController {
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
        carreraRepository.remove(carrera.getId());
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

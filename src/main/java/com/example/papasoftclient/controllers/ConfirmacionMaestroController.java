package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.MaestroModel;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.MaestroRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class ConfirmacionMaestroController {
    @FXML
    Button close;

    MaestroModel maestro;

    private CloseableHttpClient httpClient;
    private MaestroRepository maestroRepository;
    private ObjectMapper mapper;

    @FXML
    private void confirmar(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        maestroRepository = new MaestroRepository(httpClient, mapper , RestAPI.MAESTROS_ENDPOINT);
        maestroRepository.remove(maestro.getId());
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setMaestro(MaestroModel maestro){
        this.maestro = maestro;
    }
}
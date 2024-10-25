package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.repositories.MaestroRepository;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class ConfirmacionPeriodoController {
    @FXML
    Button close;

    PeriodoModel periodo;

    private CloseableHttpClient httpClient;
    private PeriodoRepository periodoRepository;
    private ObjectMapper mapper;

    @FXML
    private void confirmar(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        periodoRepository = new MaestroRepository(httpClient, mapper , RestAPI.PERIODO_ENDPOINT);
        periodoRepository.remove(periodo.getId());
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setPeriodo(PeriodoModel periodo){
        this.periodo = periodo;
    }
}
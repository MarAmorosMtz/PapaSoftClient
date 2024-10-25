package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.PeriodoBase;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.time.ZoneId;
import java.util.Date;


public class AddPeriodoController {
    @FXML
    TextField txtNombre;
    @FXML
    DatePicker dateInicio;
    @FXML
    DatePicker dateFinal;


    private CloseableHttpClient httpClient;
    private PeriodoRepository periodoRepository;
    private ObjectMapper mapper;

    @FXML
    private void guardar(){
        PeriodoBase periodo = new PeriodoBase();
        periodo.setNombre(txtNombre.getText());
        periodo.setFechaInicio(Date.from(dateInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        periodo.setFechaFinal(Date.from(dateFinal.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        periodoRepository = new PeriodoRepository(httpClient, mapper , RestAPI.PERIODO_ENDPOINT);
        periodoRepository.save(periodo);
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)txtNombre.getScene().getWindow();
        stage.close();
    }

}

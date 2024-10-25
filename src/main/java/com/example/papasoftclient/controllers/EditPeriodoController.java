package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.PeriodoBase;
import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.time.ZoneId;
import java.util.Date;

public class EditPeriodoController {
    @FXML
    TextField txtNombre;
    @FXML
    DatePicker dateInicio;
    @FXML
    DatePicker dateFinal;
    @FXML
    Button close;

    PeriodoBase periodoBase;
    PeriodoModel periodoModel;

    private CloseableHttpClient httpClient;
    private PeriodoRepository periodoRepository;
    private ObjectMapper mapper;

    public void initialize(){
        //textField.setText(carreraBase.getNombre());
    }

    @FXML
    private void guardar(){
        PeriodoBase periodoUpdated = new PeriodoBase();
        periodoUpdated.setNombre(txtNombre.getText());
        periodoUpdated.setFechaInicio(Date.from(dateInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        periodoUpdated.setFechaFinal(Date.from(dateFinal.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        periodoRepository = new PeriodoRepository(httpClient, mapper , RestAPI.PERIODO_ENDPOINT);
        periodoRepository.update(periodoModel.getId(), periodoUpdated);
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setBase(PeriodoBase base){
        this.periodoBase = base;
    }

    public void setModel(PeriodoModel model){
        this.periodoModel = model;
    }
}

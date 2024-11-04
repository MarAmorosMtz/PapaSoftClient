package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.PeriodoBase;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.utils.Observable;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.time.ZoneId;
import java.util.Date;


public class AddPeriodoController extends Observable {
    @FXML
    TextField txtNombre;
    @FXML
    DatePicker dateInicio;
    @FXML
    DatePicker dateFinal;

    private PeriodoRepository periodoRepository;


    @FXML
    private void guardar(){
        PeriodoBase periodo = new PeriodoBase();
        periodo.setNombre(txtNombre.getText());
        periodo.setFecha_inicio(dateInicio.getValue());
        periodo.setFecha_fin(dateFinal.getValue());
        periodoRepository = new PeriodoRepository();
        periodoRepository.save(periodo);
        this.notificar();
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)txtNombre.getScene().getWindow();
        stage.close();
    }

}

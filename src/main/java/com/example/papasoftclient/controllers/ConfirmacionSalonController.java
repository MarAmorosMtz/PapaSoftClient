package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.models.SalonModel;
import com.example.papasoftclient.repositories.MateriaRepository;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.repositories.SalonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class ConfirmacionSalonController {
    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    private SalonModel salon;

    private SalonRepository salonRepository;

    @FXML
    private void confirmar(){
        salonRepository = new SalonRepository();
        salonRepository.remove(salon.getId());
        cancelar();
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
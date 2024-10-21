package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class EditCarreraController {
    @FXML
    TextField textField;
    @FXML
    Button close;

    CarreraBase carreraBase;
    CarreraModel carreraModel;

    private CloseableHttpClient httpClient;
    private CarreraRepository carreraRepository;
    private ObjectMapper mapper;

    public void initialize(){
        //textField.setText(carreraBase.getNombre());
    }

    @FXML
    private void guardar(){
        CarreraBase carreraUpdated = new CarreraBase(textField.getText());
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        carreraRepository = new CarreraRepository(httpClient, mapper , RestAPI.CARRERAS_ENDPOINT);
        carreraRepository.update(carreraModel.getId(), carreraUpdated);
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setBase(CarreraBase base){
        this.carreraBase = base;
    }

    public void setModel(CarreraModel model){
        this.carreraModel = model;
    }
}

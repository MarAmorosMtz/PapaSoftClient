package com.example.papasoftclient.controllers;


import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;


public class AddCarreraController {
    @FXML
    TextField txtNombre;

    private CloseableHttpClient httpClient;
    private CarreraRepository carreraRepository;
    private ObjectMapper mapper;

    @FXML
    private void guardar(){
        CarreraBase carrera = new CarreraBase(txtNombre.getText());
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        carreraRepository = new CarreraRepository(httpClient, mapper , RestAPI.CARRERAS_ENDPOINT);
        carreraRepository.save(carrera);
    }

    @FXML
    private void cancelar(){
    }

}

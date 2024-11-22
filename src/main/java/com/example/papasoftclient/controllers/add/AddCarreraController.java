package com.example.papasoftclient.controllers.add;


import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.Observable;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;


public class AddCarreraController extends Observable {
    @FXML
    private TextField carrera;


    private CarreraRepository carreraRepository;

    public AddCarreraController() {
        carreraRepository = new CarreraRepository();
    }

    @FXML
    private void guardar(){
        CarreraBase nuevaCarrera = new CarreraBase(carrera.getText());
        carreraRepository.save(nuevaCarrera);
        cancelar();
        this.notificar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)carrera.getScene().getWindow();
        stage.close();
    }

}

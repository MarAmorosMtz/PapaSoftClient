package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.MaestroBase;
import com.example.papasoftclient.models.MaestroModel;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.MaestroRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class EditMaestroController {
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtApellidoP;
    @FXML
    TextField txtApellidoM;
    @FXML
    Button close;

    MaestroBase maestroBase;
    MaestroModel maestroModel;

    private CloseableHttpClient httpClient;
    private MaestroRepository maestroRepository;
    private ObjectMapper mapper;

    public void initialize(){
        //textField.setText(carreraBase.getNombre());
    }

    @FXML
    private void guardar(){
        MaestroBase maestroUpdated = new MaestroBase();
        maestroUpdated.setNombre(txtNombre.getText());
        maestroUpdated.setApellido_p(txtApellidoP.getText());
        maestroUpdated.setApellido_m(txtApellidoM.getText());
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        maestroRepository = new MaestroRepository(httpClient, mapper , RestAPI.MAESTROS_ENDPOINT);
        maestroRepository.update(maestroModel.getId(), maestroUpdated);
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setBase(MaestroBase base){
        this.maestroBase = base;
    }

    public void setModel(MaestroModel model){
        this.maestroModel = model;
    }
}

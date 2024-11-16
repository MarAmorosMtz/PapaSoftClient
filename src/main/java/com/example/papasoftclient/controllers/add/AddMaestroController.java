package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.MaestroBase;
import com.example.papasoftclient.repositories.MaestroRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;


public class AddMaestroController {
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtApellidoP;
    @FXML
    TextField txtApellidoM;


    private CloseableHttpClient httpClient;
    private MaestroRepository maestroRepository;
    private ObjectMapper mapper;

    @FXML
    private void guardar(){
        MaestroBase maestro = new MaestroBase();
        maestro.setNombre(txtNombre.getText());
        maestro.setApellido_p(txtApellidoP.getText());
        maestro.setApellido_m(txtApellidoM.getText());
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        maestroRepository = new MaestroRepository(httpClient, mapper , RestAPI.MAESTROS_ENDPOINT);
        maestroRepository.save(maestro);
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)txtNombre.getScene().getWindow();
        stage.close();
    }

}

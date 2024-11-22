package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.MaestroBase;
import com.example.papasoftclient.models.MaestroModel;
import com.example.papasoftclient.repositories.MaestroRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.Observable;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class EditMaestroController extends Observable {
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtApellidoP;
    @FXML
    TextField txtApellidoM;
    @FXML
    Button close;

    private MaestroModel maestroModel;

    private MaestroRepository maestroRepository;

    public EditMaestroController() {
        this.maestroRepository = new MaestroRepository();
    }

    public void initialize(){}

    @FXML
    private void guardar(){
        MaestroBase maestroUpdated = new MaestroBase();
        maestroUpdated.setNombre(txtNombre.getText());
        maestroUpdated.setApellido_p(txtApellidoP.getText());
        maestroUpdated.setApellido_m(txtApellidoM.getText());
        maestroRepository = new MaestroRepository();
        maestroRepository.update(maestroModel.getId(), maestroUpdated);
        cancelar();
        this.notificar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setModel(MaestroModel model){
        this.maestroModel = maestroRepository.search(model.getId());
        this.txtNombre.setText(maestroModel.getNombre());
        this.txtApellidoP.setText(maestroModel.getApellido_p());
        this.txtApellidoM.setText(maestroModel.getApellido_m());
    }
}

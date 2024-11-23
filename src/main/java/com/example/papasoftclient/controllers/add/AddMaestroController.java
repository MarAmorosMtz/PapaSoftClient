package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.MaestroBase;
import com.example.papasoftclient.repositories.MaestroRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.Observable;
import com.example.papasoftclient.utils.Validate;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;


public class AddMaestroController extends Observable {
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtApellidoP;
    @FXML
    TextField txtApellidoM;

    private MaestroRepository maestroRepository;

    public AddMaestroController() {
        maestroRepository = new MaestroRepository();
    }

    @FXML
    private void guardar(){
        int err = 0;

        if(Validate.name(txtNombre.getText())){ txtNombre.getStyleClass().remove("error"); }
        else{ txtNombre.getStyleClass().add("error"); err++; }

        if(Validate.lastName(txtApellidoP.getText())){ txtApellidoP.getStyleClass().remove("error"); }
        else{ txtApellidoP.getStyleClass().add("error"); err++; }

        if(Validate.lastName(txtApellidoM.getText())){ txtApellidoM.getStyleClass().remove("error"); }
        else{ txtApellidoM.getStyleClass().add("error"); err++; }

        if(err == 0){
            MaestroBase maestro = new MaestroBase();
            maestro.setNombre(txtNombre.getText());
            maestro.setApellido_p(txtApellidoP.getText());
            maestro.setApellido_m(txtApellidoM.getText());
            maestroRepository.save(maestro);
            cancelar();
            this.notificar();
        }
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)txtNombre.getScene().getWindow();
        stage.close();
    }

}

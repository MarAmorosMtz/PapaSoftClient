package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.MaestroBase;
import com.example.papasoftclient.models.MaestroModel;
import com.example.papasoftclient.repositories.MaestroRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.Observable;
import com.example.papasoftclient.utils.Validate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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
        int err = 0;

        if(Validate.name(txtNombre.getText())){ txtNombre.getStyleClass().remove("error"); }
        else{ txtNombre.getStyleClass().add("error"); err++; }

        if(Validate.lastName(txtApellidoP.getText())){ txtApellidoP.getStyleClass().remove("error"); }
        else{ txtApellidoP.getStyleClass().add("error"); err++; }

        if(Validate.lastName(txtApellidoM.getText())){ txtApellidoM.getStyleClass().remove("error"); }
        else{ txtApellidoM.getStyleClass().add("error"); err++; }

        if(err == 0){
            MaestroBase maestroUpdated = new MaestroBase();
            maestroUpdated.setNombre(txtNombre.getText());
            maestroUpdated.setApellido_p(txtApellidoP.getText());
            maestroUpdated.setApellido_m(txtApellidoM.getText());
            maestroRepository = new MaestroRepository();
            maestroRepository.update(maestroModel.getId(), maestroUpdated);
            cancelar();
            this.notificar();
        }

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

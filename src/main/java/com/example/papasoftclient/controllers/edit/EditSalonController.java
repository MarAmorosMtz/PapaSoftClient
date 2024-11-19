package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.SalonBase;
import com.example.papasoftclient.models.SalonModel;
import com.example.papasoftclient.repositories.SalonRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditSalonController extends Observable {

    @FXML
    TextField nombreSalon;
    @FXML
    Button btnGuardar;
    @FXML
    Button btnCancelar;

    SalonModel salonModel;
    private SalonRepository salonRepository;

    public void initialize(){
        salonRepository = new SalonRepository();
    }

    @FXML
    private void guardar(){
        SalonBase salon = new SalonBase();
        salon.setNombre(nombreSalon.getText());
        salonRepository.update(salonModel.getId(), salon);
        cancelar();
        this.notificar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }


    public void setModel(SalonModel model){
        this.salonModel = model;
        nombreSalon.setText(this.salonModel.getNombre());
    }
}

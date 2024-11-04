package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.MateriaBase;
import com.example.papasoftclient.models.SalonBase;
import com.example.papasoftclient.repositories.SalonRepository;

import com.example.papasoftclient.utils.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSalonController extends Observable {

    @FXML
    TextField nombreSalon;
    @FXML
    Button btnGuardar;
    @FXML
    Button btnCancelar;


    private SalonRepository salonRepository;
    private ObservableList<CarreraModel> catalogoCarreras;

    public void initialize(){
        salonRepository  = new SalonRepository();
    }

    @FXML
    private void guardar(){
        SalonBase nuevoSalon = new SalonBase(nombreSalon.getText());
        salonRepository.save(nuevoSalon);
        this.notificar();
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }
}

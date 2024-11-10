package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.PeriodoBase;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddPeriodoController extends Observable {
    @FXML
    TextField txtNombre;
    @FXML
    DatePicker dateInicio;
    @FXML
    DatePicker dateFinal;

    private PeriodoRepository periodoRepository;


    @FXML
    private void guardar(){
        PeriodoBase periodo = new PeriodoBase();
        periodo.setNombre(txtNombre.getText());
        periodo.setFecha_inicio(dateInicio.getValue());
        periodo.setFecha_fin(dateFinal.getValue());
        periodoRepository = new PeriodoRepository();
        periodoRepository.save(periodo);
        this.notificar();
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)txtNombre.getScene().getWindow();
        stage.close();
    }

}

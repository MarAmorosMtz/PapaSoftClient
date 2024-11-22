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
    TextField nombre;
    @FXML
    DatePicker fechaInicio;
    @FXML
    DatePicker fechaFin;

    private PeriodoRepository periodoRepository;

    public AddPeriodoController() {
        periodoRepository = new PeriodoRepository();
    }

    @FXML
    private void guardar(){
        PeriodoBase periodo = new PeriodoBase();
        periodo.setNombre(nombre.getText());
        periodo.setFecha_inicio(fechaInicio.getValue());
        periodo.setFecha_fin(fechaFin.getValue());
        periodoRepository.save(periodo);
        this.notificar();
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage) nombre.getScene().getWindow();
        stage.close();
    }

}

package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.PeriodoBase;
import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.repositories.PeriodoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPeriodoController {
    @FXML
    TextField txtNombre;
    @FXML
    DatePicker dateInicio;
    @FXML
    DatePicker dateFinal;
    @FXML
    Button close;

    PeriodoBase periodoBase;
    PeriodoModel periodoModel;

    private PeriodoRepository periodoRepository;

    public void initialize(){

    }

    @FXML
    private void guardar(){
        PeriodoBase periodoUpdated = new PeriodoBase();
        periodoUpdated.setNombre(txtNombre.getText());
        periodoUpdated.setFecha_inicio(dateInicio.getValue());
        periodoUpdated.setFecha_fin(dateFinal.getValue());
        periodoRepository = new PeriodoRepository();
        periodoRepository.update(periodoModel.getId(), periodoUpdated);
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setBase(PeriodoBase base){
        this.periodoBase = base;
        txtNombre.setText(periodoBase.getNombre());
        dateInicio.setValue(periodoBase.getFecha_inicio());
        dateFinal.setValue(periodoBase.getFecha_fin());
    }

    public void setModel(PeriodoModel model){
        this.periodoModel = model;
    }
}

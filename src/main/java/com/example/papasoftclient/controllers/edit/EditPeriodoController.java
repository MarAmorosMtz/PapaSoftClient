package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.PeriodoBase;
import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPeriodoController extends Observable {
    @FXML
    private TextField nombre;
    @FXML
    private DatePicker fechaInicio;
    @FXML
    private DatePicker fechaFin;
    @FXML
    private Button close;

    private PeriodoModel periodoModel;

    private PeriodoRepository periodoRepository;

    public EditPeriodoController() {
        periodoRepository = new PeriodoRepository();
    }

    public void initialize(){

    }

    @FXML
    private void guardar(){
        PeriodoBase periodoUpdated = new PeriodoBase();
        periodoUpdated.setNombre(nombre.getText());
        periodoUpdated.setFecha_inicio(fechaInicio.getValue());
        periodoUpdated.setFecha_fin(fechaFin.getValue());
        periodoRepository.update(periodoModel.getId(), periodoUpdated);
        cancelar();
        this.notificar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setModel(PeriodoModel model){
        this.periodoModel = periodoRepository.search(model.getId());
        nombre.setText(this.periodoModel.getNombre());
        fechaInicio.setValue(this.periodoModel.getFecha_inicio());
        fechaFin.setValue(this.periodoModel.getFecha_fin());
    }
}

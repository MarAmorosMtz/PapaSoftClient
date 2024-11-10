package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.models.PeriodoBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public class ReporteController {
    @FXML
    RadioButton radioCarrera;
    @FXML
    RadioButton radioAsesor;
    @FXML
    RadioButton radioMensual;
    @FXML
    RadioButton radioSemestral;
    @FXML
    RadioButton radioTotal;
    @FXML
    ComboBox<PeriodoBase> comboPeriodo;
    @FXML
    ComboBox<String> comboAño;
    @FXML
    ComboBox<String> comboMes;
    @FXML
    Button botonHecho;

    @FXML
    public void initialize(){
        comboPeriodo.setDisable(true);
        comboAño.setDisable(true);
        comboMes.setDisable(true);
    }

    @FXML
    private void handleRadioAction(){
        comboPeriodo.setDisable(false);
        comboAño.setDisable(false);
        comboMes.setDisable(false);
        if(radioTotal.isSelected()){
            comboPeriodo.setDisable(true);
            comboAño.setDisable(true);
            comboMes.setDisable(true);
        } else if (radioMensual.isSelected()) {
            comboPeriodo.setDisable(true);
        } else if (radioSemestral.isSelected()) {
            comboMes.setDisable(true);
            comboAño.setDisable(true);
        }else{
            comboPeriodo.setDisable(true);
            comboAño.setDisable(true);
            comboMes.setDisable(true);
        }
    }

    @FXML
    private void generarReporte(){
    }
}

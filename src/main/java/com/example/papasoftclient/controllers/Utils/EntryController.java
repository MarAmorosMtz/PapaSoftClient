package com.example.papasoftclient.controllers.Utils;

import com.example.papasoftclient.models.AsesorModel;
import com.example.papasoftclient.utils.ClickListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.awt.event.MouseEvent;

public class EntryController {
    @FXML
    private VBox vbox;

    @FXML
    private Label activo;

    @FXML
    private Label nombre;

    @FXML
    private Label numerocontrol;

    @FXML
    private Label semestre;

    private ClickListener listener;
    private AsesorModel asesor;

    public void click(javafx.scene.input.MouseEvent mouseEvent) {
        listener.onClickListener(asesor);
    }

    public void setData(AsesorModel asesor, ClickListener listener){
        this.asesor = asesor;
        this.listener = listener;
        String state;
        if(asesor.getActivo()){
            state = "Activo";
        }else{
            state = "Inactivo";
        }
        nombre.setText(asesor.toString());
        numerocontrol.setText(asesor.getNum_ctrl());
        semestre.setText("Semestre: " + asesor.getSemestre());
        activo.setText("Estado: "+state);
    }

}

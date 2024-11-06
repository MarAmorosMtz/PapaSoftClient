package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.AsesoradoModel;
import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.repositories.AsesoradoRepository;
import com.example.papasoftclient.repositories.MateriaRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmacionAsesoradoController {
    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    private AsesoradoModel asesorado;

    private AsesoradoRepository asesoradoRepository;

    @FXML
    private void confirmar(){
        asesoradoRepository = new AsesoradoRepository();
        asesoradoRepository.remove(asesorado.getId());
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void setAsesorado(AsesoradoModel asesoradoModel){
        this.asesorado = asesoradoModel;
    }
}

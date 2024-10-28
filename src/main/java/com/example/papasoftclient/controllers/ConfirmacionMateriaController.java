package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.repositories.MateriaRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ConfirmacionMateriaController extends Observable {
    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    private MateriaModel materia;

    private MateriaRepository materiaRepository;

    @FXML
    private void confirmar(){
        materiaRepository = new MateriaRepository();
        materiaRepository.remove(materia.getId());
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void setMateria(MateriaModel materiaModel){
        this.materia = materiaModel;
    }
}
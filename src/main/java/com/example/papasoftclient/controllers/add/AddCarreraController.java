package com.example.papasoftclient.controllers.add;


import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.utils.Observable;
import com.example.papasoftclient.utils.Validate;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddCarreraController extends Observable {
    @FXML
    private TextField carrera;


    private CarreraRepository carreraRepository;

    public AddCarreraController() {
        carreraRepository = new CarreraRepository();
    }

    @FXML
    private void guardar(){
        int err = 0;

        if(Validate.name(carrera.getText())){ carrera.getStyleClass().remove("error"); }
        else{ carrera.getStyleClass().add("error"); err++; }

        if(err == 0){
            CarreraBase nuevaCarrera = new CarreraBase(carrera.getText());
            carreraRepository.save(nuevaCarrera);
            cancelar();
            this.notificar();
        }
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)carrera.getScene().getWindow();
        stage.close();
    }

}

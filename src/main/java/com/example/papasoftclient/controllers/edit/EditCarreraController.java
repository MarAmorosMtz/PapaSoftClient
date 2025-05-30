package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.utils.Observable;
import com.example.papasoftclient.utils.Validate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCarreraController extends Observable {
    @FXML
    TextField carrera;
    @FXML
    Button close;

    private CarreraModel carreraModel;

    private CarreraRepository carreraRepository;

    public EditCarreraController() {

        carreraRepository = new CarreraRepository();
    }

    public void initialize(){   }

    @FXML
    private void guardar(){
        int err = 0;

        if(Validate.word(carrera.getText())){ carrera.getStyleClass().remove("error"); }
        else{ carrera.getStyleClass().add("error"); err++; }

        if(err == 0){
            CarreraBase carreraUpdated = new CarreraBase(carrera.getText());
            carreraRepository.update(carreraModel.getId(), carreraUpdated);
            cancelar();
            this.notificar();
        }

    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setModel(CarreraModel model){
        this.carreraModel = carreraRepository.search(model.getId());
        this.carrera.setText(carreraModel.getNombre());
    }
}

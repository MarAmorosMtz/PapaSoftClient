package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.MateriaBase;
import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.MateriaRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.HttpClient;
import com.example.papasoftclient.utils.JsonMapper;
import com.example.papasoftclient.utils.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditMateriaController extends Observable {
    @FXML
    TextField nombre;
    @FXML
    ComboBox<CarreraModel> carrera;
    @FXML
    Button guardarButton;
    @FXML
    Button cancelarButton;

    MateriaModel materiaBase;
    MateriaModel materiaModel;

    private MateriaRepository materiaRepository;
    private CarreraRepository carreraRepository;
    private ObservableList<CarreraModel> catalogoCarreras;

    public void initialize(){
        materiaRepository = new MateriaRepository();
        carreraRepository = new CarreraRepository(HttpClient.getClient(), JsonMapper.getMapper(), RestAPI.CARRERAS_ENDPOINT);
        catalogoCarreras = carreraRepository.getCatalogoCarreras();
        carrera.setItems(catalogoCarreras);
    }

    @FXML
    private void guardar(){
        MateriaBase materia = new MateriaBase();
        materia.setNombre(nombre.getText());
        materia.setCarrera(carrera.getSelectionModel().getSelectedItem().getId());
        materiaRepository.update(materiaModel.getId(), materia);
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)cancelarButton.getScene().getWindow();
        stage.close();
    }

    public void setBase(MateriaModel base){
        this.materiaBase = base;
    }

    public void setModel(MateriaModel model){
        this.materiaModel = model;
        nombre.setText(this.materiaModel.getNombre());
        carrera.setValue(this.materiaModel.getCarrera());
    }
}
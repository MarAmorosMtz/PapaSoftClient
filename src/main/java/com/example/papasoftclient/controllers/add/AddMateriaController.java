package com.example.papasoftclient.controllers.add;


import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.MateriaBase;
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

public class AddMateriaController extends Observable {
    @FXML
    TextField nombre;
    @FXML
    ComboBox<CarreraModel> carrera;
    @FXML
    Button guardarButton;
    @FXML
    Button cancelarButton;


    private MateriaRepository materiaRepository;
    private CarreraRepository carreraRepository;
    private ObservableList<CarreraModel> catalogoCarreras;

    public void initialize(){
        carreraRepository = new CarreraRepository();
        materiaRepository  = new MateriaRepository();
        catalogoCarreras = carreraRepository.getCatalogoCarreras();
        carrera.setItems(catalogoCarreras);
    }

    @FXML
    private void guardar(){
        MateriaBase nuevaMateria = new MateriaBase(nombre.getText(),carrera.getSelectionModel().getSelectedItem().getId());
        materiaRepository.save(nuevaMateria);
        this.notificar();
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)cancelarButton.getScene().getWindow();
        stage.close();
    }

}

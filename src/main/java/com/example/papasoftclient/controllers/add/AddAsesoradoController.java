package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.AsesoradoBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.repositories.AsesoradoRepository;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.HttpClient;
import com.example.papasoftclient.utils.JsonMapper;
import com.example.papasoftclient.utils.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddAsesoradoController extends Observable {
    @FXML
    TextField numCtrl;
    @FXML
    TextField nombre;
    @FXML
    TextField apellidoP;
    @FXML
    TextField apellidoM;
    @FXML
    TextField correo;
    @FXML
    TextField telefono;
    @FXML
    DatePicker fechaInscripcion;
    @FXML
    ComboBox<CarreraModel> carrera;
    @FXML
    Spinner<Integer> semestre;
    @FXML
    Button guardarBtn;
    @FXML
    Button cancelarBtn;


    private AsesoradoRepository asesoradoRepository;
    private CarreraRepository carreraRepository;
    private ObservableList<CarreraModel> catalogoCarreras;

    public void initialize(){
        inicializarSpinner();
        carreraRepository = new CarreraRepository(HttpClient.getClient(), JsonMapper.getMapper(), RestAPI.CARRERAS_ENDPOINT);
        asesoradoRepository  = new AsesoradoRepository();
        catalogoCarreras = carreraRepository.getCatalogoCarreras();
        carrera.setItems(catalogoCarreras);
    }

    @FXML
    private void guardar(){
        AsesoradoBase nuevoAsesorado = new AsesoradoBase(
                nombre.getText(),
                apellidoP.getText(),
                apellidoM.getText(),
                numCtrl.getText(),
                correo.getText(),
                telefono.getText(),
                fechaInscripcion.getValue(),
                semestre.getValue(),
                carrera.getSelectionModel().getSelectedItem().getId(),
                "");
        asesoradoRepository.save(nuevoAsesorado);
        this.notificar();
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)cancelarBtn.getScene().getWindow();
        stage.close();
    }

    private void inicializarSpinner(){
        semestre.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,1));
        semestre.setEditable(false);
    }
}
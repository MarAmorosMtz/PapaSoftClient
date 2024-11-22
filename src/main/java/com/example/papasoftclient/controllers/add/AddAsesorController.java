package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.AsesorBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.HttpClient;
import com.example.papasoftclient.utils.JsonMapper;
import com.example.papasoftclient.utils.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddAsesorController extends Observable {

    @FXML
    TextField txtNControl, txtNombre, txtApellidoP, txtApellidoM, txtCorreo, txtTelefono;

    @FXML
    DatePicker date;

    @FXML
    Spinner<Integer> spnSemestre;

    @FXML
    ComboBox<CarreraModel> comboCarrera;

    @FXML
    Button cancelarButton;

    private AsesorRepository asesorRepository;
    private CarreraRepository carreraRepository;
    private ObservableList<CarreraModel> catalogoCarreras;

    public void initialize(){
        carreraRepository = new CarreraRepository();
        asesorRepository = new AsesorRepository();
        catalogoCarreras = carreraRepository.getCatalogoCarreras();
        comboCarrera.setItems(catalogoCarreras);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1, 1);
        spnSemestre.setValueFactory(valueFactory);
        spnSemestre.setEditable(false);
    }

    @FXML
    private void guardar(){
        AsesorBase asesor = new AsesorBase();

        asesor.setNum_ctrl(txtNControl.getText());
        asesor.setNombre(txtNombre.getText());
        asesor.setApellido_p(txtApellidoP.getText());
        asesor.setApellido_m(txtApellidoM.getText());
        asesor.setCorreo(txtCorreo.getText());
        asesor.setTelefono(txtTelefono.getText());
        asesor.setContrato("");
        asesor.setFoto("");

        asesor.setFecha_inscripcion(date.getValue());
        asesor.setSemestre(spnSemestre.getValue());
        asesor.setCarrera(comboCarrera.getSelectionModel().getSelectedItem().getId());

        asesor.setFoto("/media");

        asesorRepository.save(asesor);
        this.notificar();
        cancelar();

    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)cancelarButton.getScene().getWindow();
        stage.close();
    }
}

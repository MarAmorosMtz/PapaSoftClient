package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.repositories.AsesoradoRepository;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.HttpClient;
import com.example.papasoftclient.utils.JsonMapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditAsesorController {

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


    private AsesorRepository asesorRepository;
    private CarreraRepository carreraRepository;
    private ObservableList<CarreraModel> catalogoCarreras;
    private AsesorModel asesor;

    public void initialize(){
        inicializarSpinner();
        carreraRepository = new CarreraRepository(HttpClient.getClient(), JsonMapper.getMapper(), RestAPI.CARRERAS_ENDPOINT);
        asesorRepository = new AsesorRepository();
        catalogoCarreras = carreraRepository.getCatalogoCarreras();
        carrera.setItems(catalogoCarreras);
    }

    @FXML
    private void guardar(){
        AsesorBase nuevoAsesor = new AsesorBase(
                nombre.getText(),
                apellidoP.getText(),
                apellidoM.getText(),
                numCtrl.getText(),
                correo.getText(),
                telefono.getText(),
                fechaInscripcion.getValue(),
                semestre.getValue(),
                "",
                "",
                carrera.getSelectionModel().getSelectedItem().getId()
        );
        asesorRepository.update(asesor.getId(),nuevoAsesor);
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

    public void setModel(AsesorModel model){
        this.asesor = asesorRepository.search(model.getId());
        numCtrl.setText(this.asesor.getNum_ctrl());
        nombre.setText(this.asesor.getNombre());
        apellidoP.setText(this.asesor.getApellido_p());
        apellidoM.setText(this.asesor.getApellido_m());
        telefono.setText(this.asesor.getTelefono());
        correo.setText(this.asesor.getCorreo());
        fechaInscripcion.setValue(model.getFecha_inscripcion());
        semestre.getValueFactory().setValue(model.getSemestre());
        carrera.setValue(this.asesor.getCarrera());

    }
}

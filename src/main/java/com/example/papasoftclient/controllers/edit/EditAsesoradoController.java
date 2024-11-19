package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.AsesoradoBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.AsesoradoModel;
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

public class EditAsesoradoController extends Observable {
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
    private AsesoradoModel asesorado;

    public EditAsesoradoController() {
        carreraRepository = new CarreraRepository();
        asesoradoRepository  = new AsesoradoRepository();
    }

    public void initialize(){
        inicializarSpinner();
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
        asesoradoRepository.update(asesorado.getId(),nuevoAsesorado);
        cancelar();
        this.notificar();
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

    public void setModel(AsesoradoModel model){
        System.out.println("Objeto recibido:");
        System.out.println(model);
        this.asesorado = asesoradoRepository.search(model.getId());
        System.out.println("Objeto consultado:");
        System.out.println(this.asesorado);
        numCtrl.setText(this.asesorado.getNum_ctrl());
        nombre.setText(this.asesorado.getNombre());
        apellidoP.setText(this.asesorado.getApellido_p());
        apellidoM.setText(this.asesorado.getApellido_m());
        telefono.setText(this.asesorado.getTelefono());
        correo.setText(this.asesorado.getCorreo());
        fechaInscripcion.setValue(this.asesorado.getFecha_inscripcion());
        semestre.getValueFactory().setValue(this.asesorado.getSemestre());
        carrera.setValue(this.asesorado.getCarrera());

    }
}

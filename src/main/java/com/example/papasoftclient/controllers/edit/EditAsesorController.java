package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.repositories.AsesoradoRepository;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.HttpClient;
import com.example.papasoftclient.utils.JsonMapper;
import com.example.papasoftclient.utils.Observable;
import com.example.papasoftclient.utils.Validate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Date;

public class EditAsesorController extends Observable {

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

    private File archivoSeleccionado;
    private File imagenSeleccionada;

    public void initialize(){
        inicializarSpinner();
        carreraRepository = new CarreraRepository();
        asesorRepository = new AsesorRepository();
        catalogoCarreras = carreraRepository.getCatalogoCarreras();
        carrera.setItems(catalogoCarreras);
    }

    @FXML
    private void guardar(){
        int err = 0;

        if(Validate.email(correo.getText())){ correo.getStyleClass().remove("error"); }
        else{ correo.getStyleClass().add("error"); err++; }

        if(Validate.noControl(numCtrl.getText())){ numCtrl.getStyleClass().remove("error"); }
        else{ numCtrl.getStyleClass().add("error"); err++; }

        if(Validate.name(nombre.getText())){ nombre.getStyleClass().remove("error"); }
        else{ nombre.getStyleClass().add("error"); err++; }

        if(Validate.lastName(apellidoP.getText())){ apellidoP.getStyleClass().remove("error"); }
        else{ apellidoP.getStyleClass().add("error"); err++; }

        if(Validate.lastName(apellidoM.getText())){ apellidoM.getStyleClass().remove("error"); }
        else{ apellidoM.getStyleClass().add("error"); err++; }

        if(Validate.phone(telefono.getText())){ telefono.getStyleClass().remove("error"); }
        else{ telefono.getStyleClass().add("error"); err++; }

        if(carrera.getSelectionModel().getSelectedItem() != null){ carrera.getStyleClass().remove("error"); }
        else{ carrera.getStyleClass().add("error"); err++; }

        if(archivoSeleccionado == null | imagenSeleccionada == null){

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Se ha producido un error");
            alerta.setContentText("Por favor seleccione todos los archivos");
            alerta.showAndWait();

            err++;
        }

        if(fechaInscripcion.getValue() != null){
            if(Validate.date(Date.valueOf(fechaInscripcion.getValue()))){ fechaInscripcion.getStyleClass().remove("error"); }
            else{ fechaInscripcion.getStyleClass().add("error"); err++; }
        }else{ fechaInscripcion.getStyleClass().add("error"); err++; }



        if(err == 0){
            AsesorBase nuevoAsesor = new AsesorBase(
                    nombre.getText().toUpperCase(),
                    apellidoP.getText().toUpperCase(),
                    apellidoM.getText().toUpperCase(),
                    numCtrl.getText(),
                    correo.getText(),
                    telefono.getText(),
                    fechaInscripcion.getValue(),
                    semestre.getValue(),
                    archivoSeleccionado.getAbsolutePath(),
                    imagenSeleccionada.getAbsolutePath(),
                    carrera.getSelectionModel().getSelectedItem().getId()
            );
            asesorRepository.update(asesor.getId(),nuevoAsesor);
            cancelar();
            this.notificar();
        }

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
        fechaInscripcion.setValue(this.asesor.getFecha_inscripcion());
        semestre.getValueFactory().setValue(this.asesor.getSemestre());
        carrera.setValue(this.asesor.getCarrera());

    }

    @FXML
    private void seleccionarArchivo() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar archivo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Todos los archivos", "*.pdf"));

        Stage stage = new Stage();
        archivoSeleccionado = fileChooser.showOpenDialog(stage);

    }

    @FXML
    private void seleccionarImagen() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar archivo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.jpg", "*.png"));

        Stage stage = new Stage();
        imagenSeleccionada = fileChooser.showOpenDialog(stage);

    }
}

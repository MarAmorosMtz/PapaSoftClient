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
import com.example.papasoftclient.utils.Validate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Date;

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

    //private File horario;

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

        if(fechaInscripcion.getValue() != null){
            if(Validate.date(Date.valueOf(fechaInscripcion.getValue()))){ fechaInscripcion.getStyleClass().remove("error"); }
            else{ fechaInscripcion.getStyleClass().add("error"); err++; }
        }else{ fechaInscripcion.getStyleClass().add("error"); err++; }

        /*if(horario == null){

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Se ha producido un error");
            alerta.setContentText("Por favor seleccione un horario");
            alerta.showAndWait();

            err++;
        }*/

        if(err == 0){
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
                    //horario.getAbsolutePath());
            asesoradoRepository.update(asesorado.getId(),nuevoAsesorado);
            this.notificar();
            cancelar();
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

    @FXML
    private void seleccionarHorario() {

        /*FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar archivo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));

        Stage stage = new Stage();
        horario = fileChooser.showOpenDialog(stage);*/

    }
}

package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.AsesorBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.example.papasoftclient.utils.Validate;


import java.io.File;
import java.sql.Date;

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

    @FXML
    CheckBox chkActivo;

    private boolean foto = false;

    private AsesorRepository asesorRepository;
    private CarreraRepository carreraRepository;
    private ObservableList<CarreraModel> catalogoCarreras;

    private File archivoSeleccionado;
    private File imagenSeleccionada;


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
        int err = 0;

        if(Validate.email(txtCorreo.getText())){ txtCorreo.getStyleClass().remove("error"); }
        else{ txtCorreo.getStyleClass().add("error"); err++; }

        if(Validate.noControl(txtNControl.getText())){ txtNControl.getStyleClass().remove("error"); }
        else{ txtNControl.getStyleClass().add("error"); err++; }

        if(Validate.name(txtNombre.getText())){ txtNombre.getStyleClass().remove("error"); }
        else{ txtNombre.getStyleClass().add("error"); err++; }

        if(Validate.lastName(txtApellidoP.getText())){ txtApellidoP.getStyleClass().remove("error"); }
        else{ txtApellidoP.getStyleClass().add("error"); err++; }

        if(Validate.lastName(txtApellidoM.getText())){ txtApellidoM.getStyleClass().remove("error"); }
        else{ txtApellidoM.getStyleClass().add("error"); err++; }

        if(Validate.phone(txtTelefono.getText())){ txtTelefono.getStyleClass().remove("error"); }
        else{ txtTelefono.getStyleClass().add("error"); err++; }

        if(comboCarrera.getSelectionModel().getSelectedItem() != null){ comboCarrera.getStyleClass().remove("error"); }
        else{ comboCarrera.getStyleClass().add("error"); err++; }

        if(imagenSeleccionada == null){
            foto = false;
        }else{
            foto = true;
        }

        if(date.getValue() != null){
            if(Validate.date(Date.valueOf(date.getValue()))){ date.getStyleClass().remove("error"); }
            else{ date.getStyleClass().add("error"); err++; }
        }else{ date.getStyleClass().add("error"); err++; }


        if(err == 0){
            asesor.setNum_ctrl(txtNControl.getText());
            asesor.setNombre(txtNombre.getText());
            asesor.setApellido_p(txtApellidoP.getText());
            asesor.setApellido_m(txtApellidoM.getText());
            asesor.setCorreo(txtCorreo.getText());
            asesor.setTelefono(txtTelefono.getText());
            if(foto){
                asesor.setFoto(imagenSeleccionada.getAbsolutePath());
            }else{
                asesor.setFoto("");
            }
            //asesor.setContrato(archivoSeleccionado.getAbsolutePath());
            asesor.setContrato("");
            asesor.setFecha_inscripcion(date.getValue());
            asesor.setSemestre(spnSemestre.getValue());
            asesor.setCarrera(comboCarrera.getSelectionModel().getSelectedItem().getId());
            asesor.setActivo(chkActivo.isSelected());

            asesorRepository.save(asesor);
            this.notificar();
            cancelar();
        }

    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)cancelarButton.getScene().getWindow();
        stage.close();
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

package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.AsesorMateriaModel;
import com.example.papasoftclient.repositories.AsesorMateriaRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmacionAsesorMateriaController extends Observable {
    @FXML
    Button btnCancelar;

    private AsesorMateriaModel asesorMateria;
    private AsesorMateriaRepository asesorMateriaRepository;

    public ConfirmacionAsesorMateriaController(){
        this.asesorMateriaRepository = new AsesorMateriaRepository();
    }
    public void setAsesorMateria(AsesorMateriaModel asesorMateria){
        this.asesorMateria = asesorMateria;
    }

    @FXML
    private void confirmar(){
        boolean status = asesorMateriaRepository.remove(asesorMateria.getId());
        if(!status){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Se ha producido un error");
            alerta.setContentText("No se ha podido eliminar. Compruebe si la entrada es referenciada. Compruebe la conexion");
            alerta.showAndWait();
        }
        cancelar();
        this.notificar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }
}

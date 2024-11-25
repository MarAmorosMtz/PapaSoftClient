package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.AsesorModel;
import com.example.papasoftclient.models.AsesoriaModel;
import com.example.papasoftclient.repositories.AsesoriaRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmacionAsesoriaController extends Observable {

    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    private AsesoriaModel asesoria;

    private AsesoriaRepository asesoriaRepository;

    public ConfirmacionAsesoriaController() {
        this.asesoriaRepository = new AsesoriaRepository();;
    }
    @FXML
    private void confirmar(){
        boolean status = asesoriaRepository.remove(asesoria.getId());
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

    public void setAsesoria(AsesoriaModel asesoria){
        this.asesoria = asesoria;
    }

}

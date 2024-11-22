package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.AsesoradoModel;
import com.example.papasoftclient.repositories.AsesoradoRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmacionAsesoradoController extends Observable {
    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    private AsesoradoModel asesorado;

    private AsesoradoRepository asesoradoRepository;

    public ConfirmacionAsesoradoController() {
        asesoradoRepository = new AsesoradoRepository();
    }

    @FXML
    private void confirmar(){
        boolean status = asesoradoRepository.remove(asesorado.getId());
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

    public void setAsesorado(AsesoradoModel asesoradoModel){
        this.asesorado = asesoradoModel;
    }
}

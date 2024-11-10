package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.AsesoradoModel;
import com.example.papasoftclient.repositories.AsesoradoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmacionAsesoradoController {
    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    private AsesoradoModel asesorado;

    private AsesoradoRepository asesoradoRepository;

    @FXML
    private void confirmar(){
        asesoradoRepository = new AsesoradoRepository();
        if(asesoradoRepository.remove(asesorado.getId())){
            asesoradoRepository.remove(asesorado.getId());
        }else{

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Se ha producido un error");
            alerta.setContentText("No se ha podido eliminar. Compruebe si la entrada es referenciada. Compruebe la conexion");

            alerta.showAndWait();
        }

        cancelar();
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

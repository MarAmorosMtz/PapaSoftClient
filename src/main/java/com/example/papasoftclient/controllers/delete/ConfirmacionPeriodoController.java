package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmacionPeriodoController extends Observable {
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConfirmar;

    private PeriodoModel periodo;

    private PeriodoRepository periodoRepository;

    @FXML
    private void confirmar(){
        periodoRepository = new PeriodoRepository();
        boolean status = periodoRepository.remove(periodo.getId());
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
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void setPeriodo(PeriodoModel periodo){
        this.periodo = periodo;
    }
}
package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class ConfirmacionPeriodoController {
    @FXML
    Button close;

    PeriodoModel periodo;

    private CloseableHttpClient httpClient;
    private PeriodoRepository periodoRepository;
    private ObjectMapper mapper;

    @FXML
    private void confirmar(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        periodoRepository = new PeriodoRepository();

        if(periodoRepository.remove(periodo.getId())){
            periodoRepository.remove(periodo.getId());
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
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }

    public void setPeriodo(PeriodoModel periodo){
        this.periodo = periodo;
    }
}
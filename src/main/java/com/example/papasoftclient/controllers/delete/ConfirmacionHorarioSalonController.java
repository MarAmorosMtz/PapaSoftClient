package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.HorarioAsesorModel;
import com.example.papasoftclient.models.HorarioModel;
import com.example.papasoftclient.models.HorarioSalonModel;
import com.example.papasoftclient.repositories.HorariosAsesorRepository;
import com.example.papasoftclient.repositories.HorariosSalonRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ConfirmacionHorarioSalonController extends Observable {

    @FXML
    private ComboBox<String> horaLibre;
    @FXML
    private ComboBox<String> diaLibre;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;

    private HorarioSalonModel horarioModel;
    private HorariosSalonRepository horariosSalonRepository;

    public ConfirmacionHorarioSalonController() {
        this.horariosSalonRepository = new HorariosSalonRepository();
    }

    @FXML
    public void confirmar(){
        boolean status = this.horariosSalonRepository.remove(this.horarioModel.getId());
        if(!status){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Se ha producido un error");
            alerta.setContentText("No se ha podido eliminar. Compruebe si la entrada es referenciada. Compruebe la conexion");
            alerta.showAndWait();
            return;
        }
        this.cancelar();
        this.notificar();
    }

    @FXML
    public void cancelar(){
        Stage ventana = (Stage)this.btnCancelar.getScene().getWindow();
        ventana.close();
    }

    public void setHorario(HorarioModel horario) {
        this.horarioModel = this.horariosSalonRepository.get(horario.getId());
    }
}

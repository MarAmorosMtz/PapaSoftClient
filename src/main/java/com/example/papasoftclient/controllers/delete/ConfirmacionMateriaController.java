package com.example.papasoftclient.controllers.delete;

import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.repositories.MateriaRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ConfirmacionMateriaController extends Observable {
    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;

    private MateriaModel materia;

    private MateriaRepository materiaRepository;

    @FXML
    private void confirmar(){
        materiaRepository = new MateriaRepository();
        if(materiaRepository.remove(materia.getId())){
            materiaRepository.remove(materia.getId());
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

    public void setMateria(MateriaModel materiaModel){
        this.materia = materiaModel;
    }
}
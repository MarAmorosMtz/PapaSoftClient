package com.example.papasoftclient.controllers.delete;
import com.example.papasoftclient.models.UsuarioModel;
import com.example.papasoftclient.repositories.UsuarioRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmacionUsuarioController extends Observable{
    @FXML
    private Button btnCancelar;

    private UsuarioModel usuario;

    private UsuarioRepository usuarioRepository;

    public ConfirmacionUsuarioController(){ usuarioRepository = new UsuarioRepository(); }

    @FXML
    private void confirmar(){
        boolean status = usuarioRepository.remove(usuario.getNombre());;
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

    public void setUsuario(UsuarioModel usuario){ this.usuario = usuario;}
}

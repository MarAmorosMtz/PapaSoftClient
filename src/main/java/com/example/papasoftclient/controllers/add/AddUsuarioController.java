package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.UsuarioModel;
import com.example.papasoftclient.repositories.UsuarioRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUsuarioController extends Observable {
    @FXML
    TextField txtNombre;
    @FXML
    PasswordField passwordField;
    @FXML
    ComboBox<String> comboCargo;

    private UsuarioRepository usuariorepository;

    public AddUsuarioController(){ usuariorepository = new UsuarioRepository(); }

    @FXML
    public void initialize(){
        comboCargo.getItems().addAll(
                "Administrador",
                "Secretario"
        );
    }

    @FXML
    private void guardar(){
        //Agregar validacion a los campos, puto lalo
        if(comboCargo.getSelectionModel().getSelectedItem() != null){ comboCargo.getStyleClass().remove("error"); }
        else{ comboCargo.getStyleClass().add("error"); }

        int tipo = (comboCargo.getValue().equals("Administrador")) ? 0:1;

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNombre(txtNombre.getText());
        usuario.setClave(passwordField.getText());
        usuario.setTipo(tipo);
        usuariorepository.save(usuario);
        cancelar();
        this.notificar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)txtNombre.getScene().getWindow();
        stage.close();
    }
}

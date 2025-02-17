package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.models.LoginModel;
import com.example.papasoftclient.repositories.LoginRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    Button btn;
    @FXML
    PasswordField pass;
    @FXML
    HBox bottomHBox;
    @FXML
    ImageView imageView;
    @FXML
    TabPane tabLogin;
    @FXML
    TextField usuarioSecretario;
    @FXML
    PasswordField claveSecretario;
    @FXML
    TextField usuarioCoordinador;
    @FXML
    PasswordField claveCoordinador;

    LoginRepository loginRepository;

    public LoginController(){
        this.loginRepository = new LoginRepository();
    }

    @FXML
    private void initialize(){
        bottomHBox.widthProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setFitWidth(newVal.doubleValue());
        });

        bottomHBox.heightProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setFitHeight(newVal.doubleValue());
        });

    }

    @FXML
    private void handleButtonAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        Stage stg = (Stage) btn.getScene().getWindow();
        stg.close();
        stage.show();
    }

    @FXML
    private void entrar() throws IOException {
        FXMLLoader vista;
        FXMLLoader vistaAdministrador = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/Main/dashboardAdministrador.fxml"));
        FXMLLoader vistaSecretario = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/Main/dashboardSecretario.fxml"));
        int tipoUsuario = tabLogin.getSelectionModel().getSelectedIndex();
        String nombreUsuario="",clave="";
        if(tipoUsuario == 0){
            nombreUsuario = usuarioCoordinador.getText();
            clave = claveCoordinador.getText();
            vista = vistaAdministrador;
        }else {
            nombreUsuario = usuarioSecretario.getText();
            clave = claveSecretario.getText();
            vista = vistaSecretario;
        }
        LoginModel loginModel = new LoginModel(nombreUsuario,clave,tipoUsuario);
        boolean usuarioValido = loginRepository.login(loginModel);

        usuarioValido = true;

        if(usuarioValido){
            Scene scene = new Scene(vista.load());
            Stage stage = new Stage();
            stage.setTitle("PapaSoft");
            stage.setScene(scene);
            stage.setMaximized(true);
            Stage cerrar = (Stage)usuarioCoordinador.getScene().getWindow();
            cerrar.close();
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Usuario o contraseña incorrectos.");
            alert.showAndWait();
        }



    }
}

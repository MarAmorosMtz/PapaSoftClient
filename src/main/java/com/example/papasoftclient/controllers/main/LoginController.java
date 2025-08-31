package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.models.LoginModel;
import com.example.papasoftclient.repositories.LoginRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private void teclaPresionada(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            this.entrar();
        }
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
        int statusCode = loginRepository.login(loginModel);

        if(statusCode==200){
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

            alert.setHeaderText(null);
            if(statusCode == 400){
                alert.setTitle("Error al iniciar sesión");
                alert.setContentText("Usuario o contraseña no válidos. Verifique que no estén vacíos.");
            }else if(statusCode == 401){
                alert.setTitle("Error de autenticación");
                alert.setContentText("Usuario o contraseña incorrectos.");
            }else if(statusCode == 404){
                alert.setTitle("Error de conexión");
                alert.setContentText("No fue posible conectarse al servicio. Contacte al administrador del sistema.");
            }else {
                alert.setTitle("Error inesperado");
                alert.setContentText("Código HTTP "+statusCode+" contacte al administrador del sistema.");
            }

            alert.showAndWait();
        }
    }
}

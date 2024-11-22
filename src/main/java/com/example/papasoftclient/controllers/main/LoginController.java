package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.models.LoginModel;
import com.example.papasoftclient.repositories.LoginRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
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

        FXMLLoader vistaAdministrador = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/Main/dashboardAdministrador.fxml"));
        FXMLLoader vistaSecretario = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/Main/dashboardSecretario.fxml"));
        int tipoUsuario = tabLogin.getSelectionModel().getSelectedIndex();
        String nombreUsuario,clave;
        if(tipoUsuario == 0){
            nombreUsuario = usuarioCoordinador.getText();
            clave = claveCoordinador.getText();
        }else {
            nombreUsuario = usuarioSecretario.getText();
            clave = claveSecretario.getText();
        }
        LoginModel loginModel = new LoginModel(nombreUsuario,clave,tipoUsuario);
        boolean usuarioValido = loginRepository.login(loginModel);

        Scene scene = new Scene(vistaAdministrador.load());
        Stage stage = new Stage();
        stage.setTitle("PapaSoft");
        stage.setScene(scene);
        stage.setMaximized(true);
        Stage cerrar = (Stage)usuarioCoordinador.getScene().getWindow();
        cerrar.close();
        stage.show();

        /*if(usuario.getText().equals("admin") && pass.getText().equals("admin")) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/Main/adminDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("PapaSoft");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            Stage cerrar = (Stage)usuario.getScene().getWindow();
            cerrar.close();
        }else{
            System.out.println("Usuario o contraseña incorrectos.");
        }*/
    }
}

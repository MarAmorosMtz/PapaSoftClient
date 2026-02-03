package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.models.LoginModel;
import com.example.papasoftclient.repositories.LoginRepository;
import com.example.papasoftclient.utils.SessionStore;
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
    Button loginBtn;
    @FXML
    RadioButton coordinatorOption;
    @FXML
    RadioButton secretaryOption;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    LoginRepository loginRepository;

    public LoginController(){
        this.loginRepository = new LoginRepository();
    }

    @FXML
    private void initialize(){
    }

    @FXML
    private void onKeyPressed(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            this.login();
        }
    }

    @FXML
    private void login() throws IOException {
        FXMLLoader view;
        FXMLLoader vistaAdministrador = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/Main/dashboardAdministrador.fxml"));
        FXMLLoader vistaSecretario = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/Main/dashboardSecretario.fxml"));
        int userType=1;     //1 para secretarios, 0 para coordinador
        String username="",password="";
        username = usernameField.getText();
        password = passwordField.getText();
        if(coordinatorOption.isSelected()){
            view = vistaAdministrador;
            userType = 0;
        }else {
            view = vistaSecretario;
            userType = 1;
        }
        LoginModel loginModel = new LoginModel(username,password,userType);
        int statusCode = loginRepository.login(loginModel);

        if(statusCode==200){
            Scene scene = new Scene(view.load());
            Stage stage = new Stage();
            stage.setTitle("PapaSoft");
            stage.setScene(scene);
            stage.setMaximized(true);
            Stage cerrar = (Stage)usernameField.getScene().getWindow();
            cerrar.close();
            SessionStore.getUsername(username);
            SessionStore.setAccountType(userType);
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

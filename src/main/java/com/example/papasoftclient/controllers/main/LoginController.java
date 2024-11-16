package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    Button btn;
    @FXML
    TextField usuario;
    @FXML
    PasswordField pass;
    @FXML
    HBox bottomHBox;
    @FXML
    ImageView imageView;

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

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/Main/dashboardMar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("PapaSoft");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        Stage cerrar = (Stage)usuario.getScene().getWindow();
        cerrar.close();

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

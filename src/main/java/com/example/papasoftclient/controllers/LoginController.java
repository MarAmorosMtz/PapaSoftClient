package com.example.papasoftclient.controllers;

import com.example.papasoftclient.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    TextField usuario;
    @FXML
    PasswordField pass;

    @FXML
    private void entrar() throws IOException {
        if(!usuario.getText().equals("") || !pass.getText().equals("")) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/Main/Dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.show();
            Stage cerrar = (Stage)usuario.getScene().getWindow();
            cerrar.close();
        }else{
            System.out.println("no seas choto");
        }
    }
}

package com.example.papasoftclient.controllers;

import com.example.papasoftclient.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    Button btn;
    @FXML
    private void handleButtonAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        Stage stg = (Stage)btn.getScene().getWindow();
        stg.close();
        stage.show();
    }
}

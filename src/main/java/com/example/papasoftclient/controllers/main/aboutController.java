package com.example.papasoftclient.controllers.main;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class aboutController {

    @FXML
    AnchorPane penesito;

    @FXML
    public void handleEmail1() {
        openEmailClient("l21020339@veracruz.tecnm.mx");
    }
    @FXML
    public void handleEmail2() {
        openEmailClient("l21020397@veracruz.tecnm.mx");
    }
    @FXML
    public void handleEmail3() {
        openEmailClient("l21020366@veracruz.tecnm.mx");
    }
    @FXML
    public void handleEmail4() {
        openEmailClient("l21020418@veracruz.tecnm.mx");
    }
    @FXML
    public void handleEmail5() {
        openEmailClient("l21020459@veracruz.tecnm.mx");
    }
    @FXML
    public void handleEmail6() {
        openEmailClient("l21020400@veracruz.tecnm.mx");
    }

    private void openEmailClient(String email) {
        try {

            URI mailto = new URI("mailto:" + email);

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(mailto);
            } else {
                Runtime.getRuntime().exec(new String[]{"xdg-open", mailto.toString()});
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void salir(){
        Stage stage = (Stage)penesito.getScene().getWindow();
        stage.close();
    }

}

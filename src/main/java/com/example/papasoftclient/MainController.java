package com.example.papasoftclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane vista;
    @FXML
    Button btnLogOut;

    @FXML
    public void initialize() {
        String imagePath = getClass().getResource("media/login_background.jpg").toExternalForm();

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(imagePath),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.AUTO,
                        BackgroundSize.AUTO,
                        false,
                        false,
                        true,
                        true
                )
        );

        // Establecer la imagen como fondo
        vista.setBackground(new Background(backgroundImage));
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();

        String fxml = getViewForButton(clickedButton);
        if (fxml != null) {
            loadView(fxml);
        }
    }

    private String getViewForButton(Button button){
        switch (button.getId()) {
            case "btnPeriodos":
                return "periodo/vistaPeriodo";
            case "btnMaestros":
                return "maestro/vistaMaestro";
            case "btnMaterias":
                return "materia/vistaMateria";
            case "btnAsesores":
                return "asesor/vistaAsesor";
            case "btnAsesorados":
                return "asesorado/vistaAsesorado";
            case "btnCarreras":
                return "carrera/vistaCarrera";
            case "btnAsesorias":
                return "asesoria/vistaAsesoria";
            case "btnSalones":
                return "salon/vistaSalon";
            case "btnHorarios":
                return "horarios/vistaHorarios";
            case "btnReportes":
                return "reporte/vistaReporte";
            case "btnHorariosSalones":
                return "horariosSalon/vistaHorariosSalon";
            case "btnHorariosAsesores":
                return "horariosAsesor/vistaHorariosAsesor";
            case "btnBackups":
                return "backup/vistaBackup";
            case "btnAcercaDe":
                return "ayuda/vistaAcercaDe";
            default:
                return null;
        }
    }

    @FXML
    private void about() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("about/vistaAbout.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();

    }


    private void loadView(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        Parent nuevaVista = loader.load();

        vista.getChildren().clear();
        vista.getChildren().add(nuevaVista);
        AnchorPane.setTopAnchor(nuevaVista, 5.0);
        AnchorPane.setBottomAnchor(nuevaVista, 5.0);
        AnchorPane.setLeftAnchor(nuevaVista, 5.0);
        AnchorPane.setRightAnchor(nuevaVista, 5.0);
    }

    @FXML
    private void logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        Stage stg = (Stage) btnLogOut.getScene().getWindow();
        stg.close();
        stage.show();
    }
}
package com.example.papasoftclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane vista;


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();

        String fxml = getViewForButton(clickedButton);
        if (fxml != null) {
            loadView(fxml);
        }
    }

    private String getViewForButton(Button button) {
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
            default:
                return null;
        }
    }


    private void loadView(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        Parent nuevaVista = loader.load();

        vista.getChildren().clear();
        vista.getChildren().add(nuevaVista);
        AnchorPane.setTopAnchor(nuevaVista, 0.0);
        AnchorPane.setBottomAnchor(nuevaVista, 0.0);
        AnchorPane.setLeftAnchor(nuevaVista, 0.0);
        AnchorPane.setRightAnchor(nuevaVista, 0.0);
    }
}
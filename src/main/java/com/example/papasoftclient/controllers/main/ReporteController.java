package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.repositories.RestAPI;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

public class ReporteController {
    @FXML
    RadioButton radioMateria;
    @FXML
    RadioButton radioAsesor;
    @FXML
    RadioButton radioMensual;
    @FXML
    RadioButton radioSemestral;
    @FXML
    RadioButton radioTotal;
    @FXML
    ComboBox<PeriodoModel> comboPeriodo;
    @FXML
    ComboBox<String> comboAño;
    @FXML
    ComboBox<String> comboMes;
    @FXML
    Button botonHecho;

    private PeriodoRepository periodoRepository;
    private ObservableList<PeriodoModel> catalogoPeriodo;

    @FXML
    public void initialize(){

        periodoRepository = new PeriodoRepository();
        comboPeriodo.setDisable(true);
        catalogoPeriodo = periodoRepository.getCatalogoPeriodo();
        comboPeriodo.setItems(catalogoPeriodo);

        comboAño.setDisable(true);
        comboMes.setDisable(true);
        meses();
        anios();
    }

    private void meses(){
        for(int i=1; i<=12; i++){
            comboMes.getItems().add(String.valueOf(i));
        }
    }

    private void anios(){
        int year = LocalDate.now().getYear();
        for(int i=2000; i<=year; i++){
            comboAño.getItems().add(String.valueOf(i));
        }
    }


    @FXML
    private void handleRadioAction(){
        comboPeriodo.setDisable(false);
        comboAño.setDisable(false);
        comboMes.setDisable(false);
        if(radioTotal.isSelected()){
            comboPeriodo.setDisable(true);
            comboAño.setDisable(true);
            comboMes.setDisable(true);
        } else if (radioMensual.isSelected()) {
            comboPeriodo.setDisable(true);
        } else if (radioSemestral.isSelected()) {
            comboMes.setDisable(true);
            comboAño.setDisable(true);
        }else{
            comboPeriodo.setDisable(true);
            comboAño.setDisable(true);
            comboMes.setDisable(true);
        }
    }

    @FXML
    private void generarReporte(){
        String url = null;
        if(radioAsesor.isSelected()){
            url = RestAPI.ASESOR_REPORT_ENDPOINT;
        }else if(radioMateria.isSelected()){
            url = RestAPI.MATERIA_REPORT_ENDPOINT;
        }
        if(!url.isEmpty() & radioSemestral.isSelected()){
            PeriodoModel model = comboPeriodo.getSelectionModel().getSelectedItem();
            url += "?periodo="+model.getId();
        }else if(!url.isEmpty() & radioMensual.isSelected()){
            int mes = Integer.parseInt(comboMes.getSelectionModel().getSelectedItem());
            int anio = Integer.parseInt(comboAño.getSelectionModel().getSelectedItem());
            url += "?mes="+mes+"&anio="+anio+"";
        }



        if(!url.isEmpty()){
            // Detecta el sistema operativo
            String os = System.getProperty("os.name").toLowerCase();

            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    // Usa Desktop si es compatible
                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(new URI(url));
                } else {
                    // Fallback para sistemas no soportados
                    abrirConComando(url, os);
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }

    }

    private static void abrirConComando(String url, String os) throws IOException {
        // Comando específico según el sistema operativo
        if (os.contains("win")) {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } else if (os.contains("mac")) {
            Runtime.getRuntime().exec("open " + url);
        } else if (os.contains("nix") || os.contains("nux")) {
            String[] browsers = {"xdg-open", "google-chrome", "firefox"};
            boolean opened = false;
            try {
                Runtime.getRuntime().exec(new String[]{"xdg-open", url});
                opened = true;
            } catch (IOException e) {
                opened = false;
            }
        }
    }
}

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
    RadioButton subjectOption;
    @FXML
    RadioButton tutorOption;
    @FXML
    RadioButton monthlyOption;
    @FXML
    RadioButton biannualOption;
    @FXML
    RadioButton totalOption;
    @FXML
    ComboBox<PeriodoModel> periodSelector;
    @FXML
    ComboBox<String> yearSelector;
    @FXML
    ComboBox<String> monthSelector;
    @FXML
    Button generateReportButton;

    private PeriodoRepository periodoRepository;
    private ObservableList<PeriodoModel> catalogoPeriodo;

    @FXML
    public void initialize(){
        periodoRepository = new PeriodoRepository();
        catalogoPeriodo = periodoRepository.getCatalogoPeriodo();
        if(catalogoPeriodo.isEmpty()){
            periodSelector.setDisable(true);
        }
        periodSelector.setItems(catalogoPeriodo);
        fillMonthDropdown();
        fillYearDropdown();
    }

    private void fillMonthDropdown(){
        for(int i=1; i<=12; i++){
            monthSelector.getItems().add(String.valueOf(i));
        }
    }

    private void fillYearDropdown(){
        int year = LocalDate.now().getYear();
        for(int i=2000; i<=year; i++){
            yearSelector.getItems().add(String.valueOf(i));
        }
    }


    @FXML
    private void handlePeriodicitySelection(){
        if(totalOption.isSelected()){
            periodSelector.setDisable(true);
            yearSelector.setDisable(true);
            monthSelector.setDisable(true);
        } else if (monthlyOption.isSelected()) {
            periodSelector.setDisable(true);
            monthSelector.setDisable(false);
            yearSelector.setDisable(false);
        } else if (biannualOption.isSelected()) {
            periodSelector.setDisable(false);
            monthSelector.setDisable(true);
            yearSelector.setDisable(true);
        }else{
            periodSelector.setDisable(true);
            yearSelector.setDisable(true);
            monthSelector.setDisable(true);
        }
    }

    @FXML
    private void generateReport(){
        String url = null;
        if(tutorOption.isSelected()){
            url = RestAPI.ASESOR_REPORT_ENDPOINT;
        }else if(subjectOption.isSelected()){
            url = RestAPI.CARRERA_REPORT_ENDPOINT;
        }
        if(!url.isEmpty() & biannualOption.isSelected()){
            PeriodoModel model = periodSelector.getSelectionModel().getSelectedItem();
            url += "?periodo="+model.getId();
        }else if(!url.isEmpty() & monthlyOption.isSelected()){
            int mes = Integer.parseInt(monthSelector.getSelectionModel().getSelectedItem());
            int anio = Integer.parseInt(yearSelector.getSelectionModel().getSelectedItem());
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

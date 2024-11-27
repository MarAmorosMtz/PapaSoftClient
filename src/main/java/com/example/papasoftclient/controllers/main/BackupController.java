package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.repositories.BackupsRepository;
import com.example.papasoftclient.repositories.RestAPI;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class BackupController {

    @FXML
    TextField txtPath;

    BackupsRepository backupsRepository;
    File archivoSeleccionado;

    public BackupController() {
        backupsRepository = new BackupsRepository();
    }

    @FXML
    private void seleccionarArchivo() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar archivo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos SQL", "*.sql"));

        Stage stage = new Stage();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            txtPath.setText(archivoSeleccionado.getAbsolutePath());
        } else {
            txtPath.setText("");
        }
        this.archivoSeleccionado = archivoSeleccionado;
    }

    @FXML
    private void restaurarCopia(){
        if (this.archivoSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(backupsRepository.restore(archivoSeleccionado)){
                alert.setTitle("Restauración completa");
                alert.setContentText("Se ha restaurado la copia de seguridad exitosamente.");
            }else{
                alert.setTitle("Error");
                alert.setContentText("No ha sido posible restaurar la copia de seguridad.");
            }
            alert.showAndWait();
        }
    }

    @FXML
    private void crearCopia() throws URISyntaxException, IOException {
        String url = RestAPI.BACKUP_ENDPOINT; // URL que deseas abrir

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

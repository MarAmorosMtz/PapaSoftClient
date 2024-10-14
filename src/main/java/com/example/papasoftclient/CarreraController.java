package com.example.papasoftclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CarreraController {
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Carrera/AgregarCarrera.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.setResizable(false);

        newStage.show();
    }

    /*private void optionsColumn(){
        accionesRow.setCellValueFactory(new PropertyValueFactory<maestroItem, String>("nombre"));

        Callback<TableColumn<maestroItem, String>, TableCell<maestroItem, String>> cellFactory
                = //
                new Callback<TableColumn<maestroItem, String>, TableCell<maestroItem, String>>() {
                    @Override
                    public TableCell call(final TableColumn<maestroItem, String> param) {
                        final TableCell<maestroItem, String> cell = new TableCell<maestroItem, String>() {

                            final Button btn = new Button("● ● ●");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    this.btn.setOnMouseClicked(mouseEvent -> {
                                        try {

                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogo.fxml"));
                                            Parent root = loader.load();

                                            // Crear y mostrar el Popup
                                            Popup popup = new Popup();
                                            popup.getContent().add(root);

                                            dialogoController controller = loader.getController();
                                            controller.setParentPopup(popup);

                                            // Mostrar el Popup
                                            popup.show(btn, mouseEvent.getScreenX(), mouseEvent.getScreenY());

                                            // Guardar referencia al Popup actual
                                            currentPopup = popup;

                                        } catch (Exception ignored) { }
                                    });
                                    btn.getStyleClass().add("actionButton");
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        accionesRow.setCellFactory(cellFactory);
    }*/
}

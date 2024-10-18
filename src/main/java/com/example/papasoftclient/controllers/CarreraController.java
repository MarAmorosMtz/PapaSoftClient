package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.CarreraPage;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.UUID;

import javafx.stage.Popup;
import javafx.util.Callback;

public class CarreraController
{

    @FXML
    private TableView<CarreraModel> tablaCarreras;
    @FXML
    private TableColumn<CarreraModel, UUID> columnaId;
    @FXML
    private TableColumn<CarreraModel,String> columnaNombre;
    @FXML
    private TableColumn<CarreraModel,String> columnaAcciones;
    @FXML
    private Pagination paginadorCarreras;
    private CloseableHttpClient httpClient;
    private CarreraRepository carreraRepository;
    private ObjectMapper mapper;

    public CarreraController(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        carreraRepository = new CarreraRepository(httpClient, mapper ,RestAPI.CARRERAS_ENDPOINT);
    }

    @FXML
    public void initialize() {
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaAcciones.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        Callback<TableColumn<CarreraModel, String>, TableCell<CarreraModel, String>> cellFoctory = (TableColumn<CarreraModel, String> param) -> {
            final TableCell<CarreraModel, String> cell = new TableCell<CarreraModel, String>() {
                private Node btn;

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        final Button btn = new Button("● ● ●");
                        this.btn.setOnMouseClicked(mouseEvent -> {
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/papasoftclient/Util/EditDelete.fxml"));
                                Parent root = loader.load();

                                // Crear y mostrar el Popup
                                Popup popup = new Popup();
                                popup.getContent().add(root);

                                DialogoController controller = loader.getController();
                                controller.setParentPopup(popup);

                                // Mostrar el Popup
                                popup.show(btn, mouseEvent.getScreenX(), mouseEvent.getScreenY());

                                // Guardar referencia al Popup actual
                                Popup currentPopup = popup;

                            } catch (Exception ignored) { }
                        });
                        btn.getStyleClass().add("actionButton");
                        setGraphic(btn);
                        setText(null);

                    }
                }

            };

            return cell;
        };
        paginadorCarreras.setPageFactory(this::updateTable);
        updateTable(1);
    }

    public void loadCarreras(CarreraPage page){
        tablaCarreras.setItems(FXCollections.observableArrayList(page.getCarreras()));
    }

    public Node updateTable(int pageIndex){
        CarreraPage tmp = carreraRepository.search(pageIndex);
        if(tmp != null){
            loadCarreras(tmp);
        }
        return tablaCarreras;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("/com/example/papasoftclient/Carrera/AgregarCarrera.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch (IOException _){}
    }

}

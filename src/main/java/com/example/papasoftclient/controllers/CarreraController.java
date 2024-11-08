package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraBase;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.UUID;

import javafx.util.Callback;

public class CarreraController
{

    @FXML
    private TableView<CarreraModel> tablaCarreras;
//    @FXML
//    private TableColumn<CarreraModel, UUID> columnaId;
    @FXML
    private TableColumn<CarreraModel,String> columnaNombre;
//    @FXML
//    private TableColumn<CarreraModel,String> columnaAcciones;
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

//        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

//        Callback<TableColumn<CarreraModel, String>, TableCell<CarreraModel, String>> cellFactory
//                = new Callback<TableColumn<CarreraModel, String>, TableCell<CarreraModel, String>>() {
//            @Override
//            public TableCell<CarreraModel, String> call(final TableColumn<CarreraModel, String> param) {
//                final TableCell<CarreraModel, String> cell = new TableCell<CarreraModel, String>() {
//                    final Button btn = new Button("● ● ●");
//
//                    @Override
//                    public void updateItem(String item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                            setText(null);
//                        } else {
//
//                            int rowIndex = getIndex();
//                            CarreraModel carrera = tablaCarreras.getItems().get(rowIndex);
//                            CarreraBase carreraBase = tablaCarreras.getItems().get(rowIndex);
//
//                            btn.setOnMouseClicked(mouseEvent -> {
//                                try {
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Util/EditDeleteCarrera.fxml"));
//                                    Parent root = loader.load();
//
//                                    Stage popupStage = new Stage(StageStyle.UNDECORATED);
//                                    popupStage.initModality(Modality.NONE);
//                                    popupStage.initOwner(btn.getScene().getWindow());
//                                    popupStage.setScene(new Scene(root));
//
//                                    popupStage.setX(mouseEvent.getScreenX());
//                                    popupStage.setY(mouseEvent.getScreenY());
//
//                                    popupStage.setAlwaysOnTop(true);
//
//                                    Scene mainScene = btn.getScene();
//                                    mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
//                                        if (!popupStage.getScene().getWindow().equals(event.getTarget())) {
//                                            popupStage.close();
//                                        }
//                                    });
//
//                                    DialogoCarreraController controller = loader.getController();
//                                    controller.setStage(popupStage);
//                                    controller.setModel(carrera);
//                                    controller.setBase(carreraBase);
//                                    System.out.println(carrera.getNombre());
//
//                                    popupStage.show();
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            });
//
//                            btn.getStyleClass().add("actionButton");
//                            setGraphic(btn);
//                            setText(null);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//
//        columnaAcciones.setCellFactory(cellFactory);

        paginadorCarreras.setPageFactory(this::updateTable);
    }





    public void loadCarreras(CarreraPage page){
        tablaCarreras.setItems(FXCollections.observableArrayList(page.getCarreras()));
    }

    public Node updateTable(int pageIndex){
        CarreraPage tmp = carreraRepository.search(pageIndex);
        if(tmp != null){
            loadCarreras(tmp);
        }else System.out.println("La pagina es nula");
        return tablaCarreras;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/example/papasoftclient/carrera/vistaAgregarCarrera.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Hacer que el Stage sea modal
        stage.initOwner(stage.getOwner());
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
    }

}

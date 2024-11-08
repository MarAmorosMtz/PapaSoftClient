package com.example.papasoftclient.controllers;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.example.papasoftclient.utils.Observador;
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
import java.util.Date;
import java.util.UUID;

import javafx.util.Callback;

public class PeriodoController implements Observador {

    @FXML
    private TableView<PeriodoModel> tablaPeriodo;
//    @FXML
//    private TableColumn<PeriodoModel, UUID> columnaID;
    @FXML
    private TableColumn<PeriodoModel,String> columnaNombre;
    @FXML
    private TableColumn<PeriodoModel, Date> columnaFechaI;
    @FXML
    private TableColumn<PeriodoModel, Date> columnaFechaF;
//    @FXML
//    private TableColumn<PeriodoModel,String> columnaAcciones;
    @FXML
    private Pagination paginadorPeriodo;

    private PeriodoRepository periodoRepository;

    public PeriodoController(){
        periodoRepository = new PeriodoRepository();
    }

    @FXML
    public void initialize() {
//        columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaFechaI.setCellValueFactory(new PropertyValueFactory<>("fecha_inicio"));
        columnaFechaF.setCellValueFactory(new PropertyValueFactory<>("fecha_fin"));

//        Callback<TableColumn<PeriodoModel, String>, TableCell<PeriodoModel, String>> cellFactory
//                = new Callback<TableColumn<PeriodoModel, String>, TableCell<PeriodoModel, String>>() {
//            @Override
//            public TableCell<PeriodoModel, String> call(final TableColumn<PeriodoModel, String> param) {
//                final TableCell<PeriodoModel, String> cell = new TableCell<PeriodoModel, String>() {
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
//                            PeriodoModel periodo = tablaPeriodo.getItems().get(rowIndex);
//                            PeriodoBase periodoBase = tablaPeriodo.getItems().get(rowIndex);
//
//                            btn.setOnMouseClicked(mouseEvent -> {
//                                try {
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Util/EditDeletePeriodo.fxml"));
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
//                                    DialogoPeriodoController controller = loader.getController();
//                                    controller.setStage(popupStage);
//                                    controller.setModel(periodo);
//                                    controller.setBase(periodoBase);
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

        paginadorPeriodo.setPageFactory(this::updateTable);
    }





    public void loadPeriodos(PeriodoPage page){
        tablaPeriodo.setItems(FXCollections.observableArrayList(page.getPeriodos()));
    }

    public Node updateTable(int pageIndex){
        PeriodoPage tmp = periodoRepository.search(pageIndex);
        if(tmp != null){
            loadPeriodos(tmp);
        }else System.out.println("La pagina es nula");
        return tablaPeriodo;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/periodo/vistaAgregarPeriodo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddPeriodoController controller = fxmlLoader.getController();
        controller.agregarObservador(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Hacer que el Stage sea modal
        stage.initOwner(stage.getOwner());
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void actualizar() {
        updateTable(paginadorPeriodo.getCurrentPageIndex());
    }
}

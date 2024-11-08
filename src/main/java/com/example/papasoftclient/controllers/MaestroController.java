package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.MaestroModel;
import com.example.papasoftclient.models.MaestroPage;
import com.example.papasoftclient.repositories.MaestroRepository;
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
import javafx.util.Callback;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.UUID;

public class MaestroController {
    @FXML
    TableView <MaestroModel> tablaMaestro;
//    @FXML
//    private TableColumn<MaestroModel, UUID> columnaID;
    @FXML
    private TableColumn<MaestroModel,String> columnaNombre;
    @FXML
    private TableColumn<MaestroModel,String> columnaApellidoP;
    @FXML
    private TableColumn<MaestroModel,String> columnaApellidoM;
//    @FXML
//    private TableColumn<MaestroModel,String> columnaAcciones;
    @FXML
    private Pagination paginadorMaestros;

    private CloseableHttpClient httpClient;
    private MaestroRepository maestroRepository;
    private ObjectMapper mapper;

    public MaestroController(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        maestroRepository = new MaestroRepository(httpClient, mapper , RestAPI.MAESTROS_ENDPOINT);
    }

    @FXML
    public void initialize() {
//        columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidoP.setCellValueFactory(new PropertyValueFactory<>("apellido_p"));
        columnaApellidoM.setCellValueFactory(new PropertyValueFactory<>("apellido_m"));

//        Callback<TableColumn<MaestroModel, String>, TableCell<MaestroModel, String>> cellFactory
//                = new Callback<TableColumn<MaestroModel, String>, TableCell<MaestroModel, String>>() {
//            @Override
//            public TableCell<MaestroModel, String> call(final TableColumn<MaestroModel, String> param) {
//                final TableCell<MaestroModel, String> cell = new TableCell<MaestroModel, String>() {
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
//                            MaestroModel maestro = tablaMaestro.getItems().get(rowIndex);
//                            MaestroModel maestroBase = tablaMaestro.getItems().get(rowIndex);
//
//                            btn.setOnMouseClicked(mouseEvent -> {
//                                try {
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Util/EditDeleteMaestro.fxml"));
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
//                                    DialogoMaestroController controller = loader.getController();
//                                    controller.setStage(popupStage);
//                                    controller.setModel(maestro);
//                                    controller.setBase(maestroBase);
//                                    System.out.println(maestro.getNombre());
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

        paginadorMaestros.setPageFactory(this::updateTable);
    }

    public void loadMaestros(MaestroPage page){
        tablaMaestro.setItems(FXCollections.observableArrayList(page.getMaestros()));
    }

    public Node updateTable(int pageIndex){
        MaestroPage tmp = maestroRepository.search(pageIndex);
        if(tmp != null){
            loadMaestros(tmp);
        }else System.out.println("La pagina es nula");
        return tablaMaestro;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/example/papasoftclient/maestro/vistaAgregarMaestro.fxml"));
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

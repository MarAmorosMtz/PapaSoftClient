package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.controllers.add.AddSalonController;
import com.example.papasoftclient.controllers.delete.ConfirmacionSalonController;
import com.example.papasoftclient.controllers.edit.EditSalonController;
import com.example.papasoftclient.models.SalonModel;
import com.example.papasoftclient.models.SalonPage;
import com.example.papasoftclient.repositories.SalonRepository;
import com.example.papasoftclient.utils.Observador;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SalonController  implements Observador{

    @FXML
    private TableView<SalonModel> tablaSalones;
//    @FXML
//    private TableColumn<SalonModel, UUID> columnaId;
    @FXML
    private TableColumn<SalonModel,String> columnaNombre;
//    @FXML
//    private TableColumn<SalonModel,String> columnaAcciones;
    @FXML
    private Pagination paginadorSalones;
    private SalonRepository salonRepository;

    public SalonController(){
        salonRepository = new SalonRepository();
    }

    @FXML
    public void initialize() {
//        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

//        Callback<TableColumn<SalonModel, String>, TableCell<SalonModel, String>> cellFactory
//                = new Callback<TableColumn<SalonModel, String>, TableCell<SalonModel, String>>() {
//            @Override
//            public TableCell<SalonModel, String> call(final TableColumn<SalonModel, String> param) {
//                final TableCell<SalonModel, String> cell = new TableCell<SalonModel, String>() {
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
//                            SalonModel salon = tablaSalones.getItems().get(rowIndex);
//                            SalonModel salonBase = tablaSalones.getItems().get(rowIndex);
//
//                            btn.setOnMouseClicked(mouseEvent -> {
//                                try {
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Util/popupSalon.fxml"));
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
//                                    DialogoSalonController controller = loader.getController();
//                                    controller.setStage(popupStage);
//                                    controller.setModel(salon);
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
//        columnaAcciones.setCellFactory(cellFactory);
        paginadorSalones.setPageFactory(this::updateTable);
    }

    public void loadMaterias(SalonPage page){
        tablaSalones.setItems(FXCollections.observableArrayList(page.getSalones()));
    }

    public Node updateTable(int pageIndex){
        SalonPage tmp = salonRepository.search(pageIndex+1);
        if(tmp != null){
            loadMaterias(tmp);paginadorSalones.setMaxPageIndicatorCount(tmp.getPaginas());
            paginadorSalones.setMaxPageIndicatorCount(tmp.getPaginas());
            paginadorSalones.setPageCount(tmp.getPaginas());
        }
        return tablaSalones;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/salon/vistaAgregarSalon.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddSalonController controller = fxmlLoader.getController();
        controller.agregarObservador(this);
        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.setResizable(false);

        newStage.show();
    }

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/salon/vistaEditarSalon.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaSalones.getSelectionModel().getSelectedIndex();
        SalonModel salon = tablaSalones.getItems().get(rowIndex);

        EditSalonController editController = loader.getController();
        editController.setModel(salon);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
    }

    @FXML
    private void delete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/salon/vistaConfirmacionSalon.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaSalones.getSelectionModel().getSelectedIndex();
        SalonModel salon = tablaSalones.getItems().get(rowIndex);

        ConfirmacionSalonController confirmacionController = loader.getController();
        confirmacionController.setSalon(salon);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
    }

    @Override
    public void actualizar() {
        updateTable(paginadorSalones.getCurrentPageIndex());
    }
}

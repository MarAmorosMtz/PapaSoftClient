package com.example.papasoftclient.controllers;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.models.MateriaPage;
import com.example.papasoftclient.repositories.MateriaRepository;
import com.example.papasoftclient.utils.Observador;
import javafx.beans.property.SimpleStringProperty;
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

import java.io.IOException;
import java.util.UUID;

public class MateriaController  implements Observador{

    @FXML
    private TableView<MateriaModel> tablaMaterias;
//    @FXML
//    private TableColumn<MateriaModel, UUID> columnaId;
    @FXML
    private TableColumn<MateriaModel,String> columnaNombre;
    @FXML
    private TableColumn<MateriaModel,String> columnaCarrera;
//    @FXML
//    private TableColumn<MateriaModel,String> columnaAcciones;
    @FXML
    private Pagination paginadorMaterias;
    private MateriaRepository materiaRepository;
    private int currentPage;

    public MateriaController(){
        materiaRepository = new MateriaRepository();
    }

    @FXML
    public void initialize() {
//        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCarrera.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCarrera().getNombre())
        );

//        Callback<TableColumn<MateriaModel, String>, TableCell<MateriaModel, String>> cellFactory
//                = new Callback<TableColumn<MateriaModel, String>, TableCell<MateriaModel, String>>() {
//            @Override
//            public TableCell<MateriaModel, String> call(final TableColumn<MateriaModel, String> param) {
//                final TableCell<MateriaModel, String> cell = new TableCell<MateriaModel, String>() {
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
//                            MateriaModel materia = tablaMaterias.getItems().get(rowIndex);
//                            MateriaModel materiaBase = tablaMaterias.getItems().get(rowIndex);
//
//                            btn.setOnMouseClicked(mouseEvent -> {
//                                try {
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Util/EditDeleteMateria.fxml"));
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
//                                    DialogoMateriaController controller = loader.getController();
//                                    controller.setStage(popupStage);
//                                    controller.setModel(materia);
//                                    controller.setBase(materiaBase);
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
        paginadorMaterias.setPageFactory(this::updateTable);
    }

    public void loadMaterias(MateriaPage page){
        tablaMaterias.setItems(FXCollections.observableArrayList(page.getMaterias()));
    }

    public Node updateTable(int pageIndex){
        MateriaPage tmp = materiaRepository.search(pageIndex);
        if(tmp != null){
            loadMaterias(tmp);paginadorMaterias.setMaxPageIndicatorCount(tmp.getPaginas());
        }
        return tablaMaterias;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/materia/vistaAgregarMateria.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddMateriaController controller = fxmlLoader.getController();
        controller.agregarObservador(this);
        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.setResizable(false);

        newStage.show();
    }

    @Override
    public void actualizar() {
        updateTable(paginadorMaterias.getCurrentPageIndex());
    }
}

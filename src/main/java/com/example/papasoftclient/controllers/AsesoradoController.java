package com.example.papasoftclient.controllers;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.models.AsesoradoModel;
import com.example.papasoftclient.models.AsesoradoModel;
import com.example.papasoftclient.models.AsesoradoPage;
import com.example.papasoftclient.models.MateriaPage;
import com.example.papasoftclient.repositories.AsesoradoRepository;
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

public class AsesoradoController  implements Observador{

    @FXML
    private TableView<AsesoradoModel> tablaAsesorados;
    @FXML
    private TableColumn<AsesoradoModel, UUID> columnaId;
    @FXML
    private TableColumn<AsesoradoModel,String> columnaNumCtrl;
    @FXML
    private TableColumn<AsesoradoModel,String> columnaApellidoP;
    @FXML
    private TableColumn<AsesoradoModel,String> columnaApellidoM;
    @FXML
    private TableColumn<AsesoradoModel,String> columnaNombre;
    @FXML
    private TableColumn<AsesoradoModel,String> columnaAcciones;
    @FXML
    private Pagination paginadorAsesorados;
    private AsesoradoRepository asesoradoRepository;
    private int currentPage;

    public AsesoradoController(){
        asesoradoRepository  = new AsesoradoRepository();
    }

    @FXML
    public void initialize() {
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNumCtrl.setCellValueFactory(new PropertyValueFactory<>("num_ctrl"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidoP.setCellValueFactory(new PropertyValueFactory<>("apellido_p"));
        columnaApellidoM.setCellValueFactory(new PropertyValueFactory<>("apellido_m"));


        Callback<TableColumn<AsesoradoModel, String>, TableCell<AsesoradoModel, String>> cellFactory
                = new Callback<TableColumn<AsesoradoModel, String>, TableCell<AsesoradoModel, String>>() {
            @Override
            public TableCell<AsesoradoModel, String> call(final TableColumn<AsesoradoModel, String> param) {
                final TableCell<AsesoradoModel, String> cell = new TableCell<AsesoradoModel, String>() {
                    final Button btn = new Button("● ● ●");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            int rowIndex = getIndex();
                            AsesoradoModel materia = tablaAsesorados.getItems().get(rowIndex);

                            btn.setOnMouseClicked(mouseEvent -> {
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/Util/EditDeleteAsesorado.fxml"));
                                    Parent root = loader.load();

                                    Stage popupStage = new Stage(StageStyle.UNDECORATED);
                                    popupStage.initModality(Modality.NONE);
                                    popupStage.initOwner(btn.getScene().getWindow());
                                    popupStage.setScene(new Scene(root));

                                    popupStage.setX(mouseEvent.getScreenX());
                                    popupStage.setY(mouseEvent.getScreenY());

                                    popupStage.setAlwaysOnTop(true);

                                    Scene mainScene = btn.getScene();
                                    mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                                        if (!popupStage.getScene().getWindow().equals(event.getTarget())) {
                                            popupStage.close();
                                        }
                                    });

                                    DialogoAsesoradoController controller = loader.getController();
                                    controller.setStage(popupStage);
                                    controller.setModel(materia);
                                    popupStage.show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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
        columnaAcciones.setCellFactory(cellFactory);
        paginadorAsesorados.setPageFactory(this::updateTable);
        updateTable(paginadorAsesorados.getCurrentPageIndex());
    }

    public void loadMaterias(AsesoradoPage page){
        tablaAsesorados.setItems(FXCollections.observableArrayList(page.getAsesorados()));
    }

    public Node updateTable(int pageIndex){
        AsesoradoPage tmp = asesoradoRepository.search(pageIndex);
        if(tmp != null){
            loadMaterias(tmp);
            paginadorAsesorados.setMaxPageIndicatorCount(tmp.getPaginas());
            paginadorAsesorados.setPageCount(tmp.getPaginas());
        }
        return tablaAsesorados;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/asesorado/vistaAgregarAsesorado.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddAsesoradoController controller = fxmlLoader.getController();
        controller.agregarObservador(this);
        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.setResizable(false);

        newStage.show();
    }

    @Override
    public void actualizar() {
        updateTable(paginadorAsesorados.getCurrentPageIndex());
    }
}

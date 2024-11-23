package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.controllers.add.AddCarreraController;
import com.example.papasoftclient.controllers.delete.ConfirmacionCarreraController;
import com.example.papasoftclient.controllers.edit.EditCarreraController;
import com.example.papasoftclient.models.CarreraBase;
import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.CarreraPage;
import com.example.papasoftclient.repositories.CarreraRepository;
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
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CarreraController implements Observador {

    @FXML
    private TableView<CarreraModel> tablaCarreras;

    @FXML
    private TableColumn<CarreraModel,String> columnaNombre;

    @FXML
    private Pagination paginadorCarreras;
    private CarreraRepository carreraRepository;

    public CarreraController(){
        carreraRepository = new CarreraRepository();
    }

    @FXML
    public void initialize() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        paginadorCarreras.setPageFactory(this::updateTable);
    }

    public void loadCarreras(CarreraPage page){
        tablaCarreras.setItems(FXCollections.observableArrayList(page.getCarreras()));
    }

    public Node updateTable(int pageIndex){
        CarreraPage tmp = carreraRepository.search(pageIndex+1);
        if(tmp != null){
            loadCarreras(tmp);
            //No sé por qué funciona este parche, LOL  <---- Puto kevin
            if(tmp.getPaginas()!=paginadorCarreras.getPageCount()) {
                paginadorCarreras.setPageCount(tmp.getPaginas());
            }
        }
        return tablaCarreras;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/carrera/vistaAgregarCarrera.fxml"));
        Parent parent = loader.load();

        AddCarreraController controller = loader.getController();
        controller.agregarObservador(this);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Hacer que el Stage sea modal
        stage.initOwner(stage.getOwner());
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/carrera/vistaEditarCarrera.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaCarreras.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
        CarreraModel carrera = tablaCarreras.getItems().get(rowIndex);

        EditCarreraController editController = loader.getController();
        editController.setModel(carrera);
        editController.agregarObservador(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        }
    }

    @FXML
    private void delete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/carrera/vistaConfirmacionCarrera.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaCarreras.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
        CarreraModel carrera = tablaCarreras.getItems().get(rowIndex);

        ConfirmacionCarreraController confirmacionController = loader.getController();
        confirmacionController.setCarrera(carrera);
        confirmacionController.agregarObservador(this);

            GaussianBlur blurEffect = new GaussianBlur();
            blurEffect.setRadius(10);
            Stage mainStage = (Stage) tablaCarreras.getScene().getWindow();
            mainStage.getScene().getRoot().setEffect(blurEffect);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

            stage.setOnHiding(event -> mainStage.getScene().getRoot().setEffect(null));

        stage.show();
        }
    }

    @Override
    public void actualizar() {
        this.updateTable(paginadorCarreras.getCurrentPageIndex());
    }
}

package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.controllers.add.AddPeriodoController;
import com.example.papasoftclient.controllers.delete.ConfirmacionPeriodoController;
import com.example.papasoftclient.controllers.edit.EditPeriodoController;
import com.example.papasoftclient.models.PeriodoBase;
import com.example.papasoftclient.models.PeriodoModel;
import com.example.papasoftclient.models.PeriodoPage;
import com.example.papasoftclient.repositories.PeriodoRepository;
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
import java.util.Date;

public class PeriodoController implements Observador {

    @FXML
    private TableView<PeriodoModel> tablaPeriodo;

    @FXML
    private TableColumn<PeriodoModel,String> columnaNombre;
    @FXML
    private TableColumn<PeriodoModel, Date> columnaFechaI;
    @FXML
    private TableColumn<PeriodoModel, Date> columnaFechaF;

    @FXML
    private Pagination paginadorPeriodo;

    private PeriodoRepository periodoRepository;

    public PeriodoController(){
        periodoRepository = new PeriodoRepository();
    }

    @FXML
    public void initialize() {

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaFechaI.setCellValueFactory(new PropertyValueFactory<>("fecha_inicio"));
        columnaFechaF.setCellValueFactory(new PropertyValueFactory<>("fecha_fin"));
        paginadorPeriodo.setPageFactory(this::updateTable);
    }

    public void loadPeriodos(PeriodoPage page){
        tablaPeriodo.setItems(FXCollections.observableArrayList(page.getPeriodos()));
    }

    public Node updateTable(int pageIndex){
        PeriodoPage tmp = periodoRepository.search(pageIndex+1);
        if(tmp != null){
            loadPeriodos(tmp);
            if(tmp.getPaginas()!=paginadorPeriodo.getPageCount()){
                paginadorPeriodo.setPageCount(tmp.getPaginas());
            }
        }
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

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/periodo/vistaEditarPeriodo.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaPeriodo.getSelectionModel().getSelectedIndex();


        if(rowIndex != -1){
            PeriodoModel periodo = tablaPeriodo.getItems().get(rowIndex);
            PeriodoBase periodoBase = tablaPeriodo.getItems().get(rowIndex);

            EditPeriodoController editController = loader.getController();
            editController.agregarObservador(this);
            editController.setModel(periodo);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/periodo/vistaConfirmacionPeriodo.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaPeriodo.getSelectionModel().getSelectedIndex();


        if(rowIndex != -1){
        PeriodoModel periodo = tablaPeriodo.getItems().get(rowIndex);

        ConfirmacionPeriodoController confirmacionController = loader.getController();
        confirmacionController.setPeriodo(periodo);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
        }
        if(rowIndex != -1){
            PeriodoModel periodo = tablaPeriodo.getItems().get(rowIndex);
            ConfirmacionPeriodoController confirmacionController = loader.getController();
            confirmacionController.agregarObservador(this);
            confirmacionController.setPeriodo(periodo);

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.setMaximized(false);
            stage.setResizable(false);

            stage.show();
        }

      
    }

    @Override
    public void actualizar() {
        updateTable(paginadorPeriodo.getCurrentPageIndex());
    }
}

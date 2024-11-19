package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.controllers.add.AddMaestroController;
import com.example.papasoftclient.controllers.delete.ConfirmacionMaestroController;
import com.example.papasoftclient.controllers.edit.EditMaestroController;
import com.example.papasoftclient.models.MaestroModel;
import com.example.papasoftclient.models.MaestroPage;
import com.example.papasoftclient.repositories.MaestroRepository;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

public class MaestroController implements Observador {
    @FXML
    TableView <MaestroModel> tablaMaestro;
    @FXML
    private TableColumn<MaestroModel,String> columnaNombre;
    @FXML
    private TableColumn<MaestroModel,String> columnaApellidoP;
    @FXML
    private TableColumn<MaestroModel,String> columnaApellidoM;
    @FXML
    private Pagination paginadorMaestros;

    private MaestroRepository maestroRepository;

    public MaestroController(){
        maestroRepository = new MaestroRepository();
    }

    @FXML
    public void initialize() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidoP.setCellValueFactory(new PropertyValueFactory<>("apellido_p"));
        columnaApellidoM.setCellValueFactory(new PropertyValueFactory<>("apellido_m"));
        paginadorMaestros.setPageFactory(this::updateTable);
    }

    public void loadMaestros(MaestroPage page){
        tablaMaestro.setItems(FXCollections.observableArrayList(page.getMaestros()));
    }

    public Node updateTable(int pageIndex){
        MaestroPage tmp = maestroRepository.search(pageIndex+1);
        if(tmp != null){
            loadMaestros(tmp);
            if(tmp.getPaginas()!=paginadorMaestros.getPageCount()){
                paginadorMaestros.setPageCount(tmp.getPaginas());
            }
        }
        return tablaMaestro;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/maestro/vistaAgregarMaestro.fxml"));
        Parent parent = loader.load();

        AddMaestroController controller = loader.getController();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/maestro/vistaEditarMaestro.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaMaestro.getSelectionModel().getSelectedIndex();
        MaestroModel maestro = tablaMaestro.getItems().get(rowIndex);
        MaestroModel maestroBase = tablaMaestro.getItems().get(rowIndex);

        EditMaestroController editController = loader.getController();
        editController.setModel(maestro);
        editController.agregarObservador(this);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/maestro/vistaConfirmacionMaestro.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaMaestro.getSelectionModel().getSelectedIndex();
        MaestroModel maestro = tablaMaestro.getItems().get(rowIndex);

        ConfirmacionMaestroController confirmacionController = loader.getController();
        confirmacionController.setMaestro(maestro);
        confirmacionController.agregarObservador(this);

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
        updateTable(paginadorMaestros.getCurrentPageIndex());
    }
}

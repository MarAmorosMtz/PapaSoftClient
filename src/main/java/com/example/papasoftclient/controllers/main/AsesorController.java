package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.controllers.delete.ConfirmacionAsesorController;
import com.example.papasoftclient.controllers.delete.ConfirmacionMaestroController;
import com.example.papasoftclient.controllers.edit.EditAsesorController;
import com.example.papasoftclient.controllers.edit.EditMaestroController;
import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.AsesorRepository;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class AsesorController {
    @FXML
    TableView<AsesorModel> tablaAsesor;
    @FXML
    TableColumn<AsesorModel, String> columnaNumeroControl;
    @FXML
    TableColumn<AsesorModel, String> columnaNombre;
    @FXML
    TableColumn<AsesorModel, String> columnaApellidoP;
    @FXML
    TableColumn<AsesorModel, String> columnaApellidoM;
    @FXML
    TableColumn<AsesorModel, String> columnaCorreo;
    @FXML
    TableColumn<AsesorModel, String> columnaTelefono;
    @FXML
    TableColumn<AsesorModel, Date> columnaFecha;
    @FXML
    TableColumn<AsesorModel, Integer> columnaSemestre;
    @FXML
    TableColumn<AsesorModel, UUID> columnaCarrera;
    @FXML
    TableColumn<AsesorModel, String> columnaContrato;
    @FXML
    Pagination paginadorAsesor;

    private CloseableHttpClient httpClient;
    private AsesorRepository asesorRepository;
    private ObjectMapper mapper;

    public AsesorController(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        asesorRepository = new AsesorRepository(httpClient, mapper , RestAPI.ASESORES_ENDPOINT);
    }

    @FXML
    public void initialize() {

        columnaNumeroControl.setCellValueFactory(new PropertyValueFactory<>("num_ctrl"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidoP.setCellValueFactory(new PropertyValueFactory<>("apellido_p"));
        columnaApellidoM.setCellValueFactory(new PropertyValueFactory<>("apellido_m"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha_inscripcion"));
        columnaSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        columnaCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        columnaContrato.setCellValueFactory(new PropertyValueFactory<>("contrato"));

        paginadorAsesor.setPageFactory(this::updateTable);
    }

    public void loadAsesores(AsesorPage page){
        tablaAsesor.setItems(FXCollections.observableArrayList(page.getAsesores()));
    }

    public Node updateTable(int pageIndex){
        AsesorPage tmp = asesorRepository.search(pageIndex);
        if(tmp != null){
            loadAsesores(tmp);
        }else System.out.println("La pagina es nula");
        return tablaAsesor;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/example/papasoftclient/asesor/vistaAgregarAsesor.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaEditarAsesor.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaAsesor.getSelectionModel().getSelectedIndex();
        AsesorModel asesorModel = tablaAsesor.getItems().get(rowIndex);
        AsesorBase asesorBase = tablaAsesor.getItems().get(rowIndex);

        EditAsesorController editController = loader.getController();
        editController.setBase(asesorBase);
        editController.setModel(asesorModel);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaConfirmacionAsesor.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaAsesor.getSelectionModel().getSelectedIndex();
        AsesorModel asesorModel = tablaAsesor.getItems().get(rowIndex);

        ConfirmacionAsesorController confirmacionController = loader.getController();
        confirmacionController.setAsesor(asesorModel);

        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.show();
    }
}

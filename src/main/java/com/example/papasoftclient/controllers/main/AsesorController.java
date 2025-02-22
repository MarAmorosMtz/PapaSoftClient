package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.controllers.add.AddAsesorController;
import com.example.papasoftclient.controllers.delete.ConfirmacionAsesorController;
import com.example.papasoftclient.controllers.edit.EditAsesorController;
import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.utils.Observador;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Date;
import javafx.scene.effect.ColorAdjust;

public class AsesorController implements Observador {
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
    TableColumn<AsesorModel, String> columnaCarrera;
    @FXML
    TableColumn<AsesorModel, String> columnaEstado;
    @FXML
    TableColumn<AsesorModel, String> columnaContrato;
    @FXML
    Pagination paginadorAsesor;

    private AsesorRepository asesorRepository;

    public AsesorController(){
        asesorRepository = new AsesorRepository();
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
        columnaCarrera.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCarrera().getNombre())
        );
        columnaEstado.setCellValueFactory(cellData -> {
            boolean activo = cellData.getValue().getActivo();
            return new SimpleStringProperty(activo ? "Activo" : "No Activo");
        });
        columnaContrato.setCellValueFactory(new PropertyValueFactory<>("contrato"));

        paginadorAsesor.setPageFactory(this::updateTable);
    }

    public void loadAsesores(AsesorPage page){
        tablaAsesor.setItems(FXCollections.observableArrayList(page.getAsesores()));
    }

    public Node updateTable(int pageIndex){
        AsesorPage tmp = asesorRepository.search(pageIndex+1);
        if(tmp != null){
            loadAsesores(tmp);
            if(tmp.getPaginas() != paginadorAsesor.getPageCount()){
                paginadorAsesor.setPageCount(tmp.getPaginas());
            }
        }
        return tablaAsesor;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaAgregarAsesor.fxml"));
        Parent parent = loader.load();
        AddAsesorController controller = loader.getController();
        controller.agregarObservador(this);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Hacer que el Stage sea modal
        stage.initOwner(stage.getOwner());
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaEditarAsesor.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaAsesor.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
            AsesorModel asesorModel = tablaAsesor.getItems().get(rowIndex);

            boolean isActive = "Activo".equalsIgnoreCase(asesorModel.getActivoTexto());
            asesorModel.setActivo(isActive);

            EditAsesorController controller = loader.getController();
            controller.agregarObservador(this);
            controller.setModel(asesorModel);


            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.setMaximized(false);
            stage.setResizable(true);

            stage.show();
        }
    }

    @FXML
    private void delete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaConfirmacionAsesor.fxml"));
        Parent parent = loader.load();
        ConfirmacionAsesorController controller = loader.getController();
        controller.agregarObservador(this);

        int rowIndex = tablaAsesor.getSelectionModel().getSelectedIndex();

        if (rowIndex != -1) {
            AsesorModel asesorModel = tablaAsesor.getItems().get(rowIndex);
            ConfirmacionAsesorController confirmacionController = loader.getController();
            confirmacionController.setAsesor(asesorModel);

            GaussianBlur blurEffect = new GaussianBlur();
            blurEffect.setRadius(10);

            Stage mainStage = (Stage) tablaAsesor.getScene().getWindow();
            mainStage.getScene().getRoot().setEffect(blurEffect);

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setOnHiding(event -> mainStage.getScene().getRoot().setEffect(null));

            stage.show();
        }
    }


    @Override
    public void actualizar() {
        updateTable(paginadorAsesor.getCurrentPageIndex());
    }

}

package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.controllers.add.AddAsesoriaController;
import com.example.papasoftclient.controllers.delete.ConfirmacionAsesoriaController;
import com.example.papasoftclient.controllers.edit.EditAsesoriaController;
import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.AsesoriaRepository;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class AsesoriaController implements Observador {

    @FXML
    TableView<AsesoriaModel> tablaAsesoria;
    @FXML
    TableColumn<AsesoriaModel, String> columnaAsesor;
    @FXML
    TableColumn<AsesoriaModel, Date> columnaFecha;
    @FXML
    TableColumn<AsesoriaModel, Time> columnaHora;
    @FXML
    TableColumn<AsesoriaModel, String> columnaMateria;
    @FXML
    TableColumn<AsesoriaModel, String> columnaSalon;
    @FXML
    TableColumn<AsesoriaModel, String> columnaEstado;

    @FXML
    Pagination paginadorAsesoria;

    private AsesoriaRepository asesoriaRepository;

    public AsesoriaController(){ asesoriaRepository = new AsesoriaRepository(); }

    @FXML
    public void initialize() {
        columnaEstado.setCellValueFactory(cellData -> {
                    boolean confirmada = cellData.getValue().getConfirmada();
                    return new SimpleStringProperty(confirmada ? "Confirmada":"Sin confirmar");
            }
        );
        columnaAsesor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAsesor().getNombre())
        );
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        columnaMateria.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMateria().getNombre())
        );
        columnaSalon.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSalon().getNombre())
        );
        paginadorAsesoria.setPageFactory(this::updateTable);
    }

    public void loadAsesorias(AsesoriaPage page){
        tablaAsesoria.setItems(FXCollections.observableArrayList(page.getAsesorias()));
    }

    public Node updateTable(int pageIndex){
        AsesoriaPage tmp = asesoriaRepository.search(pageIndex+1);
        if(tmp != null){
            loadAsesorias(tmp);
            if(tmp.getPaginas()!=paginadorAsesoria.getPageCount()){

                paginadorAsesoria.setPageCount(tmp.getPaginas());
            }
        }
        return tablaAsesoria;
    }

    @FXML
    private void crear(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/asesoria/vistaAgregarAsesoria.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddAsesoriaController controller = fxmlLoader.getController();
        controller.agregarObservador(this);
        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.setResizable(true);

        newStage.show();
    }

    @FXML
    private void editar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesoria/vistaEditarAsesoria.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaAsesoria.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
            AsesoriaModel asesoria = tablaAsesoria.getItems().get(rowIndex);

            EditAsesoriaController editController = loader.getController();
            editController.setModel(asesoria);
            editController.agregarObservador(this);

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
    private void eliminar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesoria/vistaConfirmacionAsesoria.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaAsesoria.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
            AsesoriaModel asesoriaModel = tablaAsesoria.getItems().get(rowIndex);

            ConfirmacionAsesoriaController confirmacionController = loader.getController();
            confirmacionController.setAsesoria(asesoriaModel);
            confirmacionController.agregarObservador(this);

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.setMaximized(false);
            stage.setResizable(true);

            stage.show();
        }
    }

    @Override
    public void actualizar() {
        updateTable(paginadorAsesoria.getCurrentPageIndex());
    }
}

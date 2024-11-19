package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.controllers.add.AddMateriaController;
import com.example.papasoftclient.controllers.delete.ConfirmacionMateriaController;
import com.example.papasoftclient.controllers.edit.EditMateriaController;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MateriaController  implements Observador{

    @FXML
    private TableView<MateriaModel> tablaMaterias;
    @FXML
    private TableColumn<MateriaModel,String> columnaNombre;
    @FXML
    private TableColumn<MateriaModel,String> columnaCarrera;
    @FXML
    private Pagination paginadorMaterias;
    private MateriaRepository materiaRepository;

    public MateriaController(){
        materiaRepository = new MateriaRepository();
    }

    @FXML
    public void initialize() {

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCarrera.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCarrera().getNombre())
        );
        paginadorMaterias.setPageFactory(this::updateTable);
    }

    public void loadMaterias(MateriaPage page){
        tablaMaterias.setItems(FXCollections.observableArrayList(page.getMaterias()));
    }

    public Node updateTable(int pageIndex){
        MateriaPage tmp = materiaRepository.search(pageIndex+1);
        if(tmp != null){
            loadMaterias(tmp);
            if(tmp.getPaginas()!=paginadorMaterias.getPageCount()){
                paginadorMaterias.setPageCount(tmp.getPaginas());
            }
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

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/materia/vistaEditarMateria.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaMaterias.getSelectionModel().getSelectedIndex();
        MateriaModel materia = tablaMaterias.getItems().get(rowIndex);
        MateriaModel materiaBase = tablaMaterias.getItems().get(rowIndex);

        EditMateriaController editController = loader.getController();
        editController.setBase(materiaBase);
        editController.setModel(materia);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/materia/vistaConfirmacionMateria.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaMaterias.getSelectionModel().getSelectedIndex();
        MateriaModel materia = tablaMaterias.getItems().get(rowIndex);

        ConfirmacionMateriaController confirmacionController = loader.getController();
        confirmacionController.setMateria(materia);
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
        updateTable(paginadorMaterias.getCurrentPageIndex());
    }
}

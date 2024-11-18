package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.controllers.add.AddAsesoradoController;
import com.example.papasoftclient.controllers.edit.EditAsesoradoController;
import com.example.papasoftclient.controllers.delete.ConfirmacionAsesoradoController;
import com.example.papasoftclient.models.AsesoradoModel;
import com.example.papasoftclient.models.AsesoradoPage;
import com.example.papasoftclient.repositories.AsesoradoRepository;
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

public class AsesoradoController  implements Observador{

    @FXML
    private TableView<AsesoradoModel> tablaAsesorados;

    @FXML
    private TableColumn<AsesoradoModel,String> columnaNumCtrl;
    @FXML
    private TableColumn<AsesoradoModel,String> columnaApellidoP;
    @FXML
    private TableColumn<AsesoradoModel,String> columnaApellidoM;
    @FXML
    private TableColumn<AsesoradoModel,String> columnaNombre;

    @FXML
    private Pagination paginadorAsesorados;
    private AsesoradoRepository asesoradoRepository;

    public AsesoradoController(){
        asesoradoRepository  = new AsesoradoRepository();
    }

    @FXML
    public void initialize() {
        columnaNumCtrl.setCellValueFactory(new PropertyValueFactory<>("num_ctrl"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidoP.setCellValueFactory(new PropertyValueFactory<>("apellido_p"));
        columnaApellidoM.setCellValueFactory(new PropertyValueFactory<>("apellido_m"));
        paginadorAsesorados.setPageFactory(this::updateTable);
    }

    public void loadMaterias(AsesoradoPage page){
        tablaAsesorados.setItems(FXCollections.observableArrayList(page.getAsesorados()));
    }

    public Node updateTable(int pageIndex){
        AsesoradoPage tmp = asesoradoRepository.search(pageIndex+1);
        if(tmp != null){
            loadMaterias(tmp);
            paginadorAsesorados.setMaxPageIndicatorCount(tmp.getPaginas());
            paginadorAsesorados.setPageCount(tmp.getPaginas());
        }else System.out.println("La pagina es nula");
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

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesorado/vistaEditarAsesorado.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaAsesorados.getSelectionModel().getSelectedIndex();
        AsesoradoModel asesorado = tablaAsesorados.getItems().get(rowIndex);


        EditAsesoradoController editController = loader.getController();
        editController.setModel(asesorado);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesorado/vistaConfirmacionAsesorado.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaAsesorados.getSelectionModel().getSelectedIndex();
        AsesoradoModel asesorado = tablaAsesorados.getItems().get(rowIndex);

        ConfirmacionAsesoradoController confirmacionController = loader.getController();
        confirmacionController.setAsesorado(asesorado);

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
        updateTable(paginadorAsesorados.getCurrentPageIndex());
    }
}

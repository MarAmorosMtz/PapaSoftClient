package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.controllers.add.AddSalonController;
import com.example.papasoftclient.controllers.delete.ConfirmacionSalonController;
import com.example.papasoftclient.controllers.edit.EditSalonController;
import com.example.papasoftclient.models.SalonModel;
import com.example.papasoftclient.models.SalonPage;
import com.example.papasoftclient.repositories.SalonRepository;
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

public class SalonController  implements Observador{

    @FXML
    private TableView<SalonModel> tablaSalones;
    @FXML
    private TableColumn<SalonModel,String> columnaNombre;
    @FXML
    private Pagination paginadorSalones;
    private SalonRepository salonRepository;

    public SalonController(){
        salonRepository = new SalonRepository();
    }

    @FXML
    public void initialize() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        paginadorSalones.setPageFactory(this::updateTable);
    }

    public void loadMaterias(SalonPage page){
        tablaSalones.setItems(FXCollections.observableArrayList(page.getSalones()));
    }

    public Node updateTable(int pageIndex){
        SalonPage tmp = salonRepository.search(pageIndex+1);
        if(tmp != null){
            loadMaterias(tmp);
            if(tmp.getPaginas() != paginadorSalones.getPageCount()){
                paginadorSalones.setPageCount(tmp.getPaginas());
            }
        }
        return tablaSalones;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/salon/vistaAgregarSalon.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddSalonController controller = fxmlLoader.getController();
        controller.agregarObservador(this);
        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.setResizable(false);

        newStage.show();
    }

    @FXML
    private void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/salon/vistaEditarSalon.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaSalones.getSelectionModel().getSelectedIndex();
        SalonModel salon = tablaSalones.getItems().get(rowIndex);

        EditSalonController editController = loader.getController();
        editController.setModel(salon);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/salon/vistaConfirmacionSalon.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaSalones.getSelectionModel().getSelectedIndex();
        SalonModel salon = tablaSalones.getItems().get(rowIndex);

        ConfirmacionSalonController confirmacionController = loader.getController();
        confirmacionController.setSalon(salon);
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
        updateTable(paginadorSalones.getCurrentPageIndex());
    }
}

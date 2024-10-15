package com.example.papasoftclient.controllers;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.models.MateriaPage;
import com.example.papasoftclient.repositories.MateriaRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class MateriaController
{

    @FXML
    private TableView<MateriaModel> tablaMaterias;
    @FXML
    private TableColumn<MateriaModel, UUID> columnaId;
    @FXML
    private TableColumn<MateriaModel,String> columnaNombre;
    @FXML
    private TableColumn<MateriaModel,UUID> columnaCarrera;
    @FXML
    private Pagination paginadorMaterias;
    private CloseableHttpClient httpClient;
    private MateriaRepository materiaRepository;
    private ObjectMapper mapper;

    public MateriaController(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        materiaRepository = new MateriaRepository(httpClient, mapper ,RestAPI.MATERIAS_ENDPOINT);
    }

    @FXML
    public void initialize() {
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        paginadorMaterias.setPageFactory(this::updateTable);
        updateTable(1);
    }

    public void loadMaterias(MateriaPage page){
        tablaMaterias.setItems(FXCollections.observableArrayList(page.getMaterias()));
    }

    public Node updateTable(int pageIndex){
        MateriaPage tmp = materiaRepository.search(pageIndex);
        System.out.println("hola");
        if(tmp != null){
            loadMaterias(tmp);paginadorMaterias.setMaxPageIndicatorCount(tmp.getPaginas());
        }
        return tablaMaterias;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Materia/AgregarMateria.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.setResizable(false);

        newStage.show();
    }
}

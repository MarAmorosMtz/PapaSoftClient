package com.example.papasoftclient.controllers;

import com.example.papasoftclient.models.CarreraModel;
import com.example.papasoftclient.models.CarreraPage;
import com.example.papasoftclient.repositories.CarreraRepository;
import com.example.papasoftclient.repositories.RestAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.util.ArrayList;
import java.util.UUID;

public class CarreraController
{

    @FXML
    private TableView<CarreraModel> tablaCarreras;
    @FXML
    private TableColumn<CarreraModel, UUID> columnaId;
    @FXML
    private TableColumn<CarreraModel,String> columnaNombre;
    @FXML
    private Pagination paginadorCarreras;
    private CloseableHttpClient httpClient;
    private CarreraRepository carreraRepository;
    private ObjectMapper mapper;

    public CarreraController(){
        httpClient = HttpClients.createDefault();
        mapper = new ObjectMapper();
        carreraRepository = new CarreraRepository(httpClient, mapper ,RestAPI.CARRERAS_ENDPOINT);
    }

    @FXML
    public void initialize() {
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        paginadorCarreras.setPageFactory(this::updateTable);
        updateTable(1);
    }

    public void loadCarreras(CarreraPage page){
        tablaCarreras.setItems(FXCollections.observableArrayList(page.getCarreras()));
    }

    public Node updateTable(int pageIndex){
        CarreraPage tmp = carreraRepository.search(pageIndex);
        if(tmp != null){
            loadCarreras(tmp);
        }
        return tablaCarreras;
    }
}

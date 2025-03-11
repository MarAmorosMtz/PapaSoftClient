package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.models.AsesorModel;
import com.example.papasoftclient.models.AsesorPage;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.utils.Observador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Date;

public class TableViewAsesorController implements Observador {
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

    public TableViewAsesorController(){ asesorRepository = new AsesorRepository(); }

    @FXML
    public void initialize(){
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

    public AsesorModel getSelectedAsesor(){
        int rowIndex = tablaAsesor.getSelectionModel().getSelectedIndex();

        if (rowIndex != -1) {
            return tablaAsesor.getItems().get(rowIndex);
        }

        return null;
    }

    @Override
    public void actualizar() {
        updateTable(paginadorAsesor.getCurrentPageIndex());
    }
}

package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.controllers.add.AddAsesorMateriaController;
import com.example.papasoftclient.controllers.delete.ConfirmacionAsesorMateriaController;
import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.AsesorMateriaRepository;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.repositories.MateriaRepository;
import com.example.papasoftclient.utils.Observador;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
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
import java.util.ArrayList;
import java.util.UUID;

public class AsesorMateriaController implements Observador {

    @FXML
    private ComboBox<AsesorModel> comboAsesor;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView<AsesorMateriaModel> tablaMaterias;
    @FXML
    private TableColumn<AsesorMateriaModel, String> columnaMateria;
    @FXML
    private Pagination paginadorAsesorMateria;

    private AsesorMateriaRepository asesorMateriaRepository;
    private AsesorRepository asesorRepository;
    private MateriaRepository materiaRepository;

    private ChangeListener<Object> oyenteSleccion = (observable, oldValue, newValue) -> {
        actualizarTabla();
    };

    public AsesorMateriaController(){
        this.asesorMateriaRepository = new AsesorMateriaRepository();
        this.asesorRepository = new AsesorRepository();
        this.materiaRepository =  new MateriaRepository();
    }

    @FXML
    public void initialize(){

        //Modificacion del cellValueFactory para poder visualizar el nombre de la materia y no la id de la misma
        columnaMateria.setCellValueFactory(cellData -> {
            UUID materiaId = cellData.getValue().getMateria_id();
            String nombreMateria = "";
            if (materiaId != null) {
                MateriaModel materia = materiaRepository.search(materiaId);
                nombreMateria = (materia != null) ? materia.getNombre() : "Desconocido";
            }
            return new SimpleStringProperty(nombreMateria);
        });


        comboAsesor.valueProperty().addListener(oyenteSleccion);
        cargarAsesores();

        /*columnaMateria.setCellValueFactory(new PropertyValueFactory<>("materia_id"));
        comboAsesor.valueProperty().addListener(oyenteSleccion);
        cargarAsesores();*/
    }

    private boolean listo(){ return this.comboAsesor.getValue() != null;}

    @FXML
    public boolean cargarAsesores(){
        ArrayList<AsesorModel> listaAsesores = new ArrayList<AsesorModel>();
        AsesorPage tmp = this.asesorRepository.search(1);
        if(tmp != null) {
            this.comboAsesor.getItems().clear();
            int paginas = tmp.getPaginas();
            if(paginas > 0) {
                this.comboAsesor.getItems().addAll(FXCollections.observableArrayList(tmp.getAsesores()));
                for(int i=2; i<=paginas; i++) {
                    tmp = this.asesorRepository.search(i);
                    this.comboAsesor.getItems().addAll(FXCollections.observableArrayList(tmp.getAsesores()));
                }
                return true;
            }
        }
        return false;
    }

    public void cargarMaterias(AsesorMateriaPage page){
        tablaMaterias.setItems(FXCollections.observableArrayList(page.getMaterias()));
    }

    public Node actualizarTabla(){
        if(listo()){
            AsesorMateriaPage tmp = asesorMateriaRepository.all(
                    this.comboAsesor.getSelectionModel().getSelectedItem().getId()
            );
            if(tmp != null){
                cargarMaterias(tmp);
            }
        }
        return tablaMaterias;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if(listo()){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/asesorMateria/vistaAgregarAsesorMateria.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AddAsesorMateriaController controller = fxmlLoader.getController();
            controller.agregarObservador(this);
            controller.setAsesor(comboAsesor.getValue().getId());
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setResizable(true);
            newStage.show();
        }
    }

    @FXML
    private void edit(){}

    @FXML
    private void delete() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/asesorMateria/vistaConfirmacionAsesorMateria.fxml"));
        Parent parent = fxmlLoader.load();
        int rowIndex = tablaMaterias.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
            AsesorMateriaModel asesorMateriaModel = tablaMaterias.getItems().get(rowIndex);
            System.out.println(asesorMateriaModel);
            ConfirmacionAsesorMateriaController controller = fxmlLoader.getController();
            controller.setAsesorMateria(asesorMateriaModel);
            controller.agregarObservador(this);

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.setMaximized(false);
            stage.setResizable(true);

            stage.show();
        }
    }

    @FXML
    public void actualizar(){ actualizarTabla(); }
}

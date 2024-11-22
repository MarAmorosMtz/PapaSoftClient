package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.controllers.add.AddHorarioAsesorController;
import com.example.papasoftclient.controllers.delete.ConfirmacionHorarioAsesorController;
import com.example.papasoftclient.controllers.delete.ConfirmacionPeriodoController;
import com.example.papasoftclient.controllers.edit.EditHorarioAsesorController;
import com.example.papasoftclient.controllers.edit.EditPeriodoController;
import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.repositories.AsesoradoRepository;
import com.example.papasoftclient.repositories.HorariosAsesorRepository;
import com.example.papasoftclient.repositories.PeriodoRepository;
import com.example.papasoftclient.utils.Observador;
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
import java.util.Objects;


public class HorariosAsesorController implements Observador {

    @FXML
    private ComboBox<AsesorModel> comboAsesor;
    @FXML
    private ComboBox<PeriodoModel> comboPeriodo;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView<HorarioModel> tablaHorarios;
    @FXML
    private TableColumn<HorarioModel, String> columnaDiaLibre;
    @FXML
    private TableColumn<HorarioModel, String> columnaHoraLibre;
    @FXML
    private Pagination paginadorHorarios;

    private HorariosAsesorRepository horariosAsesorRepository;
    private AsesorRepository asesorRepository;
    private PeriodoRepository periodoRepository;

    private ChangeListener<Object> oyenteSeleccion = (observable, oldValue, newValue) -> {
        actualizarTabla();
    };

    public HorariosAsesorController() {
        this.horariosAsesorRepository = new HorariosAsesorRepository();
        this.asesorRepository = new AsesorRepository();
        this.periodoRepository = new PeriodoRepository();
    }

    @FXML
    public void initialize() {
        columnaDiaLibre.setCellValueFactory(new PropertyValueFactory<>("dia_libre"));
        columnaHoraLibre.setCellValueFactory(new PropertyValueFactory<>("hora_libre"));
        comboAsesor.valueProperty().addListener(oyenteSeleccion);
        comboPeriodo.valueProperty().addListener(oyenteSeleccion);
        cargarAsesores();
        cargarPeriodos();
    }

    private boolean listo(){
        return this.comboAsesor.getValue() != null && this.comboPeriodo.getValue() != null;
    }

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
    @FXML
    public boolean cargarPeriodos(){
        ArrayList<PeriodoModel> listaAsesores = new ArrayList<PeriodoModel>();
        PeriodoPage tmp = this.periodoRepository.search(1);
        if(tmp != null) {
            this.comboPeriodo.getItems().clear();
            int paginas = tmp.getPaginas();
            if(paginas > 0) {
                this.comboPeriodo.getItems().addAll(FXCollections.observableArrayList(tmp.getPeriodos()));
                for(int i=2; i<=paginas; i++) {
                    tmp = this.periodoRepository.search(i);
                    this.comboPeriodo.getItems().addAll(FXCollections.observableArrayList(tmp.getPeriodos()));
                }
                return true;
            }
        }
        return false;
    }

    public void cargarHorarios(HorarioAsesorPage page){
        tablaHorarios.setItems(FXCollections.observableArrayList(page.getHorarios()));
    }

    public Node actualizarTabla(){
        if(listo()){
            HorarioAsesorPage tmp = horariosAsesorRepository.all(
                    this.comboAsesor.getSelectionModel().getSelectedItem().getId(),
                    this.comboPeriodo.getSelectionModel().getSelectedItem().getId()
            );
            if(tmp != null){
                cargarHorarios(tmp);
            }
        }
        return tablaHorarios;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if(listo()){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/horariosAsesor/vistaAgregarHorariosAsesor.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AddHorarioAsesorController controller = fxmlLoader.getController();
            controller.agregarObservador(this);
            controller.setAsesor(this.comboAsesor.getSelectionModel().getSelectedItem().getId());
            controller.setPeriodo(this.comboPeriodo.getSelectionModel().getSelectedItem().getId());

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // Hacer que el Stage sea modal
            stage.initOwner(stage.getOwner());
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.setScene(scene);
            stage.show();
        }
    }


    @FXML
    private void edit() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/horariosAsesor/vistaEditarHorarioAsesor.fxml"));
        Parent parent = loader.load();
        int index = this.tablaHorarios.getSelectionModel().getSelectedIndex();
        if(listo() && index!=-1){
            HorarioModel horario = tablaHorarios.getItems().get(index);

            EditHorarioAsesorController editController = loader.getController();
            editController.agregarObservador(this);
            editController.setHorarioModel(horario);


            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.setMaximized(false);
            stage.setResizable(false);

            stage.show();
        }
    }

    @FXML
    private void delete() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/horariosAsesor/vistaConfirmacionHorariosAsesor.fxml"));
        Parent parent = loader.load();
        int index = this.tablaHorarios.getSelectionModel().getSelectedIndex();
        if(listo() && index!=-1){
            HorarioModel horario = tablaHorarios.getItems().get(index);
            ConfirmacionHorarioAsesorController confirmacionController = loader.getController();
            confirmacionController.agregarObservador(this);
            confirmacionController.setHorario(horario);

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.setMaximized(false);
            stage.setResizable(false);

            stage.show();
        }
    }

    @Override
    public void actualizar() {
        actualizarTabla();
    }
}

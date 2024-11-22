package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.Main;
import com.example.papasoftclient.controllers.add.AddHorarioAsesorController;
import com.example.papasoftclient.controllers.add.AddHorarioSalonController;
import com.example.papasoftclient.controllers.delete.ConfirmacionHorarioAsesorController;
import com.example.papasoftclient.controllers.delete.ConfirmacionHorarioSalonController;
import com.example.papasoftclient.controllers.edit.EditHorarioAsesorController;
import com.example.papasoftclient.controllers.edit.EditHorarioSalonController;
import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.*;
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


public class HorariosSalonController implements Observador {

    @FXML
    private ComboBox<SalonModel> comboSalon;
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

    private HorariosSalonRepository horariosSalonRepository;
    private SalonRepository salonRepository;
    private PeriodoRepository periodoRepository;

    private ChangeListener<Object> oyenteSeleccion = (observable, oldValue, newValue) -> {
        actualizarTabla();
    };

    public HorariosSalonController() {
        this.horariosSalonRepository = new HorariosSalonRepository();
        this.salonRepository = new SalonRepository();
        this.periodoRepository = new PeriodoRepository();
    }

    @FXML
    public void initialize() {
        columnaDiaLibre.setCellValueFactory(new PropertyValueFactory<>("dia_libre"));
        columnaHoraLibre.setCellValueFactory(new PropertyValueFactory<>("hora_libre"));
        comboSalon.valueProperty().addListener(oyenteSeleccion);
        comboPeriodo.valueProperty().addListener(oyenteSeleccion);
        cargarAsesores();
        cargarPeriodos();
    }

    private boolean listo(){
        return this.comboSalon.getValue() != null && this.comboPeriodo.getValue() != null;
    }

    @FXML
    public boolean cargarAsesores(){
        ArrayList<SalonModel> listaSalones = new ArrayList<SalonModel>();
        SalonPage tmp = this.salonRepository.search(1);
        if(tmp != null) {
            this.comboSalon.getItems().clear();
            int paginas = tmp.getPaginas();
            if(paginas > 0) {
                this.comboSalon.getItems().addAll(FXCollections.observableArrayList(tmp.getSalones()));
                for(int i=2; i<=paginas; i++) {
                    tmp = this.salonRepository.search(i);
                    this.comboSalon.getItems().addAll(FXCollections.observableArrayList(tmp.getSalones()));
                }
                return true;
            }
        }
        return false;
    }
    @FXML
    public boolean cargarPeriodos(){
        ArrayList<PeriodoModel> listaPeriodos = new ArrayList<PeriodoModel>();
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

    public void cargarHorarios(HorarioSalonPage page){
        tablaHorarios.setItems(FXCollections.observableArrayList(page.getHorarios()));
    }

    public Node actualizarTabla(){
        if(listo()){
            HorarioSalonPage tmp = horariosSalonRepository.all(
                    this.comboSalon.getSelectionModel().getSelectedItem().getId(),
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
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/papasoftclient/horariosSalon/vistaAgregarHorarioSalon.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AddHorarioSalonController controller = fxmlLoader.getController();
            controller.agregarObservador(this);
            controller.setSalon(this.comboSalon.getSelectionModel().getSelectedItem().getId());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/horariosSalon/vistaEditarHorarioSalon.fxml"));
        Parent parent = loader.load();
        int index = this.tablaHorarios.getSelectionModel().getSelectedIndex();
        if(listo() && index!=-1){
            HorarioModel horario = tablaHorarios.getItems().get(index);

            EditHorarioSalonController editController = loader.getController();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/horariosSalon/vistaConfirmacionHorarioSalon.fxml"));
        Parent parent = loader.load();
        int index = this.tablaHorarios.getSelectionModel().getSelectedIndex();
        if(listo() && index!=-1){
            HorarioModel horario = tablaHorarios.getItems().get(index);
            ConfirmacionHorarioSalonController confirmacionController = loader.getController();
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

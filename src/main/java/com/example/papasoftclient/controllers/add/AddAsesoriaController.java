package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.*;
import com.example.papasoftclient.utils.Observable;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddAsesoriaController extends Observable {

    @FXML
    private DatePicker selectorFecha;

    @FXML
    private ComboBox<MateriaModel> comboMateria;
    @FXML
    private ComboBox<String> comboHorario;
    @FXML
    private ComboBox<SalonModel> comboSalon;
    @FXML
    private ComboBox<AsesorModel> comboAsesor;
    @FXML
    private ComboBox<AsesoradoModel> comboAsesorado1,comboAsesorado2,comboAsesorado3,comboAsesorado4,comboAsesorado5;
    @FXML
    private ComboBox<MaestroModel> comboMaestro1,comboMaestro2,comboMaestro3,comboMaestro4,comboMaestro5;
    @FXML
    private TextField tema;
    @FXML
    private CheckBox confirmada;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<PeriodoModel> comboPeriodo;

    private AsesorRepository asesorRepository;
    private AsesorMateriaRepository asesorMateriaRepository;
    private SalonRepository salonRepository;
    private AsesoriaRepository asesoriaRepository;
    private AsesoradoRepository asesoradoRepository;
    private MaestroRepository maestroRepository;
    private PeriodoRepository periodoRepository;
    private MateriaRepository materiaRepository;

    public AddAsesoriaController() {
        materiaRepository = new MateriaRepository();
        asesorMateriaRepository = new AsesorMateriaRepository();
        asesorRepository = new AsesorRepository();
        salonRepository = new SalonRepository();
        asesoriaRepository = new AsesoriaRepository();
        maestroRepository = new MaestroRepository();
        asesoradoRepository = new AsesoradoRepository();
        periodoRepository = new PeriodoRepository();
    }

    public void initialize(){
        comboPeriodo.valueProperty().addListener(oyentePeriodo);
        selectorFecha.valueProperty().addListener(oyenteFecha);
        comboHorario.valueProperty().addListener(oyenteHorario);
        comboMateria.valueProperty().addListener(oyenteMateria);
        cargarPeriodos();
        cargarHoras(this.comboHorario);
        cargarMaterias();
        cargarAsesorados();
        cargarMaestros();
    }

    private ChangeListener<Object> oyentePeriodo = ((observable, oldValue, newValue) -> {
        if(selectorFecha.getValue() != null && comboHorario.getValue() != null){
            if(comboMateria.getValue()!=null){
                cargarAsesores();
                comboAsesor.setDisable(false);
            }
            cargarSalones();
            comboSalon.setDisable(false);
        }
    });

    private ChangeListener<Object> oyenteHorario = ((observable, oldValue, newValue) -> {
        if (comboPeriodo.getValue()!=null && selectorFecha.getValue() != null) {
            if(comboMateria.getValue()!=null){
                cargarAsesores();
                comboAsesor.setDisable(false);
            }
            cargarSalones();
            comboSalon.setDisable(false);
        }
    });

    private ChangeListener<Object> oyenteFecha = ((observable, oldValue, newValue) -> {
        if (comboPeriodo.getValue()!=null && comboHorario.getValue() != null) {
            if(comboMateria.getValue()!=null){
                cargarAsesores();
                comboAsesor.setDisable(false);
            }
            cargarSalones();
            comboSalon.setDisable(false);
        }
    });

    private ChangeListener<Object> oyenteMateria = ((observable, oldValue, newValue) -> {
        if(comboPeriodo.getValue()!=null && selectorFecha.getValue()!=null && comboHorario.getValue()!=null){
            cargarAsesores();
            comboAsesor.setDisable(false);
        }
    });

    public void cargarPeriodos(){
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
            }
        }
    }

    public void cargarHoras(ComboBox<String> comboBox) {
        comboBox.getItems().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (int hour = 8; hour <= 18; hour++) {
            comboBox.getItems().add(LocalTime.of(hour, 0).format(formatter));
        }
    }

    private void cargarMaterias(){
        MateriaPage pagina = this.materiaRepository.search(1);
        ArrayList<MateriaModel> materias = new ArrayList<>();
        for(MateriaModel m: pagina.getMaterias()){
            materias.add(materiaRepository.search(m.getId()));
        }
        if(pagina != null){
            this.comboMateria.setItems(FXCollections.observableArrayList(materias));
            for(int i=2; i<=pagina.getPaginas(); i++){
                this.comboMateria.getItems().addAll(materias);
            }
        }
    }

    private void cargarAsesores(){
        String selectedHorario = comboHorario.getSelectionModel().getSelectedItem();
        LocalDate selectedFecha = selectorFecha.getValue();

        AsesorPage pagina = this.asesorRepository.filterByDateHourAndSubject(1, selectedFecha,selectedHorario, comboMateria.getValue().getId());
        if(pagina != null){
            this.comboAsesor.setItems(FXCollections.observableArrayList(pagina.getAsesores()));
            for(int i=2; i<=pagina.getPaginas(); i++){
                this.comboAsesor.getItems().addAll(pagina.getAsesores());
            }
        }
    }

    private void cargarSalones(){
        String selectedHorario = comboHorario.getSelectionModel().getSelectedItem();
        LocalDate selectedFecha = selectorFecha.getValue();

        SalonPage pagina = this.salonRepository.filterByDateAndHour(1, selectedFecha, selectedHorario);
        if(pagina != null){
            this.comboSalon.setItems(FXCollections.observableArrayList(pagina.getSalones()));
            for(int i=2; i<=pagina.getPaginas(); i++){
                this.comboSalon.getItems().addAll(pagina.getSalones());
            }
        }
    }

    private void cargarMaestros(){
        MaestroPage pagina = this.maestroRepository.search(1);
        ArrayList<MaestroModel> maestros = new ArrayList<MaestroModel>();
        if(pagina != null){
            maestros.addAll(pagina.getMaestros());
            for(int i=2; i<=pagina.getPaginas(); i++){
                maestros.addAll(maestroRepository.search(i).getMaestros());
            }
        }
        this.comboMaestro1.setItems(FXCollections.observableArrayList(maestros));
        this.comboMaestro2.setItems(FXCollections.observableArrayList(maestros));
        this.comboMaestro3.setItems(FXCollections.observableArrayList(maestros));
        this.comboMaestro4.setItems(FXCollections.observableArrayList(maestros));
        this.comboMaestro5.setItems(FXCollections.observableArrayList(maestros));
    }

    private void cargarAsesorados(){
        AsesoradoPage pagina = this.asesoradoRepository.search(1);
        ArrayList<AsesoradoModel> asesorados = new ArrayList<>();
        if(pagina != null){
            asesorados.addAll(pagina.getAsesorados());
            for(int i=2; i<=pagina.getPaginas(); i++){
                asesorados.addAll(asesoradoRepository.search(i).getAsesorados());
            }
        }
        this.comboAsesorado1.setItems(FXCollections.observableArrayList(asesorados));
        this.comboAsesorado2.setItems(FXCollections.observableArrayList(asesorados));
        this.comboAsesorado3.setItems(FXCollections.observableArrayList(asesorados));
        this.comboAsesorado4.setItems(FXCollections.observableArrayList(asesorados));
        this.comboAsesorado5.setItems(FXCollections.observableArrayList(asesorados));
    }

    @FXML
    private void guardar(){
        AsesoriaBase asesoria = new AsesoriaBase();
        int err = 0;

        if(err == 0){

            ArrayList<DetalleAsesoriaModel> listaAsesorados = new ArrayList<>();
            if(this.comboAsesorado1.getSelectionModel().getSelectedItem() != null && this.comboMaestro1.getSelectionModel().getSelectedItem() != null){
                listaAsesorados.add(new DetalleAsesoriaModel(this.comboAsesorado1.getValue().getId(),this.comboMaestro1.getValue().getId()));
            }
            if(this.comboAsesorado2.getSelectionModel().getSelectedItem() != null && this.comboMaestro2.getSelectionModel().getSelectedItem() != null){
                listaAsesorados.add(new DetalleAsesoriaModel(this.comboAsesorado2.getValue().getId(),this.comboMaestro2.getValue().getId()));
            }
            if (this.comboAsesorado3.getSelectionModel().getSelectedItem() != null && this.comboMaestro3.getSelectionModel().getSelectedItem() != null){
                listaAsesorados.add(new DetalleAsesoriaModel(this.comboAsesorado3.getValue().getId(),this.comboMaestro3.getValue().getId()));
            }
            if(this.comboAsesorado4.getSelectionModel().getSelectedItem() != null && this.comboMaestro4.getSelectionModel().getSelectedItem() != null){
                listaAsesorados.add(new DetalleAsesoriaModel(this.comboAsesorado4.getValue().getId(),this.comboMaestro4.getValue().getId()));
            }
            if(this.comboAsesorado5.getSelectionModel().getSelectedItem() != null && this.comboMaestro5.getSelectionModel().getSelectedItem() != null){
                listaAsesorados.add(new DetalleAsesoriaModel(this.comboAsesorado5.getValue().getId(),this.comboMaestro5.getValue().getId()));
            }

            asesoria = new AsesoriaBase(
                    comboPeriodo.getValue().getId(),
                    selectorFecha.getValue(),
                    comboHorario.getValue(),
                    comboMateria.getValue().getId(),
                    comboSalon.getValue().getId(),
                    comboAsesor.getValue().getId(),
                    tema.getText(),
                    confirmada.isSelected(),
                    listaAsesorados
            );
            asesoriaRepository.save(asesoria);
            this.notificar();
            cancelar();
        }

    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }

}

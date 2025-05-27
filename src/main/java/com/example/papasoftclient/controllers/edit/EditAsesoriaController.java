package com.example.papasoftclient.controllers.edit;

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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.UUID;

public class EditAsesoriaController extends Observable {
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
    private CheckBox confirmada;
    @FXML
    private TextField tema;
//    @FXML
//    private ComboBox<AsesoradoModel> comboAsesorado1,comboAsesorado2,comboAsesorado3,comboAsesorado4,comboAsesorado5;
//    @FXML
//    private ComboBox<MaestroModel> comboMaestro1,comboMaestro2,comboMaestro3,comboMaestro4,comboMaestro5;
    @FXML
    private Button btnCancelar;

    private ChangeListener<Object> oyenteHorario = ((observable, oldValue, newValue) -> {
        if (selectorFecha.getValue() != null) {
            cargarAsesores();
            cargarSalones();
        }
    });

    private ChangeListener<Object> oyenteFecha = ((observable, oldValue, newValue) -> {
        if (comboHorario.getValue() != null) {
            cargarAsesores();
            cargarSalones();
        }
    });

    private AsesorRepository asesorRepository;
    private MateriaRepository materiaRepository;
    private SalonRepository salonRepository;
    private AsesoriaRepository asesoriaRepository;
    private AsesoradoRepository asesoradoRepository;
    private MaestroRepository maestroRepository;
    private AsesoriaModel asesoriaModel;
    private AsesorMateriaRepository asesorMateriaRepository;
    private ArrayList<DetalleAsesoradoModel> listaAsesorados = new ArrayList<>();

    public EditAsesoriaController() {
        materiaRepository = new MateriaRepository();
        asesorRepository = new AsesorRepository();
        salonRepository = new SalonRepository();
        asesoriaRepository = new AsesoriaRepository();
//        maestroRepository = new MaestroRepository();
//        asesoradoRepository = new AsesoradoRepository();
    }

    public void initialize(){
        comboHorario.valueProperty().addListener(oyenteHorario);
        selectorFecha.valueProperty().addListener(oyenteFecha);
        //cargarSalones();
        //cargarAsesores();
//        cargarMaestros();
//        cargarAsesorados();
        cargarHoras(this.comboHorario);
    }

    public AsesoriaModel getAsesoriaModel() {
        return asesoriaModel;
    }

    public void cargarHoras(ComboBox<String> comboBox) {
        comboBox.getItems().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (int hour = 8; hour <= 18; hour++) {
            comboBox.getItems().add(LocalTime.of(hour, 0).format(formatter));
        }
    }

    private void cargarAsesores(){
        String selectedHorario = comboHorario.getSelectionModel().getSelectedItem();
        LocalDate selectedFecha = selectorFecha.getValue();

        int dia = selectedFecha.getDayOfMonth();
        int mes = selectedFecha.getMonthValue();
        int ano = selectedFecha.getYear();

        LocalTime parsedTime;
        try {
            DateTimeFormatter timeFormatterWithSeconds = DateTimeFormatter.ofPattern("HH:mm:ss");
            parsedTime = LocalTime.parse(selectedHorario, timeFormatterWithSeconds);
        } catch (DateTimeParseException e) {
            DateTimeFormatter timeFormatterWithoutSeconds = DateTimeFormatter.ofPattern("HH:mm");
            parsedTime = LocalTime.parse(selectedHorario, timeFormatterWithoutSeconds);
        }
        int hora = parsedTime.getHour();
        int minuto = parsedTime.getMinute();

        /*AsesorPage pagina = this.asesorRepository.searchFiltrado(asesoriaModel.getPeriodo(),1, dia, mes, ano, hora, minuto);
        if(pagina != null){
            this.comboAsesor.setItems(FXCollections.observableArrayList(pagina.getAsesores()));
            for(int i=2; i<=pagina.getPaginas(); i++){
                this.comboAsesor.getItems().addAll(pagina.getAsesores());
            }
        }*/
    }

    private void cargarMaterias(UUID asesor){
        AsesorMateriaPage pagina = this.asesorMateriaRepository.all(asesor);
        ArrayList<MateriaModel> materias = new ArrayList<>();
        for(AsesorMateriaModel m: pagina.getMaterias()){
            materias.add(materiaRepository.search(m.getMateria_id()));
        }
        if(pagina != null){
            this.comboMateria.setItems(FXCollections.observableArrayList(materias));
            for(int i=2; i<=pagina.getPaginas(); i++){
                this.comboMateria.getItems().addAll(materias);
            }
        }
    }

    private void cargarSalones(){
        String selectedHorario = comboHorario.getSelectionModel().getSelectedItem();
        LocalDate selectedFecha = selectorFecha.getValue();

        int dia = selectedFecha.getDayOfMonth();
        int mes = selectedFecha.getMonthValue();
        int ano = selectedFecha.getYear();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime parsedTime = LocalTime.parse(selectedHorario, timeFormatter);
        int hora = parsedTime.getHour();
        int minuto = parsedTime.getMinute();

        SalonPage pagina = this.salonRepository.searchFiltrado(asesoriaModel.getPeriodo(),1, dia, mes, ano, hora, minuto);
        if(pagina != null){
            this.comboSalon.setItems(FXCollections.observableArrayList(pagina.getSalones()));
            for(int i=2; i<=pagina.getPaginas(); i++){
                this.comboSalon.getItems().addAll(pagina.getSalones());
            }
        }
    }

//    private void cargarMaestros(){
//        MaestroPage pagina = this.maestroRepository.search(1);
//        ArrayList<MaestroModel> maestros = new ArrayList<MaestroModel>();
//        if(pagina != null){
//            maestros.addAll(pagina.getMaestros());
//            for(int i=2; i<=pagina.getPaginas(); i++){
//                maestros.addAll(pagina.getMaestros());
//            }
//        }
//        this.comboMaestro1.setItems(FXCollections.observableArrayList(maestros));
//        this.comboMaestro2.setItems(FXCollections.observableArrayList(maestros));
//        this.comboMaestro3.setItems(FXCollections.observableArrayList(maestros));
//        this.comboMaestro4.setItems(FXCollections.observableArrayList(maestros));
//        this.comboMaestro5.setItems(FXCollections.observableArrayList(maestros));
//    }
//
//    private void cargarAsesorados(){
//        AsesoradoPage pagina = this.asesoradoRepository.search(1);
//        ArrayList<AsesoradoModel> asesorados = new ArrayList<>();
//        if(pagina != null){
//            asesorados.addAll(pagina.getAsesorados());
//            for(int i=2; i<=pagina.getPaginas(); i++){
//                asesorados.addAll(pagina.getAsesorados());
//            }
//        }
//        this.comboAsesorado1.setItems(FXCollections.observableArrayList(asesorados));
//        this.comboAsesorado2.setItems(FXCollections.observableArrayList(asesorados));
//        this.comboAsesorado3.setItems(FXCollections.observableArrayList(asesorados));
//        this.comboAsesorado4.setItems(FXCollections.observableArrayList(asesorados));
//        this.comboAsesorado5.setItems(FXCollections.observableArrayList(asesorados));
//    }

    public void setModel(AsesoriaModel model) {
        this.asesoriaModel = asesoriaRepository.search(model.getId());
        selectorFecha.setValue(this.asesoriaModel.getFecha());
        comboMateria.setValue(this.asesoriaModel.getMateria());
        comboHorario.setValue(this.asesoriaModel.getHora());
        comboSalon.setValue(this.asesoriaModel.getSalon());
        comboAsesor.setValue(this.asesoriaModel.getAsesor());
        confirmada.setSelected(this.asesoriaModel.getConfirmada());
        tema.setText(this.asesoriaModel.getTema());
        this.listaAsesorados = this.asesoriaModel.getAsesorados();

        cargarAsesores();
        cargarSalones();
        //cargarMaterias(asesoriaModel.getAsesor().getId());
    }

    @FXML
    private void guardar() {
        AsesoriaBase asesoria;

        asesoria = new AsesoriaBase(
                selectorFecha.getValue(),
                comboHorario.getValue(),
                comboMateria.getValue().getId(),
                comboSalon.getValue().getId(),
                comboAsesor.getValue().getId(),
                tema.getText(),
                confirmada.isSelected(),
                listaAsesorados
        );
        asesoriaRepository.update(this.asesoriaModel.getId(),asesoria);
        this.notificar();
        cancelar();
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }
}

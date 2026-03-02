package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.*;
import com.example.papasoftclient.utils.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.stage.Stage;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class NewEditAsesoriaController extends Observable {
    private boolean editMode;
    private UUID idAsesoria;

    @FXML
    private ComboBox<PeriodoModel> selectorPeriodo;
    @FXML
    private DatePicker selectorFecha;
    @FXML
    private ComboBox<String> selectorHora;
    @FXML
    private ComboBox<SalonModel> selectorSalon;
    @FXML
    private ComboBox<MateriaModel> selectorMateria;
    @FXML
    private ComboBox<AsesorModel> selectorAsesor;
    @FXML
    private TextField campoTema;
    @FXML
    private CheckBox verificadorAsesoria;

    @FXML
    private ComboBox<String> selectorFiltros;
    @FXML
    private TextField campoBusqueda;
    @FXML
    private Button botonBuscar;

    //Tabla de búsqueda
    @FXML
    private TableView<AsesoradoModel> tablaBusquedaAsesorados;
    @FXML
    private TableColumn<AsesoradoModel, String> columnaControlBusqueda;
    @FXML
    private TableColumn<AsesoradoModel, String> columnaNombreBusqueda;
    @FXML
    private Pagination paginadorBusqueda;

    @FXML
    private Button botonAgregarAlumno;
    @FXML
    private Button botonEliminarAlumno;

    //Tabla asesorados
    @FXML
    private TableView<DetalleAsesoriaExpanded> tablaAsesoradosAgregados;
    @FXML
    private TableColumn<DetalleAsesoriaExpanded, String> columnaControlAsesorados;
    @FXML
    private TableColumn<DetalleAsesoriaExpanded, String> columnaNombreAsesorados;
    @FXML
    private TableColumn<DetalleAsesoriaExpanded, MaestroModel> columnaMaestroAsesorados;

    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonGuardarAsesoria;

    private ObservableList<PeriodoModel> catalogoPeriodo;
    private ObservableList<MateriaModel> catalogoMaterias;
    //private ObservableList<AsesorModel> catalogoAsesores;
    //private ObservableList<SalonModel> catalogoSalones;
    private ObservableList<MaestroModel> catalogoMaestros;

    private final PeriodoRepository periodoRepository;
    private final SalonRepository salonRepository;
    private final MateriaRepository materiaRepository;
    private final AsesorRepository asesorRepository;
    private final MaestroRepository maestroRepository;
    private final AsesoradoRepository asesoradoRepository;
    private final AsesoriaRepository asesoriaRepository;


    public NewEditAsesoriaController() {
        this.periodoRepository = new PeriodoRepository();
        this.salonRepository = new SalonRepository();
        this.materiaRepository = new MateriaRepository();
        this.asesorRepository = new AsesorRepository();
        this.maestroRepository = new MaestroRepository();
        this.asesoradoRepository = new AsesoradoRepository();
        this.asesoriaRepository = new AsesoriaRepository();

        this.catalogoPeriodo = FXCollections.observableArrayList();
        this.catalogoMaterias = FXCollections.observableArrayList();
        //this.catalogoSalones = FXCollections.observableArrayList();
        //this.catalogoAsesores = FXCollections.observableArrayList();
        this.catalogoMaestros = FXCollections.observableArrayList();
    }

    public NewEditAsesoriaController(boolean editMode){
        this();
        this.editMode = editMode;
    }

    @FXML
    public void initialize() {
        columnaControlBusqueda.setCellValueFactory(data -> {
            return new SimpleStringProperty(data.getValue().getNum_ctrl());
        });
        columnaNombreBusqueda.setCellValueFactory(data -> {
            return new SimpleStringProperty(data.getValue().getNombre()+" "+data.getValue().getApellido_p()+" "+data.getValue().getApellido_m());
        });
        columnaControlAsesorados.setCellValueFactory(data -> {
            return new SimpleStringProperty(data.getValue().getAsesorado().getNum_ctrl());
        });
        columnaNombreAsesorados.setCellValueFactory(data -> {
            return new SimpleStringProperty(data.getValue().getAsesorado().getNombre()+" "+data.getValue().getAsesorado().getApellido_p());
        });

        columnaMaestroAsesorados.setCellValueFactory(data -> {
            return new SimpleObjectProperty<MaestroModel>(data.getValue().getMaestro());
        });

        columnaMaestroAsesorados.setCellFactory(ComboBoxTableCell.forTableColumn(catalogoMaestros));

        columnaMaestroAsesorados.setOnEditCommit(event -> {
            event.getRowValue().setMaestro(event.getNewValue());
        });

        selectorPeriodo.valueProperty().addListener(oyentePeriodo);
        selectorFecha.valueProperty().addListener(oyenteFecha);
        selectorHora.valueProperty().addListener(oyenteHorario);
        selectorMateria.valueProperty().addListener(oyenteMateria);

        cargarFiltros();
        cargarPeriodos();
        cargarMaterias();
        cargarHorarios();
        cargarMaestros();

        paginadorBusqueda.setPageFactory(pageIndex -> {
            cargarResultadosBusqueda(pageIndex);
            return null;
        });
    }

    private ChangeListener<Object> oyentePeriodo = ((observable, oldValue, newValue) -> {
        if(selectorFecha.getValue() != null && selectorHora.getValue() != null){
            if(selectorMateria.getValue()!=null){
                selectorAsesor.getSelectionModel().clearSelection();
                cargarAsesores();
            }
            selectorSalon.getSelectionModel().clearSelection();
            cargarSalones();
        }
    });

    private ChangeListener<Object> oyenteFecha = ((observable, oldValue, newValue) -> {
        if (selectorPeriodo.getValue()!=null && selectorHora.getValue() != null) {
            if(selectorMateria.getValue()!=null){
                selectorAsesor.getSelectionModel().clearSelection();
                cargarAsesores();
            }
            selectorSalon.getSelectionModel().clearSelection();
            cargarSalones();
        }
    });

    private ChangeListener<Object> oyenteHorario = ((observable, oldValue, newValue) -> {
        if (selectorPeriodo.getValue()!=null && selectorFecha.getValue() != null) {
            if(selectorMateria.getValue()!=null){
                selectorAsesor.getSelectionModel().clearSelection();
                cargarAsesores();
            }
            selectorSalon.getSelectionModel().clearSelection();
            cargarSalones();
        }
    });

    private ChangeListener<Object> oyenteMateria = ((observable, oldValue, newValue) -> {
        if(selectorPeriodo.getValue()!=null && selectorFecha.getValue()!=null && selectorHora.getValue()!=null){
            selectorAsesor.getSelectionModel().clearSelection();
            cargarAsesores();
        }
    });

    private void cargarPeriodos(){
        catalogoPeriodo.addAll(periodoRepository.getCatalogoPeriodo());
        selectorPeriodo.setItems(catalogoPeriodo);
    }

    private void cargarMaestros(){
        MaestroPage page = maestroRepository.search(1);
        if(page!=null){
            catalogoMaestros.addAll(page.getMaestros());
            int totalCount = page.getPaginas();
            for(int i=2; i<=totalCount; i++){
                page = maestroRepository.search(i);
                catalogoMaestros.addAll(page.getMaestros());
            }
        }
    }

    private void cargarHorarios(){
        selectorHora.getItems().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (int hour = 8; hour <= 18; hour++) {
            selectorHora.getItems().add(LocalTime.of(hour, 0).format(formatter));
        }
    }

    private void cargarMaterias(){
        MateriaPage page = materiaRepository.search(1);
        if(page!=null){
            catalogoMaterias.addAll(page.getMaterias());
            int totalCount = page.getPaginas();
            for(int i=2; i<=totalCount; i++){
                page = materiaRepository.search(i);
                catalogoMaterias.addAll(page.getMaterias());
            }
            selectorMateria.setItems(catalogoMaterias);
        }
    }

    private void cargarSalones(){
        System.out.println("Recargando salones...");
        if(selectorFecha.getValue()==null || selectorHora.getValue()==null)return;
        this.limpiarSeleccionSalon();
        SalonPage page = salonRepository.filterByDateAndHour(1, selectorFecha.getValue(), selectorHora.getValue());
        if(page!=null){
            selectorSalon.setItems(FXCollections.observableArrayList(page.getSalones()));
            int totalCount = page.getPaginas();
            for(int i=2; i<=totalCount; i++){
                page = salonRepository.filterByDateAndHour(i, selectorFecha.getValue(), selectorHora.getValue());
                selectorSalon.getItems().addAll(page.getSalones());
            }
        }
    }

    private void cargarAsesores(){
        System.out.println("Recargando asesores...");
        if(selectorFecha.getValue()==null || selectorHora.getValue()==null || selectorMateria.getValue()==null)return;
        this.limpiarSeleccionAsesor();
        AsesorPage page = asesorRepository.filterByDateHourAndSubject(1, selectorFecha.getValue(),selectorHora.getValue(), selectorMateria.getValue().getId());
        if(page!=null){
            selectorAsesor.setItems(FXCollections.observableArrayList(page.getAsesores()));
            int totalCount = page.getPaginas();
            for(int i=2; i<=totalCount; i++){
                page = asesorRepository.filterByDateHourAndSubject(i, selectorFecha.getValue(),selectorHora.getValue(), selectorMateria.getValue().getId());
                selectorAsesor.getItems().addAll(page.getAsesores());
            }
        }
    }

    private void cargarFiltros(){
        selectorFiltros.getItems().addAll("Número de control","Nombres","Apellidos");
    }


    @FXML
    private void buscarAsesoradoAction(){
        if(campoBusqueda.getText()==null || campoBusqueda.getText().isEmpty())return;
        cargarResultadosBusqueda(0);
    }

    private void cargarResultadosBusqueda(int pagina){
        String texto = campoBusqueda.getText().trim();
        if(texto.isEmpty()) {
            limpiarResultadosBusqueda();
            return;
        }

        AsesoradoPage resultados = null;

        if(selectorFiltros.getValue().equals("Número de control")){
            resultados = asesoradoRepository.searchByControl(texto, pagina+1);
        }

        if(selectorFiltros.getValue().equals("Nombres")){
            resultados = asesoradoRepository.searchByName(texto, pagina+1);
        }

        if(selectorFiltros.getValue().equals("Apellidos")){
            resultados = asesoradoRepository.searchByLastname(texto, pagina+1);
        }

        if(resultados!=null){
            tablaBusquedaAsesorados.getItems().setAll(FXCollections.observableArrayList(resultados.getAsesorados()));
        }
    }

    private void limpiarResultadosBusqueda(){
        tablaBusquedaAsesorados.getItems().clear();
        paginadorBusqueda.setPageCount(0);
    }

    @FXML
    private void agregarAsesoradoAction(){
        if(this.tablaBusquedaAsesorados.getSelectionModel().getSelectedIndex()==-1)return;
        AsesoradoSearchResponse nuevoAsesorado = new AsesoradoSearchResponse(this.tablaBusquedaAsesorados.getSelectionModel().getSelectedItem());
        for(DetalleAsesoriaExpanded detalle : this.tablaAsesoradosAgregados.getItems()){
            if(detalle.getAsesorado().getId() == nuevoAsesorado.getId())return;
        }
        this.tablaAsesoradosAgregados.getItems().add(
                new DetalleAsesoriaExpanded(nuevoAsesorado,null)
        );
    }

    @FXML
    private void eliminarAsesoradoAction(){
        if(this.tablaAsesoradosAgregados.getSelectionModel().getSelectedIndex()!=-1){
            this.tablaAsesoradosAgregados.getItems().remove(this.tablaAsesoradosAgregados.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void guardarAsesoriaAction(){
        if(editMode){//Modo editar
            System.out.println("Actualizando...");
            asesoriaRepository.update(this.idAsesoria,this.getBaseModel());
        }else {//Modo crear
            asesoriaRepository.save(this.getBaseModel());
            System.out.println("Guardando...");
        }
        this.notificar();
        cancelarAction();
    }

    @FXML
    private void cancelarAction(){
        Stage stage = (Stage)botonCancelar.getScene().getWindow();
        stage.close();
    }

    public void setModel(AsesoriaModel asesoria){
        this.idAsesoria = asesoria.getId();
        this.selectorPeriodo.setValue(asesoria.getPeriodo());
        this.selectorFecha.setValue(asesoria.getFecha());
        this.selectorHora.setValue(asesoria.getHora());
        this.selectorSalon.setValue(asesoria.getSalon());
        this.selectorMateria.setValue(asesoria.getMateria());
        this.selectorAsesor.setValue(asesoria.getAsesor());
        this.campoTema.setText(asesoria.getTema());
        this.verificadorAsesoria.setSelected(asesoria.getConfirmada());

        tablaAsesoradosAgregados.setItems(FXCollections.observableArrayList(asesoria.getAsesorados()));
    }

    public AsesoriaBase getBaseModel(){
        return new AsesoriaBase(
                this.selectorPeriodo.getValue().getId(),
                this.selectorFecha.getValue(),
                this.selectorHora.getValue(),
                this.selectorMateria.getValue().getId(),
                this.selectorSalon.getValue().getId(),
                this.selectorAsesor.getValue().getId(),
                this.campoTema.getText().trim(),
                this.verificadorAsesoria.isSelected(),
                this.tablaAsesoradosAgregados.getItems().stream().map(
x -> new DetalleAsesoriaModel(
                        x.getAsesorado().getId(), x.getMaestro().getId())).collect(Collectors.toCollection(ArrayList::new))
                );
    }

    private void limpiarSeleccionAsesor(){
        this.selectorAsesor.getSelectionModel().clearSelection();
        this.selectorAsesor.setValue(null);
    }

    private void limpiarSeleccionSalon(){
        this.selectorSalon.getSelectionModel().clearSelection();
        this.selectorSalon.setValue(null);
    }

    private void clear(){
        this.selectorPeriodo.setValue(null);
        this.selectorFecha.setValue(null);
        this.selectorHora.setValue(null);
        this.selectorMateria.setValue(null);
        this.selectorSalon.setValue(null);
        this.selectorAsesor.setValue(null);
        this.campoTema.setText(null);
        this.verificadorAsesoria.setSelected(false);
        this.campoBusqueda.setText(null);
        this.tablaAsesoradosAgregados.setItems(null);
        this.tablaAsesoradosAgregados.setItems(null);
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
        if(editMode){
            this.selectorAsesor.setDisable(false);
            this.selectorSalon.setDisable(false);
        }
    }
}

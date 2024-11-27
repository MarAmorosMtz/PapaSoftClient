package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.HorariosSalonRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EditHorarioSalonController extends Observable {
    @FXML
    private ComboBox<String> comboDiaLibre;
    @FXML
    private ComboBox<String> comboHoraLibre;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    private HorariosSalonRepository horariosSalonRepository;
    private HorarioSalonModel horarioModel;
    private HorarioSalonPage horarioSalonPage;

    private ChangeListener<Object> oyenteSeleccion = (observable, oldValue, newValue) -> {
        actualizarHoras();
    };

    public EditHorarioSalonController() {
        horariosSalonRepository = new HorariosSalonRepository();
    }

    public void initialize(){
        comboDiaLibre.valueProperty().addListener(oyenteSeleccion);
        cargarDiasSemana(comboDiaLibre);
        cargarHoras(comboHoraLibre);
    }

    private boolean listo(){
        return this.comboDiaLibre.getValue() != null;
    }

    public void cargarDiasSemana(ComboBox<String> comboBox) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll("Lunes", "Martes", "Miércoles", "Jueves", "Viernes");
    }

    public void cargarHoras(ComboBox<String> comboBox) {
        comboBox.getItems().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (int hour = 8; hour <= 18; hour++) {
            comboBox.getItems().add(LocalTime.of(hour, 0).format(formatter));
        }
    }

    @FXML
    private void guardar(){
        if(comboDiaLibre.getValue()!=null && comboHoraLibre.getValue()!=null){
            HorarioBase horario = new HorarioBase(this.comboHoraLibre.getValue(),this.comboDiaLibre.getValue());
            horariosSalonRepository.update(this.horarioModel.getId(),horario);
            cancelar();
            this.notificar();
        }
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }

    public HorarioSalonModel getHorarioModel() {
        return horarioModel;
    }

    public void setHorarioModel(HorarioModel horarioModel) {
        this.horarioModel = horariosSalonRepository.get(horarioModel.getId());
        this.comboHoraLibre.setValue(horarioModel.getHora_libre());
        this.comboDiaLibre.setValue(horarioModel.getDia_libre());
    }

    public void setHorarioPage(UUID salon, UUID periodo) {
        horarioSalonPage = horariosSalonRepository.all(salon,periodo);
    }

    public void actualizarHoras() {
        if (listo()) {
            String diaSeleccionado = this.comboDiaLibre.getSelectionModel().getSelectedItem();
            if (diaSeleccionado == null) return;

            ComboBox<String> comboBox = comboHoraLibre;
            comboBox.getItems().clear();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter input = DateTimeFormatter.ofPattern("HH:mm:ss");

            ArrayList<HorarioModel> horarioModels = horarioSalonPage.getHorarios();

            Set<String> horasOcupadas = new HashSet<>();
            for (HorarioModel horarioModel : horarioModels) {
                if (horarioModel.getDia_libre().equalsIgnoreCase(diaSeleccionado)) {
                    LocalTime hora = LocalTime.parse(horarioModel.getHora_libre(), input);
                    horasOcupadas.add(hora.format(formatter));
                }
            }

            for (int hour = 8; hour <= 18; hour++) {
                String horaFormateada = LocalTime.of(hour, 0).format(formatter);
                if (!horasOcupadas.contains(horaFormateada)) comboBox.getItems().add(horaFormateada);
            }
        }
    }
}

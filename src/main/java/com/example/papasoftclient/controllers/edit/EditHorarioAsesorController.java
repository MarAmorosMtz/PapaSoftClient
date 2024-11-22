package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.*;
import com.example.papasoftclient.repositories.HorariosAsesorRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EditHorarioAsesorController extends Observable {
    @FXML
    private ComboBox<String> comboDiaLibre;
    @FXML
    private ComboBox<String> comboHoraLibre;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    private HorariosAsesorRepository horarioAsesorRepository;
    private HorarioAsesorModel horarioModel;

    public EditHorarioAsesorController() {
        horarioAsesorRepository = new HorariosAsesorRepository();
    }

    public void initialize(){
        cargarDiasSemana(comboDiaLibre);
        cargarHoras(comboHoraLibre);
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
            horarioAsesorRepository.update(this.horarioModel.getId(),horario);
            cancelar();
            this.notificar();
        }
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }

    public HorarioAsesorModel getHorarioModel() {
        return horarioModel;
    }

    public void setHorarioModel(HorarioModel horarioModel) {
        this.horarioModel = horarioAsesorRepository.get(horarioModel.getId());
        this.comboHoraLibre.setValue(horarioModel.getHora_libre());
        this.comboDiaLibre.setValue(horarioModel.getDia_libre());
    }
}

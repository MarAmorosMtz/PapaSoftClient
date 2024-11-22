package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.HorarioAsesorBase;
import com.example.papasoftclient.repositories.HorariosAsesorRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class AddHorarioAsesorController extends Observable {
    @FXML
    private ComboBox<String> comboDiaLibre;
    @FXML
    private ComboBox<String> comboHoraLibre;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    private HorariosAsesorRepository horariosAsesorRepository;
    private UUID asesor;
    private UUID periodo;

    public AddHorarioAsesorController() {
        horariosAsesorRepository = new HorariosAsesorRepository();
    }

    @FXML
    public void initialize() {
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
        if(this.comboDiaLibre.getValue()!=null && this.comboHoraLibre.getValue()!=null){
            HorarioAsesorBase horario = new HorarioAsesorBase(this.comboHoraLibre.getValue(),this.comboDiaLibre.getValue(),periodo,asesor);
            horariosAsesorRepository.save(horario);
            cancelar();
            this.notificar();
        }
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }

    public UUID getPeriodo() {
        return periodo;
    }

    public void setPeriodo(UUID periodo) {
        this.periodo = periodo;
    }

    public UUID getAsesor() {
        return asesor;
    }

    public void setAsesor(UUID asesor) {
        this.asesor = asesor;
    }
}

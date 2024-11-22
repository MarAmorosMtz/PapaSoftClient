package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.HorarioAsesorBase;
import com.example.papasoftclient.models.HorarioSalonBase;
import com.example.papasoftclient.repositories.HorariosAsesorRepository;
import com.example.papasoftclient.repositories.HorariosSalonRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class AddHorarioSalonController extends Observable {
    @FXML
    private ComboBox<String> comboDiaLibre;
    @FXML
    private ComboBox<String> comboHoraLibre;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    private HorariosSalonRepository horariosSalonRepository;
    private UUID salon;
    private UUID periodo;

    public AddHorarioSalonController() {
        horariosSalonRepository = new HorariosSalonRepository();
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
            HorarioSalonBase horario = new HorarioSalonBase(this.comboHoraLibre.getValue(),this.comboDiaLibre.getValue(),periodo, salon);
            horariosSalonRepository.save(horario);
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

    public UUID getSalon() {
        return salon;
    }

    public void setSalon(UUID salon) {
        this.salon = salon;
    }
}

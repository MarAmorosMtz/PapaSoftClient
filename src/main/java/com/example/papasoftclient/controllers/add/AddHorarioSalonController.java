package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.HorarioAsesorBase;
import com.example.papasoftclient.models.HorarioModel;
import com.example.papasoftclient.models.HorarioSalonBase;
import com.example.papasoftclient.models.HorarioSalonPage;
import com.example.papasoftclient.repositories.HorariosAsesorRepository;
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
    private HorarioSalonPage horarioSalonPage;
    private UUID salon;
    private UUID periodo;

    private ChangeListener<Object> oyenteSeleccion = (observable, oldValue, newValue) -> {
        actualizarHoras();
    };

    public AddHorarioSalonController() {
        horariosSalonRepository = new HorariosSalonRepository();
    }

    @FXML
    public void initialize() {
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

    public void actualizarHoras() {
        if (listo()) {
            String diaSeleccionado = this.comboDiaLibre.getSelectionModel().getSelectedItem();
            if (diaSeleccionado == null) return;

            ComboBox<String> comboBox = comboHoraLibre;
            comboBox.getItems().clear();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter input = DateTimeFormatter.ofPattern("HH:mm:ss");

            horarioSalonPage = horariosSalonRepository.all(salon, periodo);
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



    @FXML
    private void guardar(){
        if(this.comboDiaLibre.getValue()!=null && this.comboHoraLibre.getValue()!=null){
            System.out.println(comboDiaLibre.getValue());
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

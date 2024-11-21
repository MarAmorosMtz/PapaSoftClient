package com.example.papasoftclient.models;

import java.util.ArrayList;
import java.util.UUID;

public class HorarioSalonPage {
    protected UUID salon;
    protected UUID periodo;
    protected ArrayList<HorarioModel> horarios;

    public HorarioSalonPage() {}

    public HorarioSalonPage(ArrayList<HorarioModel> horarios, UUID periodo, UUID salon) {
        this.horarios = horarios;
        this.periodo = periodo;
        this.salon = salon;
    }

    public UUID getSalon() {
        return salon;
    }

    public void setSalon(UUID salon) {
        this.salon = salon;
    }

    public UUID getPeriodo() {
        return periodo;
    }

    public void setPeriodo(UUID periodo) {
        this.periodo = periodo;
    }

    public ArrayList<HorarioModel> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<HorarioModel> horarios) {
        this.horarios = horarios;
    }
}

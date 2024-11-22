package com.example.papasoftclient.models;

import java.util.ArrayList;
import java.util.UUID;

public class HorarioAsesorPage{
    private UUID asesor;
    private UUID periodo;
    private ArrayList<HorarioModel> horarios;

    public HorarioAsesorPage(){}

    public HorarioAsesorPage(UUID asesor, UUID periodo, ArrayList<HorarioModel> horarios) {
        this.asesor = asesor;
        this.periodo = periodo;
        this.horarios = horarios;
    }

    public UUID getAsesor() {
        return asesor;
    }

    public void setAsesor(UUID asesor) {
        this.asesor = asesor;
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

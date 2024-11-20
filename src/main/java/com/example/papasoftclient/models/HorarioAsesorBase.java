package com.example.papasoftclient.models;

import java.util.UUID;

public class HorarioAsesorBase extends HorarioConPeriodoBase{
    protected UUID asesor;

    public HorarioAsesorBase() {}

    public HorarioAsesorBase(String hora_libre, String dia_libre, UUID periodo, UUID asesor) {
        super(hora_libre, dia_libre, periodo);
        this.asesor = asesor;
    }

    public UUID getAsesor() {
        return asesor;
    }

    public void setAsesor(UUID asesor) {
        this.asesor = asesor;
    }
}

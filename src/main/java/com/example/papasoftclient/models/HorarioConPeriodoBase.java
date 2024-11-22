package com.example.papasoftclient.models;

import java.util.UUID;

public class HorarioConPeriodoBase extends HorarioBase{
    protected UUID periodo;

    public HorarioConPeriodoBase() {}

    public HorarioConPeriodoBase(String hora_libre, String dia_libre, UUID periodo) {
        super(hora_libre, dia_libre);
        this.periodo = periodo;
    }

    public UUID getPeriodo() {
        return periodo;
    }

    public void setPeriodo(UUID periodo) {
        this.periodo = periodo;
    }
}

package com.example.papasoftclient.models;

import java.util.UUID;

public class HorarioSalonBase extends HorarioConPeriodoBase{
    protected UUID salon;

    public HorarioSalonBase() {}

    public HorarioSalonBase(String hora_libre, String dia_libre, UUID periodo, UUID salon) {
        super(hora_libre, dia_libre, periodo);
        this.salon = salon;
    }

    public UUID getSalon() {
        return salon;
    }

    public void setSalon(UUID salon) {
        this.salon = salon;
    }
}

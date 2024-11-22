package com.example.papasoftclient.models;

import java.util.UUID;

public class HorarioAsesorModel extends HorarioAsesorBase{
    protected UUID id;

    public HorarioAsesorModel() {}

    public HorarioAsesorModel(String hora_libre, String dia_libre, UUID periodo, UUID asesor, UUID id) {
        super(hora_libre, dia_libre, periodo, asesor);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

package com.example.papasoftclient.models;

import java.util.UUID;

public class HorarioSalonModel extends HorarioSalonBase{
    protected UUID id;

    public HorarioSalonModel(){}

    public HorarioSalonModel(String hora_libre, String dia_libre, UUID periodo, UUID salon, UUID id) {
        super(hora_libre, dia_libre, periodo, salon);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

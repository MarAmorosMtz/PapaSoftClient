package com.example.papasoftclient.models;

import java.util.UUID;

public class HorarioModel extends HorarioBase{

    protected UUID id;

    public HorarioModel() {}

    public HorarioModel(String hora_libre, String dia_libre, UUID id) {
        super(hora_libre, dia_libre);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

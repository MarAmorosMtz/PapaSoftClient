package com.example.papasoftclient.models;

import java.util.UUID;

public class MaestroModel extends MaestroBase{

    private UUID id;

    public MaestroModel() {}

    public MaestroModel(String nombre, UUID id) {
        super(nombre);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

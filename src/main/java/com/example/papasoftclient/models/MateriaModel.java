package com.example.papasoftclient.models;

import java.util.UUID;

public class MateriaModel extends MateriaBase{
    private UUID id;

    public MateriaModel() {}

    public MateriaModel( UUID id, String nombre, UUID carrera) {
        super(nombre, carrera);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

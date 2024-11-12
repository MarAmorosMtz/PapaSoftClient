package com.example.papasoftclient.models;

import java.util.UUID;

public class AsesorModel extends AsesorBase{

    private UUID id;
    private CarreraModel carreraModel;

    public AsesorModel() {}

    public AsesorModel(String nombre, UUID id) {
        super(nombre);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CarreraModel getCarrera() {
        return carreraModel;
    }

    public void setCarrera(CarreraModel carrera) {
        this.carreraModel = carrera;
    }
}

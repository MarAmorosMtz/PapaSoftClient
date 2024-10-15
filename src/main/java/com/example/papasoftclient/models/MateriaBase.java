package com.example.papasoftclient.models;

import java.util.UUID;

public class MateriaBase {

    private String nombre;
    private UUID carrera;

    public MateriaBase() {}

    public MateriaBase(String nombre, UUID carrera) {
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UUID getCarrera() {
        return carrera;
    }

    public void setCarrera(UUID carrera) {
        this.carrera = carrera;
    }
}

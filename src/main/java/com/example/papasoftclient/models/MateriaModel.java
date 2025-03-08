package com.example.papasoftclient.models;

import java.util.UUID;

public class MateriaModel{
    private UUID id;
    private String nombre;
    private CarreraModel carrera;

    public MateriaModel() {}

    public MateriaModel( UUID id, String nombre, CarreraModel carrera) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CarreraModel getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraModel carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}

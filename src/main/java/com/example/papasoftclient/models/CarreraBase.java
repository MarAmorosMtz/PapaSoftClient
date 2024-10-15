package com.example.papasoftclient.models;

import java.util.UUID;

public class CarreraBase {
    private String nombre;

    public CarreraBase() {}

    public CarreraBase(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

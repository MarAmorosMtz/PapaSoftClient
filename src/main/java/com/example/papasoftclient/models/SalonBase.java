package com.example.papasoftclient.models;


public class SalonBase {
    protected String nombre;

    public SalonBase(){

    }

    public SalonBase(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

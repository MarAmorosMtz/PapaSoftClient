package com.example.papasoftclient.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public class PeriodoBase {
    protected String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected LocalDate fecha_inicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected LocalDate fecha_fin;

    public PeriodoBase() {}

    public PeriodoBase(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }
    public LocalDate getFecha_inicio(){ return fecha_inicio; }
    public LocalDate getFecha_fin(){ return fecha_fin; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setFecha_inicio(LocalDate fecha_inicio){ this.fecha_inicio = fecha_inicio; }
    public void setFecha_fin(LocalDate fecha_fin){ this.fecha_fin = fecha_fin; }
}

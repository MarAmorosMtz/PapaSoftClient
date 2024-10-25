package com.example.papasoftclient.models;

import java.util.Date;
import java.util.UUID;

public class PeriodoBase {
    private String nombre;
    private Date fechaInicio;
    private Date fechaFinal;

    public PeriodoBase() {}

    public PeriodoBase(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }
    public Date getFechaInicio(){ return fechaInicio; }
    public Date getFechaFinal(){ return fechaFinal; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setFechaInicio(Date fechaInicio){ this.fechaInicio = fechaInicio; }
    public void setFechaFinal(Date fechaFinal){ this.fechaFinal = fechaFinal; }
}

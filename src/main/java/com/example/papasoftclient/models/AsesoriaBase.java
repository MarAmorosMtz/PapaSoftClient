package com.example.papasoftclient.models;

import java.sql.Time;
import java.util.UUID;
import java.sql.Date;

public class AsesoriaBase {

    private Date fecha;
    private Time hora;
    private UUID materia, salon, asesor;
    private String tipo;

    public AsesoriaBase(){}

    public AsesoriaBase(Date fecha, Time hora, UUID materia, UUID salon, UUID asesor, String tipo) {
        this.fecha = fecha;
        this.hora = hora;
        this.materia = materia;
        this.salon = salon;
        this.asesor = asesor;
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public UUID getMateria() {
        return materia;
    }

    public void setMateria(UUID materia) {
        this.materia = materia;
    }

    public UUID getSalon() {
        return salon;
    }

    public void setSalon(UUID salon) {
        this.salon = salon;
    }

    public UUID getAsesor() {
        return asesor;
    }

    public void setAsesor(UUID asesor) {
        this.asesor = asesor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}

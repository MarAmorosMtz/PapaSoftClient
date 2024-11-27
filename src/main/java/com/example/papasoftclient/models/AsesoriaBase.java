package com.example.papasoftclient.models;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.Date;

public class AsesoriaBase {

    private LocalDate fecha;
    private String hora;
    private UUID materia, salon, asesor;
    private String tipo;
    private ArrayList<DetalleAsesoradoModel> asesorados;

    public AsesoriaBase(){}

    public AsesoriaBase(LocalDate fecha, String hora, UUID materia, UUID salon, UUID asesor, String tipo, ArrayList<DetalleAsesoradoModel> asesorados) {
        this.fecha = fecha;
        this.hora = hora;
        this.materia = materia;
        this.salon = salon;
        this.asesor = asesor;
        this.tipo = tipo;
        this.asesorados = asesorados;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
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

    public ArrayList<DetalleAsesoradoModel> getAsesorados() {
        return asesorados;
    }

    public void setAsesorados(ArrayList<DetalleAsesoradoModel> asesorados) {
        this.asesorados = asesorados;
    }
}

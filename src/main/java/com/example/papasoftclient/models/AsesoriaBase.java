package com.example.papasoftclient.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AsesoriaBase {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private String hora;
    private String tema;
    private UUID materia, salon, asesor;
    private boolean confirmada;
    private ArrayList<DetalleAsesoradoModel> asesorados;

    public AsesoriaBase(){}

    public AsesoriaBase(LocalDate fecha, String hora, UUID materia, UUID salon, UUID asesor, String tema, boolean confirmada,ArrayList<DetalleAsesoradoModel> asesorados) {
        this.fecha = fecha;
        this.hora = hora;
        this.materia = materia;
        this.salon = salon;
        this.asesor = asesor;
        this.tema = tema;
        this.asesorados = asesorados;
        this.confirmada = confirmada;
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

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public boolean getConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public ArrayList<DetalleAsesoradoModel> getAsesorados() {
        return asesorados;
    }

    public void setAsesorados(ArrayList<DetalleAsesoradoModel> asesorados) {
        this.asesorados = asesorados;
    }
}

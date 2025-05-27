package com.example.papasoftclient.models;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.Date;

public class AsesoriaModel {

    private LocalDate fecha;
    private String hora;
    private String tema;
    private MateriaModel materia;
    private SalonModel salon;
    private AsesorModel asesor;
    private UUID id;
    private boolean confirmada;
    private ArrayList<DetalleAsesoradoModel> asesorados;
    private UUID periodo;

    public AsesoriaModel() {}

    public AsesoriaModel(LocalDate fecha, String hora, MateriaModel materia, SalonModel salon, AsesorModel asesor, String tema, UUID id, boolean confirmada) {
        this.fecha = fecha;
        this.hora = hora;
        this.materia = materia;
        this.salon = salon;
        this.asesor = asesor;
        this.tema = tema;
        this.id = id;
        this.confirmada = confirmada;
    }

    public AsesoriaModel(LocalDate fecha, String hora, MateriaModel materia, SalonModel salon, AsesorModel asesor, String tema, UUID id,boolean confirmada, ArrayList<DetalleAsesoradoModel> asesorados) {
        this.fecha = fecha;
        this.hora = hora;
        this.materia = materia;
        this.salon = salon;
        this.asesor = asesor;
        this.tema = tema;
        this.id = id;
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

    public MateriaModel getMateria() {
        return materia;
    }

    public void setMateria(MateriaModel materia) {
        this.materia = materia;
    }

    public SalonModel getSalon() {
        return salon;
    }

    public void setSalon(SalonModel salon) {
        this.salon = salon;
    }

    public AsesorModel getAsesor() {
        return asesor;
    }

    public void setAsesor(AsesorModel asesor) {
        this.asesor = asesor;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tipo) {
        this.tema = tipo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setPeriodo(UUID periodo){this.periodo = periodo;}
    public UUID getPeriodo(){return this.periodo;}
}
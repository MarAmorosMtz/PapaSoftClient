package com.example.papasoftclient.models;
import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;
import java.sql.Date;

public class AsesoriaModel {

    private LocalDate fecha;
    private Time hora;
    private MateriaModel materia;
    private SalonModel salon;
    private AsesorModel asesor;
    private String tipo;
    private UUID id;

    public AsesoriaModel(){}

    public AsesoriaModel(UUID id, LocalDate fecha, Time hora, MateriaModel materia, SalonModel salon, AsesorModel asesor, String tipo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.materia = materia;
        this.salon = salon;
        this.asesor = asesor;
        this.tipo = tipo;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public UUID getId(){
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
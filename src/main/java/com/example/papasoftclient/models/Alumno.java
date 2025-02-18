package com.example.papasoftclient.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class Alumno extends Persona{
    protected String num_ctrl;
    protected String telefono;
    protected String correo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected LocalDate fecha_inscripcion;
    protected int semestre;

    public Alumno(){}

    public Alumno(String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre) {
        this.num_ctrl = num_ctrl;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_inscripcion = fecha_inscripcion;
        this.semestre = semestre;
    }

    public Alumno(String nombre, String apellido_p, String apellido_m, String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre) {
        super(nombre, apellido_p, apellido_m);
        this.num_ctrl = num_ctrl;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_inscripcion = fecha_inscripcion;
        this.semestre = semestre;
    }

    public String getNum_ctrl() {
        return num_ctrl;
    }

    public void setNum_ctrl(String num_ctrl) {
        this.num_ctrl = num_ctrl;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(LocalDate fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

}

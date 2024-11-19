package com.example.papasoftclient.models;

import java.time.LocalDate;
import java.util.UUID;

public class AsesoradoModel extends Alumno{
    protected UUID id;
    protected CarreraModel carrera;
    protected String horario;

    public AsesoradoModel() {}


    public AsesoradoModel(CarreraModel carrera, String horario) {
        this.carrera = carrera;
        this.horario = horario;
    }

    public AsesoradoModel(UUID id, CarreraModel carrera, String horario) {
        this.id = id;
        this.carrera = carrera;
        this.horario = horario;
    }

    public AsesoradoModel(String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre, UUID id, CarreraModel carrera, String horario) {
        super(num_ctrl, correo, telefono, fecha_inscripcion, semestre);
        this.id = id;
        this.carrera = carrera;
        this.horario = horario;
    }

    public AsesoradoModel(String nombre, String apellido_p, String apellido_m, String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre, UUID id, CarreraModel carrera, String horario) {
        super(nombre, apellido_p, apellido_m, num_ctrl, correo, telefono, fecha_inscripcion, semestre);
        this.id = id;
        this.carrera = carrera;
        this.horario = horario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CarreraModel getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraModel carrera) {
        this.carrera = carrera;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "{id="+this.id+",numCtrl="+this.num_ctrl+",nombre="+this.nombre+",apellidoP="+this.apellido_p+",apellidoM="+this.apellido_m+"}";
    }
}

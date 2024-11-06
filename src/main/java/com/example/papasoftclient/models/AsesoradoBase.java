package com.example.papasoftclient.models;

import java.time.LocalDate;
import java.util.UUID;

public class AsesoradoBase extends Alumno{

    protected UUID carrera;
    protected String horario;

    public AsesoradoBase(UUID carrera, String horario) {
        this.carrera = carrera;
        this.horario = horario;
    }

    public AsesoradoBase(String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre, UUID carrera, String horario) {
        super(num_ctrl, correo, telefono, fecha_inscripcion, semestre);
        this.carrera = carrera;
        this.horario = horario;
    }

    public AsesoradoBase(String nombre, String apellido_p, String apellido_m, String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre, UUID carrera, String horario) {
        super(nombre, apellido_p, apellido_m, num_ctrl, correo, telefono, fecha_inscripcion, semestre);
        this.carrera = carrera;
        this.horario = horario;
    }

    public AsesoradoBase(UUID carrera) {
        this.carrera = carrera;
    }

    public UUID getCarrera() {
        return carrera;
    }

    public void setCarrera(UUID carrera) {
        this.carrera = carrera;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}

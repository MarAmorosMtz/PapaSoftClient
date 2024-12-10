package com.example.papasoftclient.models;

import eu.hansolo.tilesfx.tools.DoubleExponentialSmoothingForLinearSeries;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class AsesorBase extends Alumno {
    private String contrato;
    private String foto;
    private UUID carrera;
    private boolean activo;

    public AsesorBase() {}

    public AsesorBase(String contrato, String foto, UUID carrera) {
        this.contrato = contrato;
        this.foto = foto;
        this.carrera = carrera;
    }

    public AsesorBase(String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre, String contrato, String foto, UUID carrera, boolean activo) {
        super(num_ctrl, correo, telefono, fecha_inscripcion, semestre);
        this.contrato = contrato;
        this.foto = foto;
        this.carrera = carrera;
        this.activo = activo;
    }

    public AsesorBase(String nombre, String apellido_p, String apellido_m, String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre, String contrato, String foto, UUID carrera, boolean activo) {
        super(nombre, apellido_p, apellido_m, num_ctrl, correo, telefono, fecha_inscripcion, semestre);
        this.contrato = contrato;
        this.foto = foto;
        this.carrera = carrera;
        this.activo = activo;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public UUID getCarrera() {
        return carrera;
    }

    public void setCarrera(UUID carrera) {
        this.carrera = carrera;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean estado) {
        this.activo = estado;
    }
}

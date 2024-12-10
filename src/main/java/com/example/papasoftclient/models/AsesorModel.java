package com.example.papasoftclient.models;

import java.time.LocalDate;
import java.util.UUID;

public class AsesorModel extends Alumno{

    private UUID id;
    protected CarreraModel carrera;
    protected String foto;
    protected String contrato;
    protected boolean activo;
    protected String activoTexto;

    public AsesorModel() {}

    public AsesorModel(UUID id, CarreraModel carrera, String foto, String contrato) {
        this.id = id;
        this.carrera = carrera;
        this.foto = foto;
        this.contrato = contrato;
    }

    public AsesorModel(String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre, UUID id, CarreraModel carrera, String foto, String contrato, boolean activo) {
        super(num_ctrl, correo, telefono, fecha_inscripcion, semestre);
        this.id = id;
        this.carrera = carrera;
        this.foto = foto;
        this.contrato = contrato;
        this.activo = activo;
    }

    public AsesorModel(String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre, UUID id, CarreraModel carrera, String foto, String contrato, String activoTexto) {
        super(num_ctrl, correo, telefono, fecha_inscripcion, semestre);
        this.id = id;
        this.carrera = carrera;
        this.foto = foto;
        this.contrato = contrato;
        this.activoTexto = activoTexto;
    }

    public AsesorModel(String nombre, String apellido_p, String apellido_m, String num_ctrl, String correo, String telefono, LocalDate fecha_inscripcion, int semestre, UUID id, CarreraModel carrera, String foto, String contrato) {
        super(nombre, apellido_p, apellido_m, num_ctrl, correo, telefono, fecha_inscripcion, semestre);
        this.id = id;
        this.carrera = carrera;
        this.foto = foto;
        this.contrato = contrato;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getActivoTexto() {
        return activoTexto;
    }

    public void setActivoTexto(String activoTexto) {
        this.activoTexto = activoTexto;
        this.activo = "Activo".equalsIgnoreCase(activoTexto);
    }

    @Override
    public String toString() {
        return this.apellido_p+" "+this.apellido_m+" "+this.nombre;
    }
}

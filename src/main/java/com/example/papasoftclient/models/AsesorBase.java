package com.example.papasoftclient.models;

import java.util.Date;
import java.util.UUID;

public class AsesorBase {
    private String num_ctrl ;
    private String nombre;
    private String apellido_p;
    private String apellido_m;
    private String correo;
    private String telefono;
    private Date fecha_inscripcion;
    private int semestre;
    private String contrato;
    private String foto;

    public AsesorBase() {}

    public AsesorBase(String num_ctrl) {
        this.num_ctrl = num_ctrl;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public String getNum_ctrl() {
        return num_ctrl;
    }

    public void setNum_ctrl(String num_ctrl) {
        this.num_ctrl = num_ctrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_p() {
        return apellido_p;
    }

    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }

    public String getApellido_m() {
        return apellido_m;
    }

    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }
}

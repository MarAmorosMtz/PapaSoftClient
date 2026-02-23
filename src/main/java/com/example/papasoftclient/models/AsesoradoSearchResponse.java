package com.example.papasoftclient.models;

import java.util.UUID;

public class AsesoradoSearchResponse {
    private UUID id;
    private String num_ctrl;
    private String nombre;
    private String apellido_p;
    private String apellido_m;

    public AsesoradoSearchResponse() {}

    public AsesoradoSearchResponse(UUID id, String num_ctrl, String nombre, String apellido_p, String apellido_m) {
        this.id = id;
        this.num_ctrl = num_ctrl;
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
    }

    public AsesoradoSearchResponse(AsesoradoModel asesorado){
        this.id = asesorado.getId();
        this.num_ctrl = asesorado.getNum_ctrl();
        this.nombre = asesorado.getNombre();
        this.apellido_p = asesorado.getApellido_p();
        this.apellido_m = asesorado.getApellido_m();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AsesoradoSearchResponse{" +
                "id=" + id +
                ", num_ctrl='" + num_ctrl + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido_p='" + apellido_p + '\'' +
                ", apellido_m='" + apellido_m + '\'' +
                '}';
    }
}

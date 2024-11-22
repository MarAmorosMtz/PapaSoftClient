package com.example.papasoftclient.models;

public class HorarioBase {
    protected String hora_libre;
    protected String dia_libre;

    public HorarioBase() {}

    public HorarioBase(String hora_libre, String dia_libre) {
        this.hora_libre = hora_libre;
        this.dia_libre = dia_libre;
    }

    public String getHora_libre() {
        return hora_libre;
    }

    public void setHora_libre(String hora_libre) {
        this.hora_libre = hora_libre;
    }

    public String getDia_libre() {
        return dia_libre;
    }

    public void setDia_libre(String dia_libre) {
        this.dia_libre = dia_libre;
    }
}

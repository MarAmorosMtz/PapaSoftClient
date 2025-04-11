package com.example.papasoftclient.models;

public class UsuarioBase {
    private int tipo;
    private String clave;

    public UsuarioBase(){}

    public int getTipo(){
        return tipo;
    }

    public String getClave(){
        return clave;
    }

    public void setTipo(int tipo){
        this.tipo = tipo;
    }

    public void setClave(String clave){
        this.clave = clave;
    }
}

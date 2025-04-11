package com.example.papasoftclient.models;

public class UsuarioModel extends UsuarioBase{
    private String nombre;

    public UsuarioModel(){}

    public UsuarioModel(String nombre){
        super();
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString(){ return this.getTipo()+" "+this.getNombre()+" "+this.getClave(); }
}

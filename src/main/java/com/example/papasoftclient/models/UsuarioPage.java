package com.example.papasoftclient.models;

import java.util.ArrayList;

public class UsuarioPage extends Page{
    public ArrayList<UsuarioModel> usuarios;

    public UsuarioPage(){}

    public UsuarioPage(int total, int paginas, ArrayList<UsuarioModel> usuarios){
        super(total, paginas);
        this.usuarios = usuarios;
    }

    public ArrayList<UsuarioModel> getUsuarios(){ return usuarios; }

    public void setUsuarios(ArrayList<UsuarioModel> usuarios){ this.usuarios = usuarios; }
}

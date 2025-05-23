package com.example.papasoftclient.models;

import com.example.papasoftclient.controllers.main.LoginController;

public class LoginModel {
    private String usuario;
    private String clave;
    private int tipo;

    public LoginModel() {}

    public LoginModel(String usuario, String clave, int tipo) {
        this.usuario = usuario;
        this.clave = clave;
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}

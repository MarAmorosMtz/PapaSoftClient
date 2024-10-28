package com.example.papasoftclient.models;

import java.util.UUID;

public class CarreraModel extends CarreraBase{
    private UUID id;

    public CarreraModel(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String toString(){
        return this.nombre;
    }
}

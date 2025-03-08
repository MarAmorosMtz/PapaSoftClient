package com.example.papasoftclient.models;

import java.util.UUID;

public class AsesorMateriaModel {
    protected UUID id;
    protected UUID asesor_id;
    protected UUID materia_id;

    public AsesorMateriaModel(){}

    public AsesorMateriaModel(UUID asesor_id, UUID materia_id){
        this.asesor_id = asesor_id;
        this.materia_id = materia_id;
    }

    public UUID getId(){
        return id;
    }

    public UUID getAsesor_id(){
        return asesor_id;
    }

    public void setAsesor_id(UUID asesor_id){
        this.asesor_id = asesor_id;
    }

    public UUID getMateria_id() { return materia_id; }

    public void setMateria_id(UUID materia_id) { this.materia_id = materia_id; }


    @Override
    public String toString(){
        return this.asesor_id +" "+this.materia_id;
    }

}

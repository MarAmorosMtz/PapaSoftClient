package com.example.papasoftclient.models;

import java.util.UUID;

public class AsesorMateriaBase {

    protected UUID asesor_id;
    protected UUID materia_id;

    public AsesorMateriaBase(UUID asesor, UUID materia){
        this.asesor_id = asesor;
        this.materia_id = materia;
    }

    public UUID getAsesor_id(){
        return this.asesor_id;
    }

    public UUID getMateria_id(){
        return this.materia_id;
    }
}

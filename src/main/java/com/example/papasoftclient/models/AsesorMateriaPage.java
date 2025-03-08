package com.example.papasoftclient.models;

import java.util.ArrayList;
import java.util.UUID;

public class AsesorMateriaPage extends Page{
    private UUID asesor_id;
    private ArrayList<AsesorMateriaModel> asesor_materias;


    public AsesorMateriaPage(){}

    public AsesorMateriaPage(UUID asesor, ArrayList<AsesorMateriaModel> materias){
        this.asesor_id = asesor;
        this.asesor_materias = materias;
    }

    public AsesorMateriaPage(int total, int paginas, ArrayList<AsesorMateriaModel> materias){
        super(total, paginas);
        this.asesor_materias = materias;
    }

    public ArrayList<AsesorMateriaModel> getMaterias(){
        return asesor_materias;
    }

    public void setMaterias(ArrayList<AsesorMateriaModel> materias){
        this.asesor_materias = materias;
    }


    public UUID getAsesor(){ return asesor_id; }

    public void setAsesor(UUID asesor){ this.asesor_id = asesor; }
}

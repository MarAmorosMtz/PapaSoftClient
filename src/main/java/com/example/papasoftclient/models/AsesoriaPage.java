package com.example.papasoftclient.models;

import java.util.ArrayList;

public class AsesoriaPage extends Page{
    private ArrayList<AsesoriaModel> asesorias;
    public AsesoriaPage(){}
    public AsesoriaPage(ArrayList<AsesoriaModel> asesorias){
        this.asesorias = asesorias;
    }
    public void setAsesoriaPage(ArrayList<AsesoriaModel> asesorias){
        this.asesorias = asesorias;
    }
    public ArrayList<AsesoriaModel> getAsesorias(){
        return asesorias;
    }
}

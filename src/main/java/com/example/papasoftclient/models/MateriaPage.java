package com.example.papasoftclient.models;

import java.util.ArrayList;

public class MateriaPage extends Page{

    private ArrayList<MateriaModel> materias;

    public MateriaPage() {};

    public MateriaPage(ArrayList<MateriaModel> materias) {
        this.materias = materias;
    }

    public ArrayList<MateriaModel> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<MateriaModel> materias) {
        this.materias = materias;
    }
}

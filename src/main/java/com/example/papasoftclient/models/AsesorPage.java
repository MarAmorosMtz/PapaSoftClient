package com.example.papasoftclient.models;

import java.util.ArrayList;

public class AsesorPage extends Page{
    public ArrayList<AsesorModel> asesores;

    public AsesorPage() {}

    public AsesorPage(ArrayList<AsesorModel> asesores) {
        this.asesores = asesores;
    }


    public ArrayList<AsesorModel> getAsesores() {
        return asesores;
    }

    public void setAsesores(ArrayList<AsesorModel> asesores) {
        this.asesores = asesores;
    }
}

package com.example.papasoftclient.models;

import java.util.ArrayList;

public class AsesoradoPage extends Page{
    private ArrayList<AsesoradoModel> asesorados;

    public AsesoradoPage() {}

    public AsesoradoPage(ArrayList<AsesoradoModel> asesorados) {
        this.asesorados = asesorados;
    }

    public AsesoradoPage(int total, int paginas, ArrayList<AsesoradoModel> asesorados) {
        super(total, paginas);
        this.asesorados = asesorados;
    }

    public ArrayList<AsesoradoModel> getAsesorados() {
        return asesorados;
    }

    public void setAsesorados(ArrayList<AsesoradoModel> asesorados) {
        this.asesorados = asesorados;
    }
}

package com.example.papasoftclient.models;

import java.util.ArrayList;

public class MaestroPage extends Page{
    public ArrayList<MaestroModel> maestros;

    public MaestroPage() {}

    public MaestroPage(int total, int paginas, ArrayList<MaestroModel> maestros) {
        super(total, paginas);
        this.maestros = maestros;
    }


    public ArrayList<MaestroModel> getMaestros() {
        return maestros;
    }

    public void setMaestros(ArrayList<MaestroModel> maestros) {
        this.maestros = maestros;
    }
}

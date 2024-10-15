package com.example.papasoftclient.models;

import java.util.ArrayList;

public class CarreraPage extends Page{

    private ArrayList<CarreraModel> carreras;

    public CarreraPage() {}

    public ArrayList<CarreraModel> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<CarreraModel> carreras) {
        this.carreras = carreras;
    }
}

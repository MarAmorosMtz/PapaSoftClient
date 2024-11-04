package com.example.papasoftclient.models;

import java.util.ArrayList;

public class SalonPage extends Page {

    private ArrayList<SalonModel> salones;

    public SalonPage() {};

    public SalonPage(ArrayList<SalonModel> salones) {
        this.salones = salones;
    }

    public ArrayList<SalonModel> getSalones() {
        return salones;
    }

    public void setSalones(ArrayList<SalonModel> salones) {
        this.salones = salones;
    }
}

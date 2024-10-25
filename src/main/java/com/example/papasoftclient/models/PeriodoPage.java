package com.example.papasoftclient.models;

import java.util.ArrayList;

public class PeriodoPage extends Page {

    private ArrayList<PeriodoModel> periodos;

    public PeriodoPage() {
    }

    public ArrayList<PeriodoModel> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(ArrayList<PeriodoModel> periodos) {
        this.periodos = periodos;
    }
}
package com.example.papasoftclient.utils;

import java.util.ArrayList;
import java.util.Observer;

public abstract class Observable {
    protected ArrayList<Observador> observadores;

    public Observable() {
        observadores = new ArrayList<>();
    }

    public void notificar(){
        System.out.println("Notificando a"+observadores.size()+" observadores...");
        for (Observador observador : observadores) {
            observador.actualizar();
        }
    }

    public void agregarObservador(Observador observador){
        this.observadores.add(observador);
    }

    public void eliminarObservador(Observador observer){
        this.observadores.remove(observer);
    }
}

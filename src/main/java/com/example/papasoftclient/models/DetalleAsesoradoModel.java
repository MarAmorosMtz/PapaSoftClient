package com.example.papasoftclient.models;

import java.util.UUID;

public class DetalleAsesoradoModel {
    private UUID asesorado;
    private UUID maestro;

    public DetalleAsesoradoModel() {}

    public DetalleAsesoradoModel(UUID asesorado, UUID maestro) {
        this.asesorado = asesorado;
        this.maestro = maestro;
    }

    public UUID getAsesorado() {
        return asesorado;
    }

    public void setAsesorado(UUID asesorado) {
        this.asesorado = asesorado;
    }

    public UUID getMaestro() {
        return maestro;
    }

    public void setMaestro(UUID maestro) {
        this.maestro = maestro;
    }
}

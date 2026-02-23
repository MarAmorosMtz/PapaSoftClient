package com.example.papasoftclient.models;

public class DetalleAsesoriaExpanded {
    private AsesoradoSearchResponse asesorado;
    private MaestroModel maestro;

    public DetalleAsesoriaExpanded(){}

    public DetalleAsesoriaExpanded(AsesoradoSearchResponse asesorado, MaestroModel maestro) {
        this.asesorado = asesorado;
        this.maestro = maestro;
    }

    public AsesoradoSearchResponse getAsesorado() {
        return asesorado;
    }

    public void setAsesorado(AsesoradoSearchResponse asesorado) {
        this.asesorado = asesorado;
    }

    public MaestroModel getMaestro() {
        return maestro;
    }

    public void setMaestro(MaestroModel maestro) {
        this.maestro = maestro;
    }

    @Override
    public String toString() {
        return "DetalleAsesoriaExpanded{" +
                "asesorado=" + asesorado +
                ", maestro=" + maestro +
                '}';
    }
}

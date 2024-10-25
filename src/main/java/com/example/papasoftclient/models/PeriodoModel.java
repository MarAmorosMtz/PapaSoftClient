package com.example.papasoftclient.models;

import java.util.UUID;

public class PeriodoModel extends PeriodoBase{
    private UUID id;

    public PeriodoModel(){
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
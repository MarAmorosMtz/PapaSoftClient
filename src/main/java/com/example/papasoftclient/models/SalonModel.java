package com.example.papasoftclient.models;

import java.util.UUID;

public class SalonModel extends SalonBase{
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

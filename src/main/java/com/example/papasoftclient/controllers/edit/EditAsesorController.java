package com.example.papasoftclient.controllers.edit;

import com.example.papasoftclient.models.AsesorBase;
import com.example.papasoftclient.models.AsesorModel;

public class EditAsesorController {

    AsesorModel asesorModel;
    AsesorBase asesorBase;

    public void setBase(AsesorBase base){
        this.asesorBase = base;
    }

    public void setModel(AsesorModel model){
        this.asesorModel = model;
    }
}

package com.example.papasoftclient.controllers;

import javafx.fxml.FXML;
import javafx.stage.Popup;

public class DialogoController {
    private Popup parentPopup;

    public void setParentPopup(Popup popup) {
        this.parentPopup = popup;
    }

    @FXML
    private void edit(){
        parentPopup.hide();
    }

    @FXML
    private void delete(){
        parentPopup.hide();
    }
}

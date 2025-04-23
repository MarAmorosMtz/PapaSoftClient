package com.example.papasoftclient.controllers.Utils;

import com.example.papasoftclient.models.AsesorModel;
import com.example.papasoftclient.utils.ClickListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;


public class EntryController {
    @FXML
    private VBox vbox;

    @FXML
    private Label activo;

    @FXML
    private Label nombre;

    @FXML
    private Label numerocontrol;

    @FXML
    private Label semestre;

    @FXML
    private ImageView imagen;

    private ClickListener listener;
    private AsesorModel asesor;

    public void click(javafx.scene.input.MouseEvent mouseEvent) {
        listener.onClickListener(asesor);
    }

    public void setData(AsesorModel asesor, ClickListener listener){
        this.asesor = asesor;
        this.listener = listener;
        String state;
        if(asesor.getActivo()){
            state = "Activo";
        }else{
            state = "Inactivo";
        }
        try{
            if(!asesor.getFoto().isEmpty()){
                File file = new File(asesor.getFoto());
                Image image = new Image(file.toURI().toString());
                imagen.setImage(image);
            }else{
                imagen.setImage(new Image(getClass().getResourceAsStream("/com/example/papasoftclient/media/defaultProfilePhoto.png")));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        nombre.setText(asesor.toString());
        numerocontrol.setText(asesor.getNum_ctrl());
        semestre.setText("Semestre: " + asesor.getSemestre());
        activo.setText("Estado: "+state);
    }

}

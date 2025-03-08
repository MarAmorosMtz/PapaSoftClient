package com.example.papasoftclient.controllers.add;

import com.example.papasoftclient.models.AsesorMateriaBase;
import com.example.papasoftclient.models.MateriaModel;
import com.example.papasoftclient.models.MateriaPage;
import com.example.papasoftclient.repositories.AsesorMateriaRepository;
import com.example.papasoftclient.repositories.MateriaRepository;
import com.example.papasoftclient.utils.Observable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.UUID;

public class AddAsesorMateriaController extends Observable {
    @FXML
    ComboBox<MateriaModel> comboMateria;
    @FXML
    Button btnCancelar;

    private MateriaRepository materiaRepository;
    private AsesorMateriaRepository asesorMateriaRepository;
    private UUID asesor_id;

    public AddAsesorMateriaController(){
        this.materiaRepository =  new MateriaRepository();
        this.asesorMateriaRepository = new AsesorMateriaRepository();
    }

    public void initialize(){
        cargarMaterias();
    }

    public void setAsesor(UUID asesor_id){ this.asesor_id = asesor_id; }

    private boolean cargarMaterias(){
        ArrayList<MateriaModel> listaMaterias = new ArrayList<MateriaModel>();
        MateriaPage tmp = this.materiaRepository.search(1);
        if(tmp != null){
            this.comboMateria.getItems().clear();
            int paginas = tmp.getPaginas();
            if(paginas > 0){
                this.comboMateria.getItems().addAll(FXCollections.observableArrayList(tmp.getMaterias()));
                for(int i = 2; i <= paginas; i++){
                    tmp = this.materiaRepository.search(i);
                    this.comboMateria.getItems().addAll(FXCollections.observableArrayList(tmp.getMaterias()));
                }
                return true;
            }
        }
        return false;
    }

    @FXML
    public void guardar(){
        UUID materia_id = comboMateria.getValue().getId();
        AsesorMateriaBase asesorMateria = new AsesorMateriaBase(asesor_id, materia_id);
        asesorMateriaRepository.save(asesorMateria);
        this.notificar();
        cancelar();
    }
    @FXML
    public void cancelar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }
}

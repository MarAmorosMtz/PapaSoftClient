package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.controllers.Utils.EntryController;
import com.example.papasoftclient.models.AsesorModel;
import com.example.papasoftclient.models.AsesorPage;
import com.example.papasoftclient.repositories.AsesorRepository;
import com.example.papasoftclient.utils.ClickListener;
import com.example.papasoftclient.utils.Observador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ScrollPaneAsesorController implements Observador {
    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scrollpane;

    private AsesorRepository asesorRepository;

    private final int maxColumns = 4;

    private ClickListener listener;
    private AsesorModel asesorSelected;


    public ScrollPaneAsesorController(){ asesorRepository = new AsesorRepository(); }

    public void setSelectedAsesor(AsesorModel asesor){
        this.asesorSelected = asesor;
    }

    public AsesorModel getSelectedAsesor(){
        return asesorSelected;
    }

    @FXML
    public void initialize(){
        int column = 0;
        int row = 1;
        if(!loadAsesores().isEmpty()){
            listener = this::setSelectedAsesor;
        }
        try {
            for(int i = 0; i <= loadAsesores().size()-1; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/asesor/vistaEntryAsesor.fxml"));
                AnchorPane anchorPane = loader.load();
                EntryController controller = loader.getController();
                controller.setData(loadAsesores().get(i), listener);


                VBox vbox = (VBox) anchorPane.getChildren().getFirst();

                vbox.setOnMouseClicked(event -> {
                    clearSelections();
                    vbox.getStyleClass().add("selected");
                });

                if (column == maxColumns) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<AsesorModel> loadAsesores(){
        int i = 1;
        ArrayList<AsesorModel> asesores = new ArrayList<>();
        AsesorPage tmp;
        do {
            tmp = asesorRepository.search(i);
            if(tmp != null && !tmp.getAsesores().isEmpty()) {
                asesores.addAll(tmp.getAsesores());
            }
            i++;
        } while (tmp != null && !tmp.getAsesores().isEmpty());

        return asesores;
    }


    private void clearSelections() {
        for (Node node : grid.getChildren()) {
            if (node instanceof AnchorPane) {
                VBox vbox = (VBox) ((AnchorPane) node).getChildren().getFirst();
                vbox.getStyleClass().remove("selected");
            }
        }
    }

    @Override
    public void actualizar(){
        grid.getChildren().clear();
        clearSelections();
        initialize();
    }

}

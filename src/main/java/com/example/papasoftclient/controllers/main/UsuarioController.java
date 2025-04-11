package com.example.papasoftclient.controllers.main;

import com.example.papasoftclient.controllers.add.AddUsuarioController;
import com.example.papasoftclient.controllers.delete.ConfirmacionUsuarioController;
import com.example.papasoftclient.controllers.edit.EditMaestroController;
import com.example.papasoftclient.controllers.edit.EditUsuarioController;
import com.example.papasoftclient.models.MaestroModel;
import com.example.papasoftclient.models.UsuarioModel;
import com.example.papasoftclient.models.UsuarioPage;
import com.example.papasoftclient.repositories.UsuarioRepository;
import com.example.papasoftclient.utils.Observador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class UsuarioController implements Observador {
    @FXML
    TableView<UsuarioModel> tablaUsuario;
    @FXML
    private TableColumn<UsuarioModel, String> columnaTipo;
    @FXML
    private TableColumn<UsuarioModel, String> columnaNombre;
    @FXML
    private Pagination paginadorUsuarios;

    private UsuarioRepository usuarioRepository;

    public UsuarioController(){ usuarioRepository = new UsuarioRepository(); }

    @FXML
    public void initialize(){
        columnaTipo.setCellValueFactory(cellData -> {
            Integer tipoValue = cellData.getValue().getTipo();
            String tipoTexto = (tipoValue == 0) ? "administrador" : "secretario";
            return new SimpleStringProperty(tipoTexto);
        });
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        paginadorUsuarios.setPageFactory(this::updateTable);
    }

    public void loadUsuarios(UsuarioPage page){
        tablaUsuario.setItems(FXCollections.observableArrayList(page.getUsuarios()));
    }

    public Node updateTable(int pageIndex){
        UsuarioPage tmp = usuarioRepository.search(pageIndex+1);
        if(tmp != null){
            loadUsuarios(tmp);
            if(tmp.getPaginas() != paginadorUsuarios.getPageCount()){
                paginadorUsuarios.setPageCount(tmp.getPaginas());
            }
        }
        return tablaUsuario;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/usuario/vistaAgregarUsuario.fxml"));
        Parent parent = loader.load();

        AddUsuarioController controller = loader.getController();
        controller.agregarObservador(this);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage.getOwner());
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void edit() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/usuario/vistaEditarUsuario.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaUsuario.getSelectionModel().getSelectedIndex();

        if(rowIndex != -1){
            UsuarioModel usuario = tablaUsuario.getItems().get(rowIndex);

            EditUsuarioController editController = loader.getController();
            editController.setModel(usuario);
            editController.agregarObservador(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.setMaximized(false);
            stage.setResizable(true);

            stage.show();
        }
    }
    @FXML
    private void delete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/papasoftclient/usuario/vistaConfirmacionUsuario.fxml"));
        Parent parent = loader.load();

        int rowIndex = tablaUsuario.getSelectionModel().getSelectedIndex();
        if(rowIndex != -1){
            UsuarioModel usuario = tablaUsuario.getItems().get(rowIndex);

            ConfirmacionUsuarioController confirmacionController = loader.getController();
            confirmacionController.setUsuario(usuario);
            confirmacionController.agregarObservador(this);
            GaussianBlur blurEffect = new GaussianBlur();
            blurEffect.setRadius(10);
            Stage mainStage = (Stage) tablaUsuario.getScene().getWindow();
            mainStage.getScene().getRoot().setEffect(blurEffect);

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(stage.getOwner());
            stage.setMaximized(false);
            stage.setResizable(true);

            stage.setOnHiding(event -> mainStage.getScene().getRoot().setEffect(null));

            stage.show();
        }
    }

    @Override
    public void actualizar() {
        updateTable(paginadorUsuarios.getCurrentPageIndex());
    }
}

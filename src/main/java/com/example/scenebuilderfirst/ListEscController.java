package com.example.scenebuilderfirst;

import entities.Escale;
import entities.Vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceEscale;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListEscController implements Initializable {
    @FXML
    private TableColumn<Escale, String> aeroesc;

    @FXML
    private Button ajesc;

    @FXML
    private TableColumn<Escale, String> haesc;

    @FXML
    private TableColumn<Escale, String> hdesc;

    @FXML
    private TableColumn<Escale, Void> jesc;

    @FXML
    private Button moesc;

    @FXML
    private Button supesc;
    @FXML
    private TableView<Escale> tbesc;
    @FXML
    private TableColumn<Escale, Void> actionsColumn;

    ServiceEscale sesc = new ServiceEscale();
    private void refreshTable() {
        try {
            List<Escale> esc = sesc.selectAll();
            ObservableList<Escale> observableArrayList = FXCollections.observableArrayList(sesc.selectAll());
            tbesc.setItems(observableArrayList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





        actionsColumn.setCellFactory(column -> {
            return new TableCell<Escale, Void>() {
                private final Button deletebt = new Button("Supprimer");
                private final Button modify = new Button("Modifier");

                {
                    deletebt.setStyle("-fx-cursor: hand;");
                    modify.setStyle("-fx-cursor: hand;");

                    deletebt.setOnAction((event) -> {
                        try {
                            Escale escale = getTableView().getItems().get(getIndex());
                            sesc.deleteOne(escale.getId_escale());
                            refreshTable();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });


                    modify.setOnAction((event) -> {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("modifesc.fxml"));
                        Parent root= null;
                        try {
                            root = loader.load();
                            ModifyEscController mvc=loader.getController();
                            mvc.setEscale(getTableView().getItems().get(getIndex()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        modify.getScene().setRoot(root);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setGraphic(new javafx.scene.layout.HBox(deletebt, modify));
                    } else {
                        setGraphic(null);
                    }
                }
            };
        });}

    public void load() throws SQLException {
        List<Escale> escale = sesc.selectAll();
        System.out.println(escale.toString());
        ObservableList<Escale> esclist= FXCollections.observableArrayList(escale);
        aeroesc.setCellValueFactory(new PropertyValueFactory<>("aero_escale"));
        hdesc.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
        haesc.setCellValueFactory(new PropertyValueFactory<>("heure_arrivee"));
        jesc.setCellValueFactory(new PropertyValueFactory<>("jour_escale"));
        tbesc.setItems(esclist);

    }
}

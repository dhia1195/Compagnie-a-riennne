package com.example.scenebuilderfirst;

import entities.Vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServiceVol;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListeVolController implements Initializable {




        @FXML
        private TableColumn<Vol, String> cha;

        @FXML
        private TableColumn<Vol, String> chd;


        @FXML
        private TableColumn<Vol, Integer> cid;

        @FXML
        private TableColumn<Vol, String> cnum;

        @FXML
        private TableColumn<Vol, Void> actionsColumn;

        @FXML
        private TableView<Vol> volTable;

        @FXML

        private Button aj;
    @FXML
    private Button mo;

    @FXML
    private Button supp;

        ServiceVol sev = new ServiceVol();
    private void refreshTable() {
        try {
            List<Vol> user = sev.selectAll();
            ObservableList<Vol> observableArrayList = FXCollections.observableArrayList(sev.selectAll());
            volTable.setItems(observableArrayList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {



            aj.setOnAction(event -> {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("ajoutervol.fxml"));
                Parent root= null;
                try {
                    root = loader.load();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                aj.getScene().setRoot(root);
            });

            mo.setOnAction(eve->{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("listesc.fxml"));
                Parent root= null;
                try {
                    root = loader.load();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mo.getScene().setRoot(root);
            });

            supp.setOnAction(event -> {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Parent root= null;
                try {
                    root = loader.load();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                supp.getScene().setRoot(root);
            });

            try {
                load();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            actionsColumn.setCellFactory(column -> {
                return new TableCell<Vol, Void>() {
                    private final Button deletebt = new Button("Supprimer");
                    private final Button modify = new Button("Modifier");

                    {
                        deletebt.setStyle("-fx-cursor: hand;");
                        modify.setStyle("-fx-cursor: hand;");

                        deletebt.setOnAction((event) -> {
                            try {
                                Vol vol = getTableView().getItems().get(getIndex());
                                sev.deleteOne(vol.getId_vol());
                                refreshTable();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });


                        modify.setOnAction((event) -> {
                            FXMLLoader loader=new FXMLLoader(getClass().getResource("modifierVol.fxml"));
                            Parent root= null;
                            try {
                                root = loader.load();
                                ModifierVolController mvc=loader.getController();
                                mvc.setVol(getTableView().getItems().get(getIndex()));
                            } catch (IOException  e) {
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
            });
        }





    public void load() throws SQLException {
            List<Vol> vol = sev.selectAll();
            ObservableList<Vol> vlist= FXCollections.observableArrayList(vol);
            cha.setCellValueFactory(new PropertyValueFactory<>("heure_arrivee"));
            chd.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
            cid.setCellValueFactory(new PropertyValueFactory<>("jour_vol"));
            cnum.setCellValueFactory(new PropertyValueFactory<>("num_vol"));
            volTable.setItems(vlist);

    }



}









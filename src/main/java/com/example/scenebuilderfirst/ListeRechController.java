package com.example.scenebuilderfirst;

import entities.Vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceVol;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeRechController implements Initializable {

    @FXML
    private TableColumn<Vol, String> aeroarr;

    @FXML
    private TableColumn<Vol, String> aerodep;
    @FXML
    private TableView<Vol> tb;


    @FXML
    private TableColumn<Vol, String> jv;
    ServiceVol sev = new ServiceVol();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void load() throws SQLException {
        List<Vol> vol = sev.selectAll();
        ObservableList<Vol> vlist= FXCollections.observableArrayList(vol);
        aeroarr.setCellValueFactory(new PropertyValueFactory<>("aero_arrivee"));
        aerodep.setCellValueFactory(new PropertyValueFactory<>("aero_depart"));
        jv.setCellValueFactory(new PropertyValueFactory<>("jour_vol"));

        tb.setItems(vlist);

    }}



    /*public void load() throws SQLException {
        List<Vol> vol = sev.getAll();
        ObservableList<Vol> vlist= FXCollections.observableArrayList(vol);
        aeroarr.setCellValueFactory(new PropertyValueFactory<>("aero_arrivee"));
        aerodep.setCellValueFactory(new PropertyValueFactory<>("aero_depart"));
        jv.setCellValueFactory(new PropertyValueFactory<>("jour_vol"));
        tb.setItems(vlist);

    }
}

/*public class ListeRechController implements Initializable {

    @FXML
    private ChoiceBox<String> departChoiceBox;

    @FXML
    private ChoiceBox<String> arriveeChoiceBox;

    @FXML
    private ChoiceBox<String> dateChoiceBox;

    @FXML
    private TableView<Vol> volTableView;

    @FXML
    private TableColumn<Vol, String> aeroarrColumn;

    @FXML
    private TableColumn<Vol, String> aerodepColumn;

    @FXML
    private TableColumn<Vol, String> jvColumn;

    private ServiceVol sev = new ServiceVol();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisation des ChoiceBox
        departChoiceBox.getItems().addAll("Paris", "Lyon", "Marseille", "Nice");
        arriveeChoiceBox.getItems().addAll("Paris", "Lyon", "Marseille", "Nice");
        dateChoiceBox.getItems().addAll("2022-03-01", "2022-03-02", "2022-03-03");

        // Configuration des colonnes du TableView
        aeroarrColumn.setCellValueFactory(new PropertyValueFactory<>("aero_arrivee"));
        aerodepColumn.setCellValueFactory(new PropertyValueFactory<>("aero_depart"));
        jvColumn.setCellValueFactory(new PropertyValueFactory<>("jour_vol"));
    }

    @FXML
    public void search() throws SQLException {
        String depart = departChoiceBox.getValue();
        String arrivee = arriveeChoiceBox.getValue();
        String date = dateChoiceBox.getValue();

        List<Vol> vols = sev.getAll();

        ObservableList<Vol> observableVols = FXCollections.observableArrayList(vols);
        volTableView.setItems(observableVols);
    }
}*/


package com.example.scenebuilderfirst;

import entities.Escale;
import entities.Vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.ServiceEscale;
import services.ServiceVol;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class RechercheController implements Initializable {

    @FXML
    private TableColumn<Vol, String> aeroarr;

    @FXML
    private TableColumn<Vol, String> aerodep;

    @FXML
    private Button btrech;

    @FXML
    private ChoiceBox<String> comboBoxAeroArr;

    @FXML
    private ChoiceBox<String> comboBoxAeroDep;

    @FXML
    private ChoiceBox<String> comboBoxDate;

    @FXML
    private TableColumn<Vol, String> jv;

    @FXML
    private TableView<Vol> tb;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btrech.setOnAction(evet -> {

            try {
                load();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });







        ServiceVol sv = new ServiceVol();
        List<Vol> lv = new ArrayList<>();
        try {
            lv = sv.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<String> aero = new ArrayList<>();
        for (Vol vol : lv) {
            aero.add(vol.getAero_depart());
        }
        comboBoxAeroDep.getItems().addAll(aero);

        comboBoxAeroDep.setOnAction(eve->{
            comboBoxAeroDep.getValue();
            System.out.println(comboBoxAeroDep.getValue());
            try {
                List<Vol> lvv =  sv.selectByAeroportDepart( comboBoxAeroDep.getValue());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }




        });




        ServiceVol svv = new ServiceVol();
        List<Vol> lvv = new ArrayList<>();
        try {
            lvv = svv.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<String> aeroArr = new ArrayList<>();
        for (Vol vol : lvv) {
            aeroArr.add(vol.getAero_arrivee());
        }
        comboBoxAeroArr.getItems().addAll(aeroArr);

        comboBoxAeroArr.setOnAction(eve->{
            comboBoxAeroArr.getValue();
            System.out.println(comboBoxAeroArr.getValue());
            try {
                List<Vol> lvArr =  svv.findByAeroportArrivee( comboBoxAeroArr.getValue());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }




        });

        ServiceVol s = new ServiceVol();
        List<Vol> v = new ArrayList<>();
        try {
            v = s.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<String> Date = new ArrayList<>();
        for (Vol vol : v) {
            Date.add(vol.getJour_vol());
        }
        comboBoxDate.getItems().addAll(Date);

        comboBoxDate.setOnAction(ev->{
            comboBoxDate.getValue();
            System.out.println(comboBoxDate.getValue());
            try {
                List<Vol> date =  svv.selectByDate( comboBoxDate.getValue());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }




        });




            ServiceVol svol = new ServiceVol();
            List<String> dates = null;
            try {
                dates = svol.getAllDate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ObservableList<String> observableDates = FXCollections.observableArrayList(dates);
            comboBoxDate.setItems(observableDates);




    }
    ServiceVol s = new ServiceVol();
    public void load() throws SQLException {
        List<Vol> vol = s.getAll(comboBoxAeroDep.getValue(), comboBoxAeroArr.getValue(),comboBoxDate.getValue());
        ObservableList<Vol> vlist= FXCollections.observableArrayList(vol);
        aeroarr.setCellValueFactory(new PropertyValueFactory<>("aero_arrivee"));
        aerodep.setCellValueFactory(new PropertyValueFactory<>("aero_depart"));
        jv.setCellValueFactory(new PropertyValueFactory<>("jour_vol"));
        tb.setItems(vlist);

    }




    }





package com.example.scenebuilderfirst;

import entities.Escale;
import entities.Vol;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.ServiceEscale;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ModifyEscController implements Initializable {
    @FXML
    private TextField aero_escomod;

    @FXML
    private Button ajou;

    @FXML
    private Button annmod;

    @FXML
    private Button delete;

    @FXML
    private TextField h_arrmod;

    @FXML
    private TextField h_depmod;

    @FXML
    private TextField j_escmod;

    @FXML
    private Button modif;

    @FXML
    private Button valmod;
    @FXML
    private DatePicker pickmod;

    static Escale escale = null;
   Escale es = new Escale();
    ServiceEscale se = new ServiceEscale();
    public void setEscale (Escale esc){
        aero_escomod.setText(esc.getAero_escale());
        h_arrmod.setText(esc.getHeure_arrivee());
        h_depmod.setText(esc.getHeure_depart());
        j_escmod.setText(esc.getJour_escale());
        /*final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String getDateEscale = es.setJour_escale(pickmod.getValue().format(NEW_FORMATTER).toString());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");*/
    }



    ServiceEscale see= new ServiceEscale();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valmod.setOnAction(t -> {

            try {
                Escale es = new Escale();
                es.setAero_escale(j_escmod.getText());
                es.setHeure_depart(h_depmod.getText());
                es.setHeure_arrivee(h_arrmod.getText());
                // es.setJour_escale(j_esc.getText());
                final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String getDateEscale = es.setJour_escale(pickmod.getValue().format(NEW_FORMATTER).toString());

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                see.createOne(es);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("listEsc.fxml"));
                Parent root = null;
                try {
                    root = loader.load();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                valmod.getScene().setRoot(root);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        annmod.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("listesc.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            annmod.getScene().setRoot(root);
        });

        ajou.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("hello-esc.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ajou.getScene().setRoot(root);
        });

        delete.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("hello-esc.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            delete.getScene().setRoot(root);
        });

        modif.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("hello-esc.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            modif.getScene().setRoot(root);
        });



    }
}

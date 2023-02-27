package com.example.scenebuilderfirst;

import entities.Escale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceEscale;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjoutEscController implements Initializable {
    @FXML
    private TextField aero_esc;

    @FXML
    private Button ann;

    @FXML
    private TextField h_arr;

    @FXML
    private TextField h_dep;

    @FXML
    private TextField j_esc;

    @FXML
    private Button val;

    ServiceEscale se = new ServiceEscale();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        val.setOnAction(valid->{
            try {
                Escale es = new Escale();
                es.setAero_escale(aero_esc.getText());
                es.setHeure_depart(h_dep.getText());
                es.setHeure_arrivee(h_arr.getText());
                es.setJour_escale(j_esc.getText());
                se.createOne(es);

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }

            FXMLLoader loader=new FXMLLoader(getClass().getResource("hello-esc.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            val.getScene().setRoot(root);





        });

    }
}

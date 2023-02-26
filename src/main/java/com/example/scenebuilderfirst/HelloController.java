package com.example.scenebuilderfirst;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button ajout;

    @FXML
    private Button modifier;

    @FXML
    private Button suppression;

    @FXML
    void ajouter(ActionEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void supprimer(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ajout.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ajoutervol.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ajout.getScene().setRoot(root);
        });


    }
}
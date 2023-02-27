package com.example.scenebuilderfirst;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloEsc implements Initializable {
    @FXML
    private Button aj;

    @FXML
    private Button mod;

    @FXML
    private Button supp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            aj.setOnAction(event -> {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("ajouterescale.fxml"));
                Parent root= null;
                try {
                    root = loader.load();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                aj.getScene().setRoot(root);
            });

    }
}

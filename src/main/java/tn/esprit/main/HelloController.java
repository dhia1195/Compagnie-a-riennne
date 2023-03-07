package tn.esprit.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button ajout;

    @FXML
    private Button modifier;

    @FXML
    private Button suppression;
    @FXML
    private Button consulte;
    @FXML
    void modify(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("listesVol.fxml"));
        Parent root= null;
        try {
            root = loader.load();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        modifier.getScene().setRoot(root);

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

        consulte.setOnAction(ev->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            consulte.getScene().setRoot(root);

        });
        suppression.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("listesVol.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            suppression.getScene().setRoot(root);
        });


    }




}
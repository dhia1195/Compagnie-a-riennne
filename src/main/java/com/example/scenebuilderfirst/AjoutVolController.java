package com.example.scenebuilderfirst;

import entities.Escale;
import entities.Vol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import services.ServiceEscale;
import services.ServiceVol;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AjoutVolController implements Initializable {
    @FXML
    private TextField aero_arr;

    @FXML
    private TextField aero_dep;

    @FXML
    private Button ajout;

    @FXML
    private TextField h_arr;

    @FXML
    private TextField h_dep;

    @FXML
    private TextField id_av;

    @FXML
    private TextField id_esc;

    @FXML
    private TextField j_vol;

    @FXML
    private Button modifier;

    @FXML
    private Button nul;

    @FXML
    private TextField num_vol;

    @FXML
    private Button suppression;

    @FXML
    private Button valide;

    @FXML
    void valideButtonClicked(ActionEvent event) {


    }


    ServiceEscale se = new ServiceEscale();

ServiceVol sv = new ServiceVol();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nul.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            nul.getScene().setRoot(root);
        });

        valide.setOnAction(validation -> {
            try {
                Escale w = se.FindById(parseInt(id_esc.getText()));
                Vol v = new Vol();
                v.setNum_vol(parseInt(num_vol.getText()));
                v.setAero_arrivee(aero_arr.getText());
                v.setAero_depart(aero_dep.getText());
                v.setJour_vol(j_vol.getText());
                v.setHeure_arrivee(h_arr.getText());
                v.setHeure_depart(h_dep.getText());
                v.setId_avion(parseInt(id_av.getText()));
                v.setEscale(w);
                sv.createOne(v);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Les données ont été validées avec succès !");
                alert.showAndWait();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            valide.getScene().setRoot(root);


        });
        valide.setOnAction(validation -> {
            String errorMsg = "";

            if (num_vol.getText().isEmpty()) {
                errorMsg += "Le champ Numéro de vol est obligatoire.\n";
            }
            if (aero_arr.getText().isEmpty()) {
                errorMsg += "Le champ Aéroport d'arrivée est obligatoire.\n";
            }
            if (aero_dep.getText().isEmpty()) {
                errorMsg += "Le champ Aéroport de départ est obligatoire.\n";
            }
            if (j_vol.getText().isEmpty()) {
                errorMsg += "Le champ Jour de vol est obligatoire.\n";
            }
            if (h_arr.getText().isEmpty()) {
                errorMsg += "Le champ Heure d'arrivée est obligatoire.\n";
            }
            if (h_dep.getText().isEmpty()) {
                errorMsg += "Le champ Heure de départ est obligatoire.\n";
            }
            if (id_av.getText().isEmpty()) {
                errorMsg += "Le champ ID de l'avion est obligatoire.\n";
            }
            if (id_esc.getText().isEmpty()) {
                errorMsg += "Le champ ID de l'escale est obligatoire.\n";
            }

            if (!errorMsg.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Les champs suivants sont obligatoires :");
                alert.setContentText(errorMsg);
                alert.showAndWait();
                return;
            }


        });
    }}

















package com.example.scenebuilderfirst;

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

import static java.lang.Integer.parseInt;

public class ModifierVolController implements Initializable {
    @FXML
    private DatePicker picker;
    @FXML
    private TextField aero_arrmod;

    @FXML
    private TextField aero_depmod;

    @FXML
    private Button ajout;

    @FXML
    private Button annmod;

    @FXML
    private TextField h_arrmod;

    @FXML
    private TextField h_depmod;

    @FXML
    private TextField id_avmod;

    @FXML
    private TextField id_escmod;

    @FXML
    private TextField j_volmod;

    @FXML
    private Button modifier;

    @FXML
    private TextField num_volmod;

    @FXML
    private Button suppression;

    @FXML
    private Button validmod;

    static Vol v = null;
    Vol vl = new Vol();
ServiceEscale se = new ServiceEscale();
    public void setVol(Vol vol){
        aero_arrmod.setText(vol.getAero_arrivee());
        aero_depmod.setText(vol.getAero_depart());
        id_avmod.setText(String.valueOf(vol.getId_avion()));

        j_volmod.setText(vol.getJour_vol());


        num_volmod.setText(String.valueOf(vol.getNum_vol()));
        h_arrmod.setText(vol.getHeure_arrivee());
        h_depmod.setText(vol.getHeure_depart());








    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        annmod.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("listesVol.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            annmod.getScene().setRoot(root);
        });

        validmod.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("listesVol.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validmod.getScene().setRoot(root);
        });



    }
}

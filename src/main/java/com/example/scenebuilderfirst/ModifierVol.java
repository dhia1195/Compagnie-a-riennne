package com.example.scenebuilderfirst;

import entities.Vol;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifierVol implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validmod.setOnAction(modify-> {
            try {
                Vol vo = new Vol();
                vo.setJour_vol();
                vo.setAero_depart();
                vo.setAero_arrivee();
                vo.setHeure_depart();
                vo.getHeure_arrivee();
                vo.setId_avion();
                vo.setEscale();
                vo.setNum_vol();


            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }
}

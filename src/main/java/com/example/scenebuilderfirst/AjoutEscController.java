package com.example.scenebuilderfirst;

import entities.Escale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
    private DatePicker dpicker;


    @FXML
    private Button val;
    void valButtonClicked(ActionEvent event) {


    }

    ServiceEscale se = new ServiceEscale();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ann.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("hello-esc.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ann.getScene().setRoot(root);
        });
        val.setOnAction(valid->{
            try {
                Escale esc = new Escale();
                Escale es = new Escale();
                es.setAero_escale(aero_esc.getText());
                es.setHeure_depart(h_dep.getText());
                es.setHeure_arrivee(h_arr.getText());
               // es.setJour_escale(j_esc.getText());
                final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String getDateEscale = es.setJour_escale(dpicker.getValue().format(NEW_FORMATTER).toString());

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                se.createOne(es);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Les données ont été validées avec succès !");
                alert.showAndWait();
                esc=se.rechercheId(es);
                AjoutVolController avc = new AjoutVolController();
                ModifierVolController ac = new ModifierVolController();

                avc.setEscale(esc);
                ac.setEscale(esc);







            }catch (SQLException e) {
                throw new RuntimeException(e);
            }

            val.getScene().getWindow().hide();





        });

    }
}

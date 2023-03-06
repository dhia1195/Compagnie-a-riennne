package com.example.scenebuilderfirst;

import entities.Escale;
import entities.Vol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import services.ServiceEscale;
import services.ServiceVol;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.stage.Stage;
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
    private RadioButton checkboxAvec;

    @FXML
    private RadioButton checkboxSans;
    @FXML
    private Label labelajesc;



    @FXML
    private Button valide;
    @FXML
    private DatePicker dt;
    @FXML
    private Button buttonNext;

    @FXML
    private Label labelMessage;

    @FXML
    void valideButtonClicked(ActionEvent event) {


    }


    @FXML
    void onCheckboxAvecClicked(ActionEvent event) {
        if (checkboxAvec.isSelected()) {
            buttonNext.setVisible(true);
            labelMessage.setVisible(false);
        } else {
            buttonNext.setVisible(false);
        }
    }

    @FXML
    void onCheckboxSansClicked(ActionEvent event) {
        if (checkboxSans.isSelected()) {
            labelMessage.setText("Pas d'escale");
            labelMessage.setVisible(true);
            buttonNext.setVisible(false);
        } else {
            labelMessage.setVisible(false);
        }
    }







    ServiceEscale se = new ServiceEscale();

ServiceVol sv = new ServiceVol();

    public void AjoutEscScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ajouterescale.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 569, 400);
        Stage stage = new Stage();
        stage.setTitle("Ajouter Escale ");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(checkboxAvec, checkboxSans);

// add event listener to toggle group
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (toggleGroup.getSelectedToggle() != null) {
                // get selected radio button text
                String selectedText = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                System.out.println("Selected option: " + selectedText);
            }
        });

        buttonNext.setVisible(false);




        buttonNext.setOnAction(ev->{
            boolean allFieldsFilled = true;
            if(num_vol.getText().isEmpty() || aero_arr.getText().isEmpty() || aero_dep.getText().isEmpty()
                    || j_vol.getText() == null || h_arr.getText().isEmpty() || h_dep.getText().isEmpty()) {
                allFieldsFilled = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez remplir tous les champs obligatoires !");
                alert.showAndWait();
            }

            if(allFieldsFilled) {
                try {
                    AjoutEscScene();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            };
        });

        //suppression.setOnAction(ev->{
            //System.out.println(myEscale.toString());
        //});

        suppression.setOnAction(ev->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            suppression.getScene().setRoot(root);
        });



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
            if (num_vol.getText().isEmpty() || aero_arr.getText().isEmpty() || aero_dep.getText().isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs obligatoires");
                alert.setHeaderText("Veuillez remplir tous les champs obligatoires.");
                alert.showAndWait();
            } else {
            try {
                //Escale w = se.FindById(parseInt(id_esc.getText()));
                Vol v = new Vol();
                v.setNum_vol(parseInt(num_vol.getText()));
                v.setAero_arrivee(aero_arr.getText());
                v.setAero_depart(aero_dep.getText());
                //v.setJour_vol(j_vol.getText());
                final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String getDateVol = v.setJour_vol(dt.getValue().format(NEW_FORMATTER).toString());

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");


                v.setHeure_arrivee(h_arr.getText());
                v.setHeure_depart(h_dep.getText());
                v.setId_avion(3);
               // v.setEscale(w);
                if (myEscale!= null) {
                    v.setEscale(myEscale);

                }else
                {v.setEscale(null);}

                sv.createOne(v);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Les données ont été validées avec succès !");
                alert.showAndWait();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("listesVol.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            valide.getScene().setRoot(root);


        }





    });


    }



    private static Escale myEscale;
    public void setEscale(Escale esc) {
        myEscale=esc;


    }




        /*public static boolean controleSaisieHeure(String heure) {
            // Définir le pattern pour l'heure au format 19h20
            String pattern = "^\\d{0,60}h\\d{0,60}$";

            // Créer le Pattern et le Matcher
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(heure);

            // Vérifier si l'heure correspond au pattern
            if (!m.matches()) {
                return false;
            }

            // Extraire les heures et minutes
            String[] heureMin = heure.split("h");
            int heures = Integer.parseInt(heureMin[0]);
            int minutes = Integer.parseInt(heureMin[1]);

            // Vérifier que l'heure est valide
            if (heures < 0 || heures > 23) {
                return false;
            }

            // Vérifier que les minutes sont valides
            if (minutes < 0 || minutes > 59) {
                return false;
            }

            return true;
        }*/
    }























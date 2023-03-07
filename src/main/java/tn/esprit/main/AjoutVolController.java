package tn.esprit.main;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.jdbc.entities.Escale;
import tn.esprit.jdbc.entities.Vol;
import tn.esprit.jdbc.services.ServiceEscale;
import tn.esprit.jdbc.services.ServiceVol;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button chauffeur;
    @FXML
    private Button gavion;
    @FXML
    private Button gsiege;
    @FXML
    private Button reclamation;
    @FXML
    private Button dvol;
    @FXML
    private Button gvol;
    @FXML
    private Button gmoy;

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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ajouterescale.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 569, 400);
        Stage stage = new Stage();
        stage.setTitle("Ajouter Escale ");
        stage.setScene(scene);
        stage.show();
    }

    private static Escale myEscale;
    public void setEscale(Escale esc) {
        myEscale=esc;



    }

    /*DatePicker dtp = new DatePicker();
    LocalDate date = dtp.getValue();
    LocalDate dateActuelle = LocalDate.now();*/
    private boolean dou= true;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chauffeur.setOnAction(ch->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("adminChauffeurDash.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            chauffeur.getScene().setRoot(root);

        });

        logout_button.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        reclamation.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("addReponse.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gavion.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Ajouter2.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gsiege.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterS.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gmoy.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLGererM.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        dvol.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gvol.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("listesVol.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );


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


        buttonNext.setOnAction(ev -> {
            boolean allFieldsFilled = true;
            if (num_vol.getText().isEmpty() || aero_arr.getText().isEmpty() || aero_dep.getText().isEmpty()
                    || j_vol.getText() == null || h_arr.getText().isEmpty() || h_dep.getText().isEmpty()) {
                allFieldsFilled = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez remplir tous les champs obligatoires !");
                alert.showAndWait();
            }

            if (allFieldsFilled) {
                try {
                    AjoutEscScene();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
            ;
        });

        //suppression.setOnAction(ev->{
        //System.out.println(myEscale.toString());
        //});




        nul.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listesVol.fxml"));
            Parent root = null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            nul.getScene().setRoot(root);
        });



        valide.setOnAction(validation -> {
            if (num_vol.getText().isEmpty() || aero_arr.getText().isEmpty() || aero_dep.getText().isEmpty() || h_dep.getText().isEmpty() || h_arr.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs obligatoires");
                alert.setHeaderText("Veuillez remplir tous les champs obligatoires.");
                alert.showAndWait();
                dou = false;
            }

            if (!(h_dep.getText().isEmpty() && h_arr.getText().isEmpty())) {
                LocalTime heureDep = LocalTime.parse(h_dep.getText());
                LocalTime heureArr = LocalTime.parse(h_arr.getText());
                if (heureDep.isAfter(heureArr)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Heures invalides");
                    alert.setHeaderText("L'heure de départ doit être antérieure à l'heure d'arrivée.");
                    alert.showAndWait();
                    dou = false;

                }
                   /*  LocalTime hDep;
                        heureDep = LocalTime.parse(h_dep.getText());
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Champs obligatoires");
                        alert.setHeaderText("Heure de départ invalide");
                        alert.showAndWait();*/


                // Vérifier l'heure d'arrivée
                    /*LocalTime hArr;

                        heureArr = LocalTime.parse(h_arr.getText());
                        Alert aler = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Champs obligatoires");
                        alert.setHeaderText("Heure d'arrivée invalide");
                        alert.showAndWait();*/



                   /* if (heureDep.isAfter(heureArr)) {
                        Alert al = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Heures invalides");
                        alert.setHeaderText("L'heure de départ doit être antérieure à l'heure d'arrivée.");
                        alert.showAndWait();
                        dou=false;
                    }}*/


                //Escale w = se.FindById(parseInt(id_esc.getText()));
                Vol v = new Vol();
                v.setNum_vol(parseInt(num_vol.getText()));
                v.setAero_arrivee(aero_arr.getText());
                v.setAero_depart(aero_dep.getText());
                //v.setJour_vol(j_vol.getText());

                final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String getDateVol = v.setJour_vol(dt.getValue().format(NEW_FORMATTER).toString());

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               /* if (date.isEqual(dateActuelle)&&(date.isBefore(dateActuelle))){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Heures invalides");
                    alert.setHeaderText("La date sélectionnée est antérieure à la date actuelle.");
                    alert.showAndWait();

                }*/

                v.setHeure_arrivee(h_arr.getText());
                v.setHeure_depart(h_dep.getText());
                v.setId_avion(3);
                // v.setEscale(w);
                if (myEscale != null) {
                    v.setEscale(myEscale);

                } else {
                    v.setEscale(null);
                }

                try {
                    if (dou) {
                        sv.createOne(v);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succès");
                        alert.setHeaderText("Les données ont été validées avec succès !");
                        alert.showAndWait();

                    }

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
    }}





























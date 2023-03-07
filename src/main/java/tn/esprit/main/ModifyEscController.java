package tn.esprit.main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.jdbc.entities.Escale;
import tn.esprit.jdbc.services.ServiceEscale;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ModifyEscController implements Initializable {
    @FXML
    private TextField aero_escomod;


    @FXML
    private Button annmod;

    @FXML
    private Button delete;

    @FXML
    private TextField h_arrmod;

    @FXML
    private TextField h_depmod;

    @FXML
    private TextField j_escmod;


    @FXML
    private Button valmod;
    @FXML
    private DatePicker pickmod;
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

    static Escale escale;


    ServiceEscale se = new ServiceEscale();
    public void setEscale (Escale esc){
        aero_escomod.setText(esc.getAero_escale());
        h_arrmod.setText(esc.getHeure_arrivee());
        h_depmod.setText(esc.getHeure_depart());
        j_escmod.setText(esc.getJour_escale());
        /*final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String getDateEscale = es.setJour_escale(pickmod.getValue().format(NEW_FORMATTER).toString());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");*/
    }



    ServiceEscale see= new ServiceEscale();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valmod.setOnAction(t -> {

            try {
                Escale es = new Escale();
                es.setAero_escale(j_escmod.getText());
                es.setHeure_depart(h_depmod.getText());
                es.setHeure_arrivee(h_arrmod.getText());
                // es.setJour_escale(j_esc.getText());
                final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String getDateEscale = es.setJour_escale(pickmod.getValue().format(NEW_FORMATTER).toString());

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                see.createOne(es);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("listEsc.fxml"));
                Parent root = null;
                try {
                    root = loader.load();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                valmod.getScene().setRoot(root);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        annmod.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("listesc.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            annmod.getScene().setRoot(root);
        });




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

        fetch.setOnAction(f->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fetch.getScene().setRoot(root);
        });



    }


}

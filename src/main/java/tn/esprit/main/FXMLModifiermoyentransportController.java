/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.main;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import tn.esprit.jdbc.entities.MoyenTransport;
import tn.esprit.jdbc.services.MoyenTransportService;
import tn.esprit.jdbc.utils.Context;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static tn.esprit.jdbc.utils.CommonController.setSceneContent;


/**
 * FXML Controller class
 *
 * @author azizi
 */
public class FXMLModifiermoyentransportController implements Initializable {

    @FXML
    private Button btnModifier;
    @FXML
    private TextField txtdesc;
    private TextField txtnomv;
    private TextField txtville;
    @FXML
    private TextField txtprix;
    private TextField txtidp;
    @FXML
    private TextField txtphoto;
    private TextField txttype;

    private tn.esprit.jdbc.services.MoyenTransportService MoyenTransportService = new MoyenTransportService();
  
    @FXML
    private ChoiceBox choix_type;
    private String[] type = {"voiture", "utilitaires", "Selection"};
    MoyenTransport u = (MoyenTransport) Context.getInstance().getContextObject("moyentransport");

    @FXML
    private TextField txtmodele;
    @FXML
    private TextField txtfrais;
    @FXML
    private Button btnBack;
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
    private Button gmoy;
    @FXML
    private Button dvol;
    @FXML
    private Button gvol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        // TODO
        choix_type.getItems().addAll(type);

  
        System.out.println("hello");
        choix_type.setValue(u.getType());
         System.out.println("hello2");
        txtmodele.setText(u.getModele());
        txtphoto.setText(u.getPhoto());
      
        txtfrais.setText(String.valueOf(u.getFrais_chauffeur()));
        txtprix.setText(String.valueOf(u.getPrix_location_jour()));
     


        txtdesc.setText(u.getDescription());

  
    }

    @FXML
    private void ModifierV(ActionEvent event) {

        String type = choix_type.getValue().toString();
        String modele = txtmodele.getText();
        String photo = txtphoto.getText();
        float frais_chauffeur =Float.parseFloat( txtfrais.getText());
        float prix_location_jour = Float.parseFloat(txtprix.getText());
      
        String description = txtdesc.getText();
        

        MoyenTransportService sp = new MoyenTransportService();
        MoyenTransport a = new MoyenTransport(u.getId_moyent(), photo,  modele,  prix_location_jour,  frais_chauffeur,  description,  type);
        sp.modifier(a);
        List<MoyenTransport> vList = MoyenTransportService.afficheListe();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLGererM.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btnModifier.getScene().setRoot(root);

    }

//    private void promotion(ActionEvent event) {
//         try {
//            Parent root = FXMLLoader.load(getClass().getResource("/gui/FXMLCreePromotion.fxml"));
//            
//            Scene sc = new Scene(root);
//            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
//            stage.setScene(sc);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLModifierMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @FXML
    private void back(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLGererM.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btnModifier.getScene().setRoot(root);

    }


}

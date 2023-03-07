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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.ReservationTservice;
import tn.esprit.jdbc.utils.Context;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static tn.esprit.jdbc.utils.CommonController.setSceneContent;


/**
 * FXML Controller class
 *
 * @author azizi
 */
public class FXMLReservationController implements Initializable {

   
    @FXML
    private DatePicker tf_dated;
    @FXML
    private DatePicker tf_retourd;
           private tn.esprit.jdbc.services.ReservationTservice ReservationTservice = new ReservationTservice();
    @FXML
    private TextField tf_modele;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Button rvol;
    static User user;
    public void setClient(User u) {
        user=u;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rvol.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("recherche.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            RechercheController rc=loader.getController();

            rc.setClient(user);

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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("addReclamation.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AddReclamationController arc=loader.getController();
            arc.setClient(user);
            logout_button.getScene().setRoot(root);
        } );
        res.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLReservation.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FXMLReservationController rc=loader.getController();
            rc.setClient(user);
            logout_button.getScene().setRoot(root);
        } );
        fetch.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("client.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ClientController cc=loader.getController();
            try {
                cc.setClient(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            logout_button.getScene().setRoot(root);
        } );
        // TODO
             
    }
    @FXML
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button reclamation;
    @FXML
    private Button res;



    @FXML
    private void rechercher(ActionEvent event) {
        
                ReservationTservice sp=new ReservationTservice();
          String modele =tf_modele.getText();
          
          
              Date date_debut = java.sql.Date.valueOf(tf_dated.getValue());
     Date date_fin = java.sql.Date.valueOf(tf_retourd.getValue());
    
     

 if (checkbox.isSelected()) {
            Context.getInstance().addContextObject("DateF",date_fin);
            Context.getInstance().addContextObject("DateD",date_debut);
            Context.getInstance().addContextObject("modele",modele);
            Context.getInstance().addContextObject("statut_chauffeur",true);
             }
 else{
      Context.getInstance().addContextObject("DateF",date_fin);
            Context.getInstance().addContextObject("DateD",date_debut);
            Context.getInstance().addContextObject("modele",modele);
            Context.getInstance().addContextObject("statut_chauffeur",false);
 }


//   Location a = new Location( date_debut, date_fin,  lieu);
//   sp.ajouter(a);
//      
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLMoyenTransport.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLMoyenTransportController rcc=loader.getController();
        rcc.setClient(user);
        tf_dated.getScene().setRoot(root);

    }

    @FXML
    private void onCheckBoxChanged(ActionEvent event) {
     
    }

}

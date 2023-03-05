/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ReservationTransport;
import entities.MoyenTransport;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import services.ReservationTservice;
import static utils.CommonController.setSceneContent;
import utils.Context;

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
           private ReservationTservice ReservationTservice = new ReservationTservice();
    @FXML
    private TextField tf_modele;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             
    }    



    @FXML
    private void rechercher(ActionEvent event) {
        
                ReservationTservice sp=new ReservationTservice();
          String modele =tf_modele.getText();
              Date date_debut = java.sql.Date.valueOf(tf_dated.getValue());
     Date date_fin = java.sql.Date.valueOf(tf_retourd.getValue());
    
     
Context.getInstance().addContextObject("DateF",date_fin);
Context.getInstance().addContextObject("DateD",date_debut);
Context.getInstance().addContextObject("modele",modele);



//   Location a = new Location( date_debut, date_fin,  lieu);
//   sp.ajouter(a);
//      
        try {
            setSceneContent("FXMLMoyenTransport");
        } catch (IOException ex) {
            Logger.getLogger(FXMLMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

}

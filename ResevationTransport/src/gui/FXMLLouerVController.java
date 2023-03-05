/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.MoyenTransport;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import static utils.CommonController.setSceneContent;
import utils.Context;

/**
 * FXML Controller class
 *
 * @author azizi
 */
public class FXMLLouerVController implements Initializable {

    /**
     * Initializes the controller class.
     */
    MoyenTransport u = (MoyenTransport) Context.getInstance().getContextObject("moyentransport");
    Date dated = (Date) Context.getInstance().getContextObject("DateD");
    Date datef = (Date) Context.getInstance().getContextObject("DateF");

    @FXML
    private Button btnALouer;
    @FXML
    private ImageView IDimage;
    @FXML
    private Label affichage;
    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("test " + u);
        if (u.getDescription() != null) {
            long diffInMillies = Math.abs(datef.getTime() - dated.getTime());
            long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            affichage.setText("nom MoyenTransport :  " + u.getModele() + "\n"
                    + "description :  " + u.getDescription() + "\n" + "prix par jour :  " + u.getPrix_location_jour()+ "\n"
                    + "\n Prix total: "+daysBetween*u.getPrix_location_jour());
        } else {
            affichage.setText("Info non disponible");
        }

    }
    @FXML
    private void louer(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    
   
          try {  
            setSceneContent("FXMLMoyenTransport");
        } catch (IOException ex) {
            Logger.getLogger(FXMLMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

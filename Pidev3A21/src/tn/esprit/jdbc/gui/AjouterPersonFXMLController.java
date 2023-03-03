/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jdbc.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.jdbc.entities.Person;
import tn.esprit.jdbc.services.ServicePerson;

/**
 * FXML Controller class
 *
 * @author FGH
 */
public class AjouterPersonFXMLController implements Initializable {

    @FXML
    private Button btnReset;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfAge;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnAfficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void Reset(ActionEvent event) {
        tfNom.setText("");
        tfPrenom.setText("");
        tfAge.setText("");
    }

    @FXML
    private void ajouterPerson(ActionEvent event) {
        
        if (tfNom.getText().isEmpty() 
                || tfPrenom.getText().isEmpty()
                || tfAge.getText().isEmpty()) {
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        }else{
            Person p = new Person(tfNom.getText()
                    , tfPrenom.getText()
                    , Integer.parseInt(tfAge.getText()));
            
            ServicePerson sp = new ServicePerson();
            
            try {
                sp.createOne(p);
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur");
                al.setHeaderText("Erreur Interne");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
        }
        
    }

    @FXML
    private void afficherPersons(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherPersonFXML.fxml"));
            
            Parent root = loader.load();            
            AfficherPersonFXMLController fx = loader.getController();
            
            ServicePerson sp = new ServicePerson();
            String msg = sp.selectAll().toString();
            fx.setLbAffiche(msg);
            
            btnAfficher.getScene().setRoot(root);
            
//            Stage stage = new Stage();
//            stage.setTitle("Afficher Persons");
//            stage.setScene(scene); 
//            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(AjouterPersonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

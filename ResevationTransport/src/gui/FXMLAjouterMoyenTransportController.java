/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.MoyenTransport;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import services.MoyenTransportService;
import static utils.CommonController.setSceneContent;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class FXMLAjouterMoyenTransportController implements Initializable {

    @FXML
    private Button btnAjouter;
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtmodele;
    @FXML
    private TextField txtfrais;
    @FXML
    private TextField txtprix;
    @FXML
    private TextField txtphoto;
    @FXML
      private MoyenTransportService MoyenTransportService = new MoyenTransportService();
    @FXML
   private ChoiceBox choix_type;
       private String[] type = {"voiture", "utilitaires", "Selection"};
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            choix_type.getItems().addAll(type);

        // TODO
    }    

    @FXML
    private void AjouterV(ActionEvent event) {
            String type = choix_type.getValue().toString();
        String modele = txtmodele.getText();
        String photo = txtphoto.getText();
        float frais_chauffeur =Float.parseFloat( txtfrais.getText());
        float prix_location_jour = Float.parseFloat(txtprix.getText());
      
        String description = txtdesc.getText();
        

        MoyenTransportService sp = new MoyenTransportService();
        MoyenTransport a = new MoyenTransport( photo,  modele,  prix_location_jour,  frais_chauffeur,  description,  type);
        sp.ajouter(a);
try {
            setSceneContent("FXMLGererM");
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @FXML
    private void back(ActionEvent event) {
        try {
            setSceneContent("FXMLGererM");
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

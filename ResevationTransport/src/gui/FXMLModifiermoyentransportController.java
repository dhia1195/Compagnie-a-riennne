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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.MoyenTransportService;
import static utils.CommonController.setSceneContent;
import utils.Context;

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

    private MoyenTransportService MoyenTransportService = new MoyenTransportService();
  
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
         try {
            setSceneContent("FXMLGererM");
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            setSceneContent("FXMLGererM");
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

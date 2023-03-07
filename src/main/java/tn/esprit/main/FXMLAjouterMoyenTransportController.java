
package tn.esprit.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.jdbc.entities.MoyenTransport;
import tn.esprit.jdbc.services.MoyenTransportService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static tn.esprit.jdbc.utils.CommonController.setSceneContent;


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
   private ChoiceBox choix_type;
       private String[] type = {"voiture", "utilitaires", "Monospace"};
    @FXML
    private Button btnBack;
    @FXML
    private Button img;
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
    private TextField text_image;
    @FXML
    private Button dvol;
    @FXML
    private Button gvol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            choix_type.getItems().addAll(type);
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
    }    

    @FXML
    private void AjouterV(ActionEvent event) {
            String type = choix_type.getValue().toString();
        String modele = txtmodele.getText();
        
        String nomimage = text_image.getText();

        String photo = txtphoto.getText();
        float frais_chauffeur =Float.parseFloat( txtfrais.getText());
        float prix_location_jour = Float.parseFloat(txtprix.getText());
      
        String description = txtdesc.getText();
        

        MoyenTransportService sp = new MoyenTransportService();
        MoyenTransport a = new MoyenTransport( nomimage,  modele,  prix_location_jour,  frais_chauffeur,  description,  type);
        sp.ajouter(a);

        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLGererM.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btnAjouter.getScene().setRoot(root);

    }

    @FXML
    private void back(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLGererM.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btnAjouter.getScene().setRoot(root);
    }

    @FXML
    private void addimg(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Fichiers image", "*.png", "*.jpg", "*.gif"),
        new FileChooser.ExtensionFilter("Tous les fichiers", ".")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        String imagePath = selectedFile.getName();
        text_image.setText(imagePath);
    } 
    }

    
}

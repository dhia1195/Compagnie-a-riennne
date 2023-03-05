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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.MoyenTransportService;
import utils.CommonController;
import utils.Context;

/**
 * FXML Controller class
 *
 * @author azizi
 */
public class FXMLGererMController extends CommonController implements Initializable {

    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnMettreajour;

    private TableColumn colonneville;
    @FXML
    private TableColumn PrixColone;
    private TableColumn colonneidPROMOTION;
    @FXML
    private TableColumn colonnedescription;
    @FXML
    private TableColumn colonnetype;
    @FXML
    private TableView<MoyenTransport> TableMoyenTransport;
    private MoyenTransportService MoyenTservice = new MoyenTransportService();

    private TableColumn colonnenom_v;
    @FXML
    private TableColumn<?, ?> colonnemodele;
    @FXML
    private TableColumn<?, ?> colonnefrais;
    @FXML
    private TableColumn<?, ?> colonnephoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colonnemodele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        colonnetype.setCellValueFactory(new PropertyValueFactory<>("type"));
        PrixColone.setCellValueFactory(new PropertyValueFactory<>("prix_location_jour"));
       colonnefrais.setCellValueFactory(new PropertyValueFactory<>("frais_chauffeur"));
        colonnedescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colonnephoto.setCellValueFactory(new PropertyValueFactory<>("photo"));
        
        // récupère les données des utilisateurs depuis la base de données
        List<MoyenTransport> vleList = MoyenTservice.afficheListe();

        // affiche les données dans le tableau
        TableMoyenTransport.getItems().setAll(vleList);

//        // Ajoute un listener sur le tableau pour détecter quand une ligne est sélectionnée
//        TableMoyenTransport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MoyenTransport>() {
//            @Override
//            public void changed(ObservableValue<? extends MoyenTransport> obs, MoyenTransport oldSelection, MoyenTransport newSelection) {
//                if (newSelection != null) {
//                    // Récupère le MoyenTransport sélectionné
//                    MoyenTransport MoyenTransportSelectionne = TableMoyenTransport.getSelectionModel().getSelectedItem();
//                    // Charge le fichier FXML de la nouvelle interface
//                    FXMLLoader loader = new FXMLLoader(FXMLGererMoyenTransportController.this.getClass().getResource("FXMLLouerV.fxml"));
//                    Parent root;
//                    try {
//                        root = loader.load();
//                    } catch (IOException ex) {
//                        Logger.getLogger(FXMLGererMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
//                        return;
//                    }   // Obtient le contrôleur de la nouvelle interface
//                    FXMLLouerVController controller = loader.getController();
//                    // Configure le contrôleur avec les données du MoyenTransport sélectionné
//                    controller.initialiserAvecMoyenTransport(MoyenTransportSelectionne);
//                    // Charge la nouvelle interface dans une nouvelle fenêtre
//                    Scene scene = new Scene(root);
//                    Stage stage = new Stage();
//                    stage.setScene(scene);
//                    stage.show();
//                }
//            }
//        }}
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        /*   MoyenTransportService sp=new MoyenTransportService();
         
           String id_MoyenTransport = txtid_MoyenTransport.getText();
       int id = Integer  .parseInt(txtid.getText());
           String photo = txtphoto.getText();
           String ville = txtville.getText();
        float prix = Float.parseFloat(txtprix.getText());
        int id_promotion = Integer  .parseInt(txtid_promotion.getText());
        String description = txtdescription.getText();
        String type = txttype.getText();
         
        
   MoyenTransport a = new MoyenTransport(id_MoyenTransport,nom_v ,  id,  photo,  ville,  prix,  id_promotion,  description,  type);
   sp.ajouter(a);
         
     


   List<MoyenTransport> vList = MoyenTransportService.afficheListe();
        
        // affiche les données dans le tableau
        TableMoyenTransport.getItems().setAll(vList);*/

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/FXMLAjouterMoyenTransport.fxml"));

            Scene sc = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        MoyenTransport selectedMoyenTransport = TableMoyenTransport.getSelectionModel().getSelectedItem();

        if (selectedMoyenTransport == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No user selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a user in the table.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deletion");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println(selectedMoyenTransport);
            MoyenTservice.supprimer(selectedMoyenTransport);
        List<MoyenTransport> vleList = MoyenTservice.afficheListe();

            // affiche les données dans le tableau
            TableMoyenTransport.getItems().setAll(vleList);
        }
    }

    @FXML
    private void Metrreajour(ActionEvent event) {
        /*                    String id_MoyenTransport = txtid_MoyenTransport.getText();
           int id = Integer  .parseInt(txtid.getText());
           String photo = txtphoto.getText();
           String ville = txtville.getText();
        float prix = Float.parseFloat(txtprix.getText());
        int id_promotion = Integer  .parseInt(txtid_promotion.getText());
        String description = txtdescription.getText();
        String type = txttype.getText();
         
        MoyenTransportService sp=new MoyenTransportService();
  MoyenTransport a = new MoyenTransport(id_MoyenTransport,  id,  photo,  ville,  prix,  id_promotion,  description,  type);
   sp.modifier(a);
 List<MoyenTransport> vList = MoyenTransportService.afficheListe();
        
        // affiche les données dans le tableau
        TableMoyenTransport.getItems().setAll(vList);}*/
        MoyenTransport selectedMoyenTransport = TableMoyenTransport.getSelectionModel().getSelectedItem();

        Context.getInstance().addContextObject("moyentransport", selectedMoyenTransport);
        try {
            setSceneContent("FXMLModifiermoyentransport");
        } catch (IOException ex) {
            Logger.getLogger(FXMLModifiermoyentransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        MoyenTransport v = TableMoyenTransport.getSelectionModel().getSelectedItem();
    }
}

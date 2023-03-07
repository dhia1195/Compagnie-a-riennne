/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.main;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import tn.esprit.jdbc.entities.MoyenTransport;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.MoyenTransportService;
import tn.esprit.jdbc.utils.CommonController;
import tn.esprit.jdbc.utils.Context;
import tn.esprit.jdbc.utils.MaConnexion;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * FXML Controller class
 *
 * @author azizi
 */
public class FXMLMoyenTransportController extends CommonController implements Initializable {
    //  public class MoyenTransportService implements IService<MoyenTransport>{

    Statement stm;
    Connection conn;
    @FXML
    private TableColumn<?, ?> colonnemodele;
    @FXML
    private TableColumn<?, ?> colonnefrais;

    public FXMLMoyenTransportController() {
        conn = MaConnexion.getInstance().getCnx();
    }

    @FXML
    private Button btnBack;

    private TableColumn<?, ?> colonnenom_v;
    @FXML
    private TableColumn<?, ?> colonnetype;
    private TableColumn<?, ?> colonneville;
    @FXML
    private TableColumn<?, ?> PrixColone;
    @FXML
    private TableColumn<?, ?> colonnedescription;
    @FXML
    private TableView<MoyenTransport> TableMoyenTransport;
    @FXML
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button reclamation;
    @FXML
    private Button res;
    @FXML
    private Button rvol;
    private MoyenTransportService vs = new MoyenTransportService();
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

        /*  colonneville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        PrixColone.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colonneidPROMOTION.setCellValueFactory(new PropertyValueFactory<>("id_promotion"));
        colonnedescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colonnetype.setCellValueFactory(new PropertyValueFactory<>("type"));
         colonnenom_v.setCellValueFactory(new PropertyValueFactory<>("nom_v"));

            // récupère les données des utilisateurs depuis la base de données
            List<MoyenTransport> vleList = MoyenTransportService.afficheListe();
            
      
        
        // affiche les données dans le tableau
        TableMoyenTransport.getItems().setAll(vleList);*/
         
        Date datef = (Date) Context.getInstance().getContextObject("DateF");
        Date dateD = (Date) Context.getInstance().getContextObject("DateD");
        String modele = (String) Context.getInstance().getContextObject("modele");
        boolean statut_chauffeur = (boolean) Context.getInstance().getContextObject("statut_chauffeur");


        List<MoyenTransport> l = vs.afficherMoyenTransportsDisponibles(modele,  dateD, datef);
        if (l != null) {
            TableMoyenTransport.setItems(FXCollections.observableList(l));
            colonnemodele.setCellValueFactory(new PropertyValueFactory<>("modele"));
            colonnetype.setCellValueFactory(new PropertyValueFactory<>("type"));
            colonnefrais.setCellValueFactory(new PropertyValueFactory<>("frais_chauffeur"));
            PrixColone.setCellValueFactory(new PropertyValueFactory<>("prix_location_jour"));
            colonnedescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        }

    }

    @FXML
    private void back(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLReservation.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLReservationController rc=loader.getController();
        rc.setClient(user);
        btnBack.getScene().setRoot(root);

    }

    @FXML
    private void handleMouseAction(MouseEvent event) {

        MoyenTransport v = TableMoyenTransport.getSelectionModel().getSelectedItem();
        Context.getInstance().addContextObject("moyentransport", v);
        System.out.println(v);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLLouerV.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLLouerVController rrc=loader.getController();
        rrc.setClient(user);

        btnBack.getScene().setRoot(root);


    }
}

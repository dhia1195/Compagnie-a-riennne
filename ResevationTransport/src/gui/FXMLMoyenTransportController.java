/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.MoyenTransport;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.MoyenTransportService;
import utils.CommonController;
import static utils.CommonController.setSceneContent;
import utils.Context;
import utils.MyDB;

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
        conn = MyDB.getInstance().getConnexion();
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
    private MoyenTransportService vs = new MoyenTransportService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        try {
            setSceneContent("FXMLReservation");
        } catch (IOException ex) {
            Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {

        try {
            MoyenTransport v = TableMoyenTransport.getSelectionModel().getSelectedItem();
            Context.getInstance().addContextObject("moyentransport", v);
            System.out.println(v);
            setSceneContent("FXMLLouerV");
        } catch (IOException ex) {
            Logger.getLogger(FXMLMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

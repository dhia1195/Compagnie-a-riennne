package tn.esprit.jdbc.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.jdbc.entities.Avion;
import tn.esprit.jdbc.services.ServiceAvion;
import tn.esprit.jdbc.utils.MaConnexion;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ajouter2 implements Initializable {

    @FXML
    private Button bu_ajouter;

    @FXML
    private Button bu_modif;

    @FXML
    private Button bu_supp;

    @FXML
    private TableColumn<?, ?> capa_aff;

    @FXML
    private TextField capacite_pla;

    @FXML
    private TableColumn<?, ?> id_aff;

    @FXML
    private TextField id_pla;

    @FXML
    private TableColumn<?, ?> mod_aff;

    @FXML
    private TextField model_pla;
    @FXML
    private TableView<Avion> tab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table();

    }


    @FXML
    private void ajouter(ActionEvent event) {
        if (id_pla.getText().isEmpty()
                || model_pla.getText().isEmpty()

                || capacite_pla.getText().isEmpty()) {

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        } else {
            Avion v = new Avion(Integer.parseInt(id_pla.getText())
                    , model_pla.getText()
                    , Integer.parseInt(capacite_pla.getText())

            );
            ServiceAvion sv = new ServiceAvion();
            try {
                sv.createOne(v);
                id_pla.clear();
                model_pla.clear();
                capacite_pla.clear();

                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Succes");
                al.setHeaderText("Ajout réussi");
                al.show();
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur");
                al.setHeaderText("Erreur Interne");
                al.setContentText(ex.getMessage());
                al.show();
            }
        }
    }

    public void table() {

        id_aff.setCellValueFactory(new PropertyValueFactory<>("ID"));
        mod_aff.setCellValueFactory(new PropertyValueFactory<>("modele"));
        capa_aff.setCellValueFactory(new PropertyValueFactory<>("capacite"));

        tab.setItems(RecupBase());

    }

    private ObservableList<Avion> RecupBase() {
        ObservableList<Avion> list = FXCollections.observableArrayList();

        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();
        String sql = "SELECT * FROM avion";
        try {

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            ResultSet R = st.executeQuery();
            while (R.next()) {
                Avion a = new Avion();


                a.setID(R.getInt(1));
                a.setModele(R.getString(2));
                a.setCapacite(R.getInt(3));

                System.out.println(a);


                list.add(a);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return list;
    }


    @FXML
    void delete(ActionEvent event) {
        Avion avion = tab.getSelectionModel().getSelectedItem();
        ServiceAvion sr = new ServiceAvion();
        try{
            sr.deletOne(avion);
            table();
            //txtRec.setText("");


        } catch (SQLException ex) {
            Logger.getLogger(Ajouter2.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @FXML
    void modifier(ActionEvent event) {
        if (((((id_pla.getText().isEmpty()
                || model_pla.getText().isEmpty())
                || capacite_pla.getText().isEmpty()))))
                 {

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        }else {
            String s = id_pla.getText();
            int n = Integer.valueOf(s);
            Avion v = new  Avion();
            v.setID(Integer.parseInt(id_pla.getText()));
            v.setModele(model_pla.getText());
            v.setCapacite(Integer.parseInt(capacite_pla.getText()));


            ServiceAvion sv = new ServiceAvion();

            try {
                System.out.println(v);
                sv.updateOne(v);
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter2.class.getName()).log(Level.SEVERE, null, ex);
            }
            table();
            id_pla.clear();
            model_pla.clear();
            capacite_pla.clear();

        }
        try{
            Avion avion = tab.getSelectionModel().getSelectedItem();
            id_pla.setText(Integer.toString(avion.getID()));
            model_pla.setText(avion.getModele());
            capacite_pla.setText(Integer.toString(avion.getCapacite()));



        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    @FXML
    public void retrieve(javafx.scene.input.MouseEvent mouseEvent) {
        try{
            Avion avion = tab.getSelectionModel().getSelectedItem();
            id_pla.setText(Integer.toString(avion.getID()));
            model_pla.setText(avion.getModele());
            capacite_pla.setText(Integer.toString(avion.getCapacite()));



        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }



        /*@FXML
        void modifier(ActionEvent event) {
            String etat ="en cours";
            Avion u=new Avion();
            u = tab.getSelectionModel().getSelectedItem();
            Avion rec = new Avion(tab.getItems().get(index).getID(),
                    txtRec.getText(),LocalDate.now(),etat,u,rp);
            try{
                sr.updateOne(rec);
                AfficheRecById();
                txtRec.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(ListeReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }













 /*   @FXML
    private void afficher(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("Ajouter.fxml"));
        Scene mainScene = new Scene(main);
        Stage stage = (Stage) id_aff.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
*/



   /*FXML
    private void create(ActionEvent event) {}

       /* (id_pla.getId().isEmpty()
           /* || model_pla.getId()isEmpty
               /*   || capacite_pla.getCapacite().isEmpty()) {

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
*/

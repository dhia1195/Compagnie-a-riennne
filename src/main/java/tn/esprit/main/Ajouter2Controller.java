package tn.esprit.main;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.jdbc.API.PasswordResetEmail;
import tn.esprit.jdbc.API.SendAdminMail;
import tn.esprit.jdbc.entities.Avion;
import tn.esprit.jdbc.services.ServiceAvion;
import tn.esprit.jdbc.services.StatPiImplemt;
import tn.esprit.jdbc.utils.MaConnexion;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ajouter2Controller implements Initializable {

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
    private PieChart pieChart;

    @FXML
    private TextField model_pla;
    @FXML
    private TableView<Avion> tab;
    @FXML
    private  TextField rechercher;
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
    private Button dvol;
    @FXML
    private Button gvol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table();
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
        try {
            piechartInitilazer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    }

    public void piechartInitilazer() throws SQLException {
        StatPiImplemt statPiImplemt = new StatPiImplemt();


        ObservableList<PieChart.Data>  pieChartData = statPiImplemt.getNumberAvionByModel();

        System.out.println("pieChartData  "+pieChartData.size());

        pieChart.setData(pieChartData);

        //Setting the title of the Pie chart
        pieChart.setTitle("Statistique Avion 2023");

        //setting the direction to arrange the data
        pieChart.setClockwise(true);

        //Setting the length of the label line
        pieChart.setLabelLineLength(50);

        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);
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

        try {
            piechartInitilazer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    void rechercher() {
        try {
            ObservableList<Avion> list = RecupBase(); // On utilise la fonction RecupBase pour récupérer les données
            tab.setItems(list);
            FilteredList<Avion> listeFilter = new FilteredList<>(list, l -> true);
            rechercher.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                listeFilter.setPredicate(reclamation -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCase = newValue.toLowerCase();
                    if (reclamation.getModele().toLowerCase().contains(lowerCase)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });
            SortedList<Avion> sortedData = new SortedList<>(listeFilter);
            sortedData.comparatorProperty().bind(tab.comparatorProperty());
            tab.setItems(sortedData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @FXML
    void delete(ActionEvent event) {
        Avion avion = tab.getSelectionModel().getSelectedItem();
        ServiceAvion sr = new ServiceAvion();
        try {
            sr.deletOne(avion);
            table();
            //txtRec.setText("");


        } catch (SQLException ex) {
            Logger.getLogger(Ajouter2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void modifier(ActionEvent event) {
        if (((((id_pla.getText().isEmpty()
                || model_pla.getText().isEmpty())
                || capacite_pla.getText().isEmpty())))) {

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        } else {
            String s = id_pla.getText();
            int n = Integer.valueOf(s);
            Avion v = new Avion();
            v.setID(Integer.parseInt(id_pla.getText()));
            v.setModele(model_pla.getText());
            v.setCapacite(Integer.parseInt(capacite_pla.getText()));


            ServiceAvion sv = new ServiceAvion();

            try {
                System.out.println(v);
                sv.updateOne(v);
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            table();
            id_pla.clear();
            model_pla.clear();
            capacite_pla.clear();

        }
        try {
            Avion avion = tab.getSelectionModel().getSelectedItem();
            id_pla.setText(Integer.toString(avion.getID()));
            model_pla.setText(avion.getModele());
            capacite_pla.setText(Integer.toString(avion.getCapacite()));


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    public void retrieve(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            Avion avion = tab.getSelectionModel().getSelectedItem();
            id_pla.setText(Integer.toString(avion.getID()));
            model_pla.setText(avion.getModele());
            capacite_pla.setText(Integer.toString(avion.getCapacite()));


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private Button d;
    @FXML
    private TextField maail;


    @FXML
    public  void Mail( ) throws MessagingException, SQLException {

        SendAdminMail se=new SendAdminMail();
        String m=maail.getText();


        se.sendAdminEmail(m);


    }


    }


   /* @FXML

    public void pieChart(ContextMenuEvent contextMenuEvent) {
        ObservableList<PieChart.Data>pieChartData
                =FXCollections.observableArrayList(
                new PieChart.Data("dhia",13),
                new PieChart.Data("gdgd",22),
                new PieChart.Data("dqd",15));
        piechart.setData(pieChartData);
    }
}



    @FXML

}
        @FXML
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


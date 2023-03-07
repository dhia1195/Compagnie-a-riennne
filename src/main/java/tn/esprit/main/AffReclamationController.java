package tn.esprit.main;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tn.esprit.jdbc.entities.Reclamation;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.ServiceReclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AffReclamationController implements Initializable {
    @FXML
    private TableView<Reclamation> tab;

    @FXML
    private TableColumn<Reclamation, Integer> id_user;

    @FXML
    private TableColumn<Reclamation, String> type_reclamation;

    @FXML
    private TableColumn<Reclamation, String> date;

    @FXML
    private TableColumn<Reclamation, String> description;


    @FXML
    private Button supp;

    @FXML
    private TextField id;
    @FXML
    private Label erreur;
    @FXML
    private Button Ad;
    @FXML
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button reclamation;
    @FXML
    private Button res;

    private tn.esprit.jdbc.services.ServiceReclamation ServiceReclamation = new ServiceReclamation();
    @FXML
    private RadioButton radiobutton;
    @FXML
    private TextField rech;
    @FXML
    private Button rvol;
    static User user;
    static int idu;

    public void setClient(User u) {
        user=u;
        idu=u.getId();
    }














    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            type_reclamation.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));

            // récupère les données des utilisateurs depuis la base de données
            List<Reclamation> vleList =ServiceReclamation.selectAllById(idu);




            // affiche les données dans le tableau
            tab.getItems().setAll(vleList);
        } catch (SQLException ex) {
            Logger.getLogger(AffReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id_user.setVisible(false);
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
    }

    @FXML
    public void supp(ActionEvent actionEvent) throws SQLException {
        erreur.setText("");
        if (id.getText().isEmpty()) {
            erreur.setText("Entrez le ID!");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet élément ?");

            // Configurez les boutons et attendez la réponse de l'utilisateur
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            // Si l'utilisateur a cliqué sur "Oui", effectuez l'opération de modification
            if (result.get() == ButtonType.YES) {

                String id_reclamation = id.getText();
                ServiceReclamation bcd = new ServiceReclamation();
                bcd.deletOne(Integer.parseInt(id_reclamation));
                System.out.println("DELETE DONE!");
            }
        }

    }


    @FXML
    public void ref(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ref.fxml"));


        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Ad.getScene().getWindow();
            RefController cc=loader.getController();

            cc.setClient(user);
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @FXML
    private void recherche(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String type_reclamation = rech.getText();
            Reclamation searchedModel = ServiceReclamation.readBytype_reclamation(type_reclamation);
            if (searchedModel != null) {
                tab.getItems().setAll(searchedModel);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No type found");
                alert.setHeaderText(null);
                alert.setContentText("No type found .");
                alert.showAndWait();
            }
        }
    }
    public List<Reclamation> trierParType_reclamation(List<Reclamation> liste, boolean ordreCroissant) {
        Collections.sort(liste, new Comparator<Reclamation>() {
            @Override
            public int compare(Reclamation t1, Reclamation t2) {
                int resultat = t1.getType_reclamation().compareTo(t2.getType_reclamation());
                if (!ordreCroissant) {
                    resultat = -resultat;
                }
                return resultat;
            }


        });
        return liste;
    }
    @FXML
    private void trier(ActionEvent event) throws SQLException {
        boolean ordreCroissant = radiobutton.isSelected();
        List<Reclamation> ResList = this.ServiceReclamation.selectAll();
        List<Reclamation> listeTriee = trierParType_reclamation(ResList, ordreCroissant);
        tab.getItems().setAll(listeTriee);
    }
/*































    @FXML
    private void ButtonAction(ActionEvent evt) {
        Date date=date.getText();
        DefaultPieDataset pieDataset = new DefaultPieDataset() ;
        pieDataset.setValue("date", new Date(10));
        pieDataset.setValue("two", new Integer(20));
        pieDataset.setValue("three", new Integer(30));
        pieDataset.setValue("four", new Integer(40));
        JFreeChart chart = ChartFactory.createPieChart("Pie Chart",pieDataset,true,true,true);
        PiePlot P=(PiePlot) chart.getPlot();
        //P.setForegroundAlpha(TOP_ALIGNMENT);
        ChartFrame frame =new ChartFrame("Pie Chart",chart);
        frame.setVisible(true);
        frame.setSize(450,400);

    }

*/








}

package tn.esprit.main;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.jdbc.entities.Reponse;
import tn.esprit.jdbc.services.ServiceReponse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AffReponseController implements Initializable {
    @FXML
    private TableView<Reponse> tab;

    @FXML
    private TableColumn<Reponse, Integer> id_reclamation;

    @FXML
    private TableColumn<Reponse, String> dateR;

    @FXML
    private TableColumn<Reponse, String> reponse;

    @FXML
    private Button supp;

    @FXML
    private Button ref;
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

    private tn.esprit.jdbc.services.ServiceReponse ServiceReponse = new ServiceReponse();



/*
    //afficher
    public static ObservableList<Reponse> RecupBase(){

        ObservableList<Reponse> list = FXCollections.observableArrayList();

        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();
        String sql = "select *from Reponse";
        try {

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            ResultSet R = st.executeQuery();
            while (R.next()){
                Reponse r =new Reponse();
                r.setId_reclamation((R.getInt(1)));
                r.setDateR(R.getDate(2));
                r.setReponse(R.getString(3));

                list.add(r);
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return list;
    }


*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            id_reclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
            reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
            dateR.setCellValueFactory(new PropertyValueFactory<>("dateR"));


            // récupère les données des utilisateurs depuis la base de données
            List<Reponse> vleList =ServiceReponse.selectAll();




            // affiche les données dans le tableau
            tab.getItems().setAll(vleList);
        } catch (SQLException ex) {
            Logger.getLogger(AffReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id_reclamation.setVisible(false);
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
    }




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

                String id_reponse = id.getText();
                ServiceReponse bcd = new ServiceReponse();
                bcd.deletOne(Integer.parseInt(id_reponse));
                System.out.println("DELETE DONE!");
            }
        }

    }


    public void ref(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reef.fxml"));


        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Ad.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}


package tn.esprit.main;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import tn.esprit.jdbc.entities.Reclamation;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.ServiceReclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class RefController implements Initializable {
    @FXML
    private Label erreur;
    @FXML
    private Button valider;
    @FXML
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button reclamation;
    @FXML
    private Button res;
    @FXML
    private TextField description;
    @FXML
    private TextField id;
    @FXML
    private Button rvol;
    static User user;


    public void setClient(User u) {
        user=u;

    }
    public void ref(ActionEvent actionEvent) throws SQLException {
        erreur.setText("");
        if (id.getText().isEmpty()) {
            erreur.setText("Entrez le User ID!");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText("Êtes-vous sûr de vouloir modifier cet élément ?");

            // Configurez les boutons et attendez la réponse de l'utilisateur
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            // Si l'utilisateur a cliqué sur "Oui", effectuez l'opération de modification
            if (result.get() == ButtonType.YES) {
                String s= description.getText();

                String id1 = id.getText();

                Reclamation t = new Reclamation();
                t.setDescription(s);


                ServiceReclamation bcd = new ServiceReclamation();
                bcd.updateOne(t, Integer.parseInt(id1));

            }

        }

    }




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
    }



}


package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import entities.Reclamation;

import javax.swing.JOptionPane;

import services.ServiceReclamation;
import utils.MaConnexion;


public class RefController implements Initializable {
    @FXML
    private Label erreur;
    @FXML
    private Button valider;
    @FXML
    private TextField description;
    @FXML
    private TextField id;
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
        // TODO
    }


}

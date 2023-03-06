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
import static java.awt.Component.TOP_ALIGNMENT;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import services.ServiceReclamation;

import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import utils.MaConnexion;

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

    private ServiceReclamation ServiceReclamation = new ServiceReclamation();
    @FXML
    private RadioButton radiobutton;
    @FXML
    private TextField rech;














    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            type_reclamation.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            
            // récupère les données des utilisateurs depuis la base de données
            List<Reclamation> vleList =ServiceReclamation.selectAll();
            
            
            
            
            // affiche les données dans le tableau
            tab.getItems().setAll(vleList);
        } catch (SQLException ex) {
            Logger.getLogger(AffReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id_user.setVisible(false);
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
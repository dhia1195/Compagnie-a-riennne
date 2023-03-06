package gui;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import javafx.stage.Stage;
import entities.Reclamation;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import services.BadWordFilter;
import services.ServiceReclamation;

import utils.MaConnexion;


public class AddReclamationController implements Initializable{
    private Connection cnx;
    @FXML
    private TextField id_usera;
    @FXML
    private TextField type_reclamationa;
    @FXML
    private TextField descriptiona;
    @FXML
    private DatePicker tf_dated;

    public AddReclamationController(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    private TextField id_user;

    private TextField type_reclamation;

    private TextField date;

    private TextField description;
    @FXML
    private Button Af;
    @FXML
    private Button Ajout;


    //cliiiccckkkk
    private void addReclamation(ActionEvent event) throws SQLException{

        Ajout();
        id_user.clear();
        type_reclamation.clear();
        date.clear();
        description.clear();

    }
    /*@FXML
    private void Retour(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("affReclamation.fxml"))));
        stage.setScene(scene);
        stage.setTitle("Login");

        stage.show();




    }
*/
/*
    private void EnrejistreBase(){

        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();


        try {
            String sql = "INSERT INTO Reclamation (`id_user`, `type_reclamation`, `date`, `description`) VALUES (?,?,?,?)";

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.setInt(2, Integer.parseInt(id_user.getText()));

            st.setString(2, type_reclamation.getText());

            st.setString(3, date.getText());

            st.setString(4, description.getText());



            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }


    }
*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        // TODO
    }



    @FXML
    public void Ajout() throws SQLException {
        if (id_usera.getText().isEmpty() || type_reclamationa.getText().isEmpty() || descriptiona.getText().isEmpty()){ Alert alert = new Alert(Alert.AlertType.WARNING); alert.setTitle("Champs manquants"); alert.setHeaderText(null); alert.setContentText("Veuillez remplir tous les champs !"); alert.showAndWait(); return; } 
        
                ServiceReclamation sp=new ServiceReclamation();
          int id_u =Integer.parseInt(id_usera.getText());
          String type=type_reclamationa.getText();
         // String desc=descriptiona.getText();
          
         Date date = java.sql.Date.valueOf(tf_dated.getValue());
         //badwords
         String desc = BadWordFilter.filter(descriptiona.getText());
        System.out.println(desc);
         
 
   Reclamation a = new Reclamation( id_u,type,date,desc);
   sp.createOne(a);


    }

    @FXML
    public void Afficher(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affReclamation.fxml"));


            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) Af.getScene().getWindow();
                stage.setScene(scene);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }


    }






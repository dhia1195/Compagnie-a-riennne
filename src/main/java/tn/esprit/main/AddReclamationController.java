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
import tn.esprit.jdbc.services.BadWordFilter;
import tn.esprit.jdbc.services.ServiceReclamation;
import tn.esprit.jdbc.utils.MaConnexion;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button reclamation;
    @FXML
    private Button Ajout;
    @FXML
    private Button rvol;
    @FXML
    private Button res;
    static User user;

    public void setClient(User u) {
        user=u;
    }


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
    }



    @FXML
    public void Ajout() throws SQLException {
        if (type_reclamationa.getText().isEmpty() || descriptiona.getText().isEmpty()){ Alert alert = new Alert(Alert.AlertType.WARNING); alert.setTitle("Champs manquants"); alert.setHeaderText(null); alert.setContentText("Veuillez remplir tous les champs !"); alert.showAndWait(); return; }

        ServiceReclamation sp=new ServiceReclamation();

        String type=type_reclamationa.getText();
        // String desc=descriptiona.getText();

        Date date = java.sql.Date.valueOf(tf_dated.getValue());
        //badwords
        String desc = BadWordFilter.filter(descriptiona.getText());
        System.out.println(desc);


        Reclamation a = new Reclamation( user.getId(),type,date,desc);
        sp.createOne(a);


    }

    @FXML
    public void Afficher(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affReclamation.fxml"));


        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Af.getScene().getWindow();
            AffReclamationController afrc=loader.getController();
            afrc.setClient(user);
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }


}

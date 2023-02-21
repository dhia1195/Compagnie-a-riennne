package tn.esprit.vromvrom.GUI;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.entities.Reclamation;
import tn.esprit.vromvrom.services.ServiceReclamation;
import tn.esprit.vromvrom.utils.MaConnexion;

public class AddReclamationController implements Initializable{
    private Connection cnx;

    public AddReclamationController(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    @FXML
    private TextField id_user;

    @FXML
    private TextField type_reclamation;

    @FXML
    private TextField date;

    @FXML
    private TextField description;

    @FXML
    private Button Ajout;
    @FXML
    private void AddReclamation(ActionEvent event){

        EnrejistreBase();
        id_user.clear();
        type_reclamation.clear();
        date.clear();
        description.clear();

    }

    private void EnrejistreBase(){

        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();


        try {
            String sql = "INSERT INTO Reclamation (`id_user`, `type_reclamation`, `date`, `description`) VALUES (?,?,?,?,?)";

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.setString(1, id_user.getText());
            st.setString(2, type_reclamation.getText());

            st.setString(3, date.getText());

            st.setString(4, description.getText());



            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


}

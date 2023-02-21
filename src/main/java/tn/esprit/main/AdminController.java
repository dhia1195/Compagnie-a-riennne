package tn.esprit.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> nameCol;
    @FXML
    private TableColumn<User, String> prenameCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, String> passwordCol;
    @FXML
    private TableColumn<User, String> sexeCol;
    @FXML
    private TableColumn<User, String> editCol;
    @FXML
    private Button logout_button;

    @FXML
    private Button fetch;


    ServiceUser su = new ServiceUser();

    @FXML
    private void refreshTable() {
        try {
            List<User> user = su.selectAll();
            ObservableList<User> observableArrayList = FXCollections.observableArrayList(su.selectAll());
            usersTable.setItems(observableArrayList);
            } catch (SQLException ex) {
            ex.printStackTrace();
    }}



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadDate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    }
    private void loadDate() throws SQLException {
        List<User> user = su.selectAll();
        ObservableList<User> observableArrayList = FXCollections.observableArrayList(su.selectAll());

        nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        sexeCol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        editCol.setCellFactory(column -> {
            TableCell<User, String> cell = new TableCell<>() {
                private final Button deleteBtn = new Button("delete");

                // Override the updateItem method to display the button
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteBtn);
                    }
                    deleteBtn.setOnMouseClicked((MouseEvent event) -> {
                        try {
                            su.deletOne(usersTable.getSelectionModel().getSelectedItem().getId());
                            refreshTable();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                }
            };
            return cell;
        });


        usersTable.setItems(observableArrayList);
    }


// Add the column to the table view

}

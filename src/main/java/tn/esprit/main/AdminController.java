package tn.esprit.main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.jdbc.entities.Role;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.ServiceRole;
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
    private TableColumn<User, Role> roleCol;
    @FXML
    private TableColumn<User, String> editCol;
    @FXML
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button chauffeur;
    @FXML
    private TextField search_field;
    ServiceUser su = new ServiceUser();
    ServiceRole sr = new ServiceRole();
    private  List<User> user = su.selectAll();

    public AdminController() throws SQLException {
    }

    public void setAdmin(User u) {








    }
    public class RoleCell extends TableCell<User, Role> {
        private ChoiceBox<Role> choiceBox;
        public RoleCell() throws SQLException {
            choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll(sr.selectAll());
            choiceBox.setOnAction(event -> {
                commitEdit(choiceBox.getValue());
                try {
                    User user = getTableView().getItems().get(getIndex());
                    su.updateRole(user.getId(),choiceBox.getValue().getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            choiceBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) {
                    commitEdit(choiceBox.getValue());
                }
            });
        }
        @Override
        protected void updateItem(Role item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                choiceBox.setValue(item);
                setGraphic(choiceBox);
            }
        }
        @Override
        public void startEdit() {
            super.startEdit();
            choiceBox.setValue(getItem());
            setGraphic(choiceBox);
        }
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setGraphic(null);
        }
    }

    private void refreshTable() {
        try {
            ObservableList<User> observableArrayList = FXCollections.observableArrayList(su.selectAll());
            usersTable.setItems(observableArrayList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        search_field.setOnKeyTyped(recherche -> {
            try {

                user=su.rechercheBy(search_field.getText());
                load();
                if (search_field.getText().isEmpty())
                    refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    }
    private void load() throws SQLException {

        ObservableList<User> observableArrayList = FXCollections.observableArrayList(user);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        sexeCol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleCol.setCellFactory(column -> {
            try {
                return new RoleCell();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        editCol.setCellFactory(column -> {
            TableCell<User, String> cell = new TableCell<>() {
                private final Button deleteBtn = new Button("delete");
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
                            User user = getTableView().getItems().get(getIndex());
                            su.deletOne(user.getId());
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
}
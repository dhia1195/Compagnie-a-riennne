package tn.esprit.jdbc.gui;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import com.sun.xml.internal.ws.util.xml.CDATA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.jdbc.entities.Avion;
import tn.esprit.jdbc.entities.Siege;
import tn.esprit.jdbc.services.ServiceAvion;
import tn.esprit.jdbc.services.ServiceSiege;
import tn.esprit.jdbc.utils.MaConnexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterSiege implements Initializable {

    @FXML
    private Button bu_aj1;

    @FXML
    private Button bu_modf1;

    @FXML
    private Button bu_supp1;
    @FXML
    private TextField rechercher;


    @FXML
    private TableColumn<Siege, Integer> num_aff1;

    @FXML
    private TableColumn<Siege, String> type_aff;

    @FXML
    private TextField id_pla2;


    @FXML
    private TextField type_pla;
    @FXML
    private TableView<Siege> tab1;

    //private ChoiceBox<String> checkbox;
    @FXML
    private LineChart linechart;


    //ObservableList<Siege> masterData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table();
    }


    public void ajoutersiege(javafx.event.ActionEvent actionEvent) {
        if (id_pla2.getText().isEmpty()
                || type_pla.getText().isEmpty()) {

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        } else {
            Siege v = new Siege(Integer.parseInt(id_pla2.getText())
                    , type_pla.getText());
            ServiceSiege sv = new ServiceSiege();
            try {
                sv.createOne(v);
                id_pla2.clear();
                type_pla.clear();


                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Succes");
                al.setHeaderText("Ajout réussi");
                al.show();
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur");
                al.setHeaderText("Erreur Interne");
                al.setContentText(ex.getMessage());
                al.show();
            }
        }
    }

    private void table() {

        num_aff1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        type_aff.setCellValueFactory(new PropertyValueFactory<>("typesiege"));


        tab1.setItems(RecupBase());

    }

    private ObservableList<Siege> RecupBase() {
        ObservableList<Siege> list = FXCollections.observableArrayList();

        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();
        String sql = "SELECT * FROM siege";
        try {

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            ResultSet R = st.executeQuery();
            while (R.next()) {
                Siege a = new Siege();


                a.setID(R.getInt(1));
                a.setTypesiege(R.getString(2));


                System.out.println(a);


                list.add(a);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return list;
    }

    @FXML
    void rechercher() {
        try {
            ObservableList<Siege> list = RecupBase(); // On utilise la fonction RecupBase pour récupérer les données
            tab1.setItems(list);
            FilteredList<Siege> listeFilter = new FilteredList<>(list, l -> true);
            rechercher.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                listeFilter.setPredicate(reclamation -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCase = newValue.toLowerCase();
                    if (reclamation.getTypesiege().toLowerCase().contains(lowerCase)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });
            SortedList<Siege> sortedData = new SortedList<>(listeFilter);
            sortedData.comparatorProperty().bind(tab1.comparatorProperty());
            tab1.setItems(sortedData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
@FXML
    void delete(ActionEvent event) {
        Siege siege = tab1.getSelectionModel().getSelectedItem();
        ServiceSiege sr = new ServiceSiege();
        try{
            sr.deletOne(siege);
            table();
            //txtRec.setText("");


        } catch (SQLException ex) {
            Logger.getLogger(AjouterSiege.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(ActionEvent actionEvent) {
        if (((((id_pla2.getText().isEmpty()
                || type_pla.getText().isEmpty())))))

        {

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        }else {
            String s = id_pla2.getText();
            int n = Integer.valueOf(s);
            Siege v = new  Siege();
            v.setID(Integer.parseInt(id_pla2.getText()));
            v.setTypesiege(type_pla.getText());



            ServiceSiege sv = new ServiceSiege();
            try {
                System.out.println(v);
                sv.updateOne(v);
            } catch (SQLException ex) {
                Logger.getLogger(AjouterSiege.class.getName()).log(Level.SEVERE, null, ex);
            }
            table();
            id_pla2.clear();
            type_pla.clear();


        }
    }

    public void retrieve1(MouseEvent mouseEvent) {
        try{
            Siege siege = tab1.getSelectionModel().getSelectedItem();
            id_pla2.setText(Integer.toString(siege.getID()));
            type_pla.setText(siege.getTypesiege());




        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    @FXML
    void linechart(MouseEvent event) {
        linechart.setTitle("Type siége choisie 2023");
                XYChart.Series series = new XYChart.Series();
        series.setName("SiegeType"); //setting series name (appear as legends)


        series.getData().add(new XYChart.Data("Economic class", 23));
        series.getData().add(new XYChart.Data("First class", 14));



        linechart.getData().add(series);


    }

    }


    /*@FXML
    void rechercher(KeyEvent ke) {
        FilteredList<Siege> filterData = new FilteredList<>(masterData, p -> true);
        rechercher.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(siege -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (siege.getTypesiege().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }


                return false;
            });
            SortedList<Siege> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tab1.comparatorProperty());
            tab1.setItems(sortedList);


        });

    }*/











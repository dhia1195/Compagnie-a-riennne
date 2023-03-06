package com.example.scenebuilderfirst;

import entities.Vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceVol;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    ServiceVol sev=new ServiceVol();
    @FXML
    private TableColumn<Vol, String> arr;



    @FXML
    private TableColumn<Vol, String> dep;

    @FXML
    private TableColumn<Vol, String> ha;

    @FXML
    private TableColumn<Vol, String> hd;

    @FXML
    private TableColumn<Vol, String> jv;

    @FXML
    private TableColumn<Vol, String> num;
    @FXML
    private TableView<Vol> tb;
    @FXML
    private PieChart pieChart;
    @FXML
    private StackedBarChart<String, Number> barChart;
    @FXML

    private CategoryAxis destination;
    @FXML

    private NumberAxis volcount;
    @FXML
    private Button retour;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        retour.setOnAction(ev->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            retour.getScene().setRoot(root);
        });


        try {
            load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            generateBarChart();

            pieChart.setData(createVolsEscalePieChart().getData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
    public PieChart createVolsEscalePieChart() {
        ServiceVol svv=new ServiceVol();

        int[] counts = new int[0];
        try {
            counts = svv.countVolsWithEscale();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int countWithoutEscale = counts[0];
        int countWithEscale = counts[1];

        PieChart.Data withoutEscale = new PieChart.Data("Sans escale", countWithoutEscale);
        PieChart.Data withEscale = new PieChart.Data("Avec escale", countWithEscale);

        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(withoutEscale, withEscale);

        return pieChart;
    }

    public void load() throws SQLException {
        List<Vol> vol = sev.selectAll();
        ObservableList<Vol> vlist= FXCollections.observableArrayList(vol);
        ha.setCellValueFactory(new PropertyValueFactory<>("heure_arrivee"));
        hd.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
        jv.setCellValueFactory(new PropertyValueFactory<>("jour_vol"));
        num.setCellValueFactory(new PropertyValueFactory<>("num_vol"));
        dep.setCellValueFactory(new PropertyValueFactory<>("aero_depart"));
        arr.setCellValueFactory(new PropertyValueFactory<>("aero_arrivee"));
        tb.setItems(vlist);

    }

    private void generateBarChart() throws SQLException {
        ServiceVol ss = new ServiceVol();
        List<Object[]> CountVolByAeroArr = ss.CountVolByAeroArr();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Object[] row : CountVolByAeroArr) {
            series.getData().add(new XYChart.Data<>(row[0].toString(), (int) row[1]));
        }
        barChart.getData().add(series);
        barChart.setLegendVisible(false);
        barChart.setTitle("Destination la plus visitée");

}}

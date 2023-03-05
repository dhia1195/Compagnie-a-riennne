package tn.esprit.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.chart.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import org.jfree.data.category.DefaultCategoryDataset;
import tn.esprit.jdbc.entities.ReservationTransport;
import tn.esprit.jdbc.entities.User;

import tn.esprit.jdbc.services.ReservationTservice;
import tn.esprit.jdbc.services.ServiceUser;

public class AdminChauffeurDashController implements Initializable {


    private BarChart<String, Number> barChart;
    @FXML
    private StackPane chartContainer;
    @FXML
    private StackPane chartContainer1;
    @FXML
    private Button fetch;
    @FXML
    private Button chauffeur;
    @FXML
    private Button logout_button;
    ServiceUser su=new ServiceUser();
    ReservationTservice rt=new ReservationTservice();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        fetch.setOnAction(f->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fetch.getScene().setRoot(root);
        });
        logout_button.setOnAction(f->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        });
        try {


            // add the chart to the container

            generateBarChart();
            chartContainer.getChildren().add(barChart);
            PieChart();
            chartContainer1.getChildren().add(PieChart());



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public PieChart PieChart() {
        ReservationTservice rts=new ReservationTservice();

        int[] counts = new int[0];
        try {
            counts = rts.countResWithChauf();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int countSansChauffer = counts[0];
        int countAvecChauffeur = counts[1];

        PieChart.Data sansChauffeur = new PieChart.Data("Sans chauffeur", countSansChauffer);
        PieChart.Data avecChauffeur = new PieChart.Data("Avec chauffeur", countAvecChauffeur);

        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(avecChauffeur, sansChauffeur);

        return pieChart;
    }

        public void generateBarChart() throws SQLException {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            List<ReservationTransport> rs = null;
            rs = rt.afficheListe();
            for (ReservationTransport r : rs) {
                if (r.getId_chauffeur()!=0) {
                    User user = su.findById(r.getId_chauffeur());
                    List<ReservationTransport> lister = rt.getByIdChauffeur(r.getId_chauffeur());


                    dataset.setValue(lister.size(), "Nombre de mission", user.getNom());
                }
            }

            barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
            Axis<String> xAxis = barChart.getXAxis();
            Axis<Number> yAxis = barChart.getYAxis();
            xAxis.setLabel("chauffeur");
            xAxis.setTickLabelsVisible(false);
            barChart.getXAxis().setTickLabelRotation(90);
            yAxis.setLabel("Nombre de mission");
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (Object columnKey : dataset.getColumnKeys()) {
                String label = (String) columnKey;
                XYChart.Data<String, Number> data = new XYChart.Data<>(label, dataset.getValue("Nombre de mission", (Comparable<?>) columnKey));
                // Set the label for the data
                data.setNode(new HoveredThresholdNode((int) (double) dataset.getValue("Nombre de mission", (Comparable<?>) columnKey), label));
                series.getData().add(data);
            }
            barChart.getData().add(series);
            barChart.setAnimated(true);
            barChart.setBarGap(0);
            barChart.setCategoryGap(10);
            barChart.setAnimated(false);
            barChart.setLegendVisible(false);
            barChart.setLegendSide(Side.RIGHT.RIGHT);

        }
        class HoveredThresholdNode extends StackPane {

            HoveredThresholdNode(int value, String label) {
                setPrefSize(15, 15);
                final Label valueLabel = new Label(String.valueOf(value));
                valueLabel.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
                valueLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
                valueLabel.setVisible(false);
                final Label dataLabel = new Label(label);
                dataLabel.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
                dataLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
                dataLabel.setVisible(false);
                setOnMouseEntered(mouseEvent -> {
                    valueLabel.setVisible(true);
                    dataLabel.setVisible(true);
                });
                setOnMouseExited(mouseEvent -> {
                    valueLabel.setVisible(false);
                    dataLabel.setVisible(false);
                });
                getChildren().setAll(valueLabel, dataLabel);
            }
        }
    }

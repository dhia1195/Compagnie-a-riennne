package tn.esprit.main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.chart.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import tn.esprit.jdbc.entities.User;

import tn.esprit.jdbc.services.ServiceUser;

public class ChauffeurDashController implements Initializable {


    private BarChart<String, Number> barChart;
    @FXML
    private StackPane chartContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {


            // add the chart to the container

            generateBarChart();
            chartContainer.getChildren().add(barChart);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        ServiceUser su=new ServiceUser();
        public void generateBarChart() throws SQLException {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            List<User> users = null;
            try {
                users = su.selectChauffeur();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (User user : users) {
                dataset.setValue(users.size(), "Nombre de mission", user.getNom());
            }

            JFreeChart chart = ChartFactory.createBarChart("Le meilleur chauffeur", "Chauffeur", "Nombre de mission", dataset);



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

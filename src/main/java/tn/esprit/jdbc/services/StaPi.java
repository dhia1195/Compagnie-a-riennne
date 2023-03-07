package tn.esprit.jdbc.services;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.SQLException;
import java.util.Map;

public interface StaPi {

    public ObservableList<PieChart.Data> getNumberAvionByModel() throws SQLException;

}


package tn.esprit.jdbc.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import tn.esprit.jdbc.entities.Avion;
import tn.esprit.jdbc.utils.MaConnexion;

import java.sql.*;
import java.util.Map;

public class StatPiImplemt implements StaPi {

    private Connection cnx;
    public StatPiImplemt(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    @Override
    public  ObservableList<PieChart.Data> getNumberAvionByModel() throws SQLException {

        String query = "SELECT modele , count(modele) as nbr FROM `avion` group by modele ; ";

        PreparedStatement ps = cnx.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        while (rs.next()){
             PieChart.Data data = new PieChart.Data(rs.getString("modele") +" :",Double.parseDouble(rs.getString("nbr")));
            pieChartData.add(data);

        }

        return pieChartData;
    }
}

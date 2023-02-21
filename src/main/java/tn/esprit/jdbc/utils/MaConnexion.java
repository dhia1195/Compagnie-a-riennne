package tn.esprit.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {

    private final String url = "jdbc:mysql://localhost:3310/esprit";
    private final String login = "root";
    private final String pwd = "";

    private Connection cnx;

    private static MaConnexion instance;

    private MaConnexion(){
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Cnnexion etablie !");
        } catch (SQLException e) {
            System.err.println("Erreur de oconnexion !");
            System.out.println(e.getMessage());
        }
    }

    public static MaConnexion getInstance(){
        if (instance == null) instance = new MaConnexion();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}

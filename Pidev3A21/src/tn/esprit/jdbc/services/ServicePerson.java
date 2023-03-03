package tn.esprit.jdbc.services;

import tn.esprit.jdbc.entities.Person;
import tn.esprit.jdbc.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePerson implements IService<Person> {

    private Connection cnx;

    public ServicePerson(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void createOne(Person person) throws SQLException {
        String req = "INSERT INTO `person`(`nom`, `prenom`, `age`)" +
                " VALUES ('"+person.getNom()+"', '"+person.getPrenom()+"',"+person.getAge()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Person ajouté !");
    }

    public void createOne1(Person person) throws SQLException {
        String req = "INSERT INTO `person`(`nom`, `prenom`, `age`)" +
                " VALUES (?, ?, ?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, person.getNom());
        st.setString(2, person.getPrenom());
        st.setInt(3, person.getAge());
        st.executeUpdate(req);
        System.out.println("Person ajouté !");
    }

    @Override
    public void updateOne(Person person) throws SQLException {

    }

    @Override
    public void deletOne(Person person) throws SQLException {

    }

    @Override
    public List<Person> selectAll() throws SQLException {
        List<Person> temp = new ArrayList<>();

        String req = "SELECT * FROM `person`";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Person p = new Person();

            p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString(3));
            p.setAge(rs.getInt("age"));

            temp.add(p);

        }


        return temp;
    }
}

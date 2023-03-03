package tn.esprit.jdbc.tests;

import tn.esprit.jdbc.entities.Person;
import tn.esprit.jdbc.services.ServicePerson;
import tn.esprit.jdbc.utils.MaConnexion;

import java.sql.SQLException;

public class MainClass {

    public static void main(String[] args) {
//        MaConnexion cn1 = MaConnexion.getInstance();
//        MaConnexion cn2 = MaConnexion.getInstance();
//        MaConnexion cn3 = MaConnexion.getInstance();
//        MaConnexion cn4 = MaConnexion.getInstance();
//
//
//        System.out.println(cn1.hashCode());
//        System.out.println(cn2.hashCode());
//        System.out.println(cn3.hashCode());
//        System.out.println(cn4.hashCode());

        Person p = new Person("Ghalleb", "Fakhreddine", 50);
        ServicePerson sp = new ServicePerson();

        try {
            
            sp.createOne(p);
            System.out.println(sp.selectAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}

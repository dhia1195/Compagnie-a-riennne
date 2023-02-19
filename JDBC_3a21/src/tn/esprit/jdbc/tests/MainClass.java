package tn.esprit.jdbc.tests;

import com.sun.xml.internal.bind.v2.model.core.ID;
import tn.esprit.jdbc.entities.Siege;
import tn.esprit.jdbc.services.ServiceSiege;

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

        //Avion p = new Avion(600, "boing", 50);
        //ServiceAvion sp = new ServiceAvion();
        Siege p = new Siege(700, "boing");
        ServiceSiege sp = new ServiceSiege();

        try {
            //sp.createOne(p);
            //System.out.println(sp.selectAll());
            sp.deletOne(p);
            System.out.println(sp.selectAll());

                    } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}

/*package test;

import entities.Reclamation;
import services.ServiceReclamation;
import services.ServiceReponse;
import entities.Reponse;

import java.sql.SQLException;

public class teste {
    public static void main(String[] args) {

        //  MaConnexion cn1 = MaConnexion.getInstance();
//        MaConnexion cn2 = MaConnexion.getInstance();
//        MaConnexion cn3 = MaConnexion.getInstance();
//        MaConnexion cn4 = MaConnexion.getInstance();
//
//   //   System.out.println(cn1.hashCode());
//        System.out.println(cn2.hashCode());
//        System.out.println(cn3.hashCode());
//        System.out.println(cn4.hashCode());


        //Reclamation p7 = new Reclamation(1,"moi","21/21/2022","aaaaaaaa");
        //Reclamation p8 = new Reclamation(1,"rimaaabbc","21/21/2022","aaaaaaaa");
        Reclamation p8 = new Reclamation(1,"rimaaabbcddd","21/21/2022","aaaaaaaa");
        //Reponse p4 = new Reponse(1,"21/21/2022","aaaaa");
        ///Reponse p8 = new Reponse(1,"21/21/2022","bbc");
        Reponse p7 = new Reponse(1,"21/21/2022","rimabb");





        ServiceReclamation sp = new ServiceReclamation();
        ServiceReponse sr = new ServiceReponse();

        try {
            //sr.createOne(p7);
            //sr.createOne(p7);

            //sr.updateOne(p7,5);
            //sr.deletOne(6);

            //sp.createOne(p8);
            //sp.createOne(p5);
            //sp.createOne(p8);
            //sp.createOne(p7);
            //sp.updateOne(p8,12);
            //sp.deletOne(12);
            //sp.updateOne(p8,2);

            System.out.println(sp.selectAll());
            System.out.println(sr.selectAll());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
*/
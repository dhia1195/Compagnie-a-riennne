/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.tests;

/**
 *
 * @author USER
 */


 import tn.esprit.vromvrom.entities.Reclamation;
 import tn.esprit.vromvrom.services.ServiceReclamation;
 import tn.esprit.vromvrom.services.ServiceReponse;
 import tn.esprit.vromvrom.entities.Reponse;


 import java.sql.SQLException;

public class vromvrom {

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


        //Reclamation p2 = new Reclamation(1,"mm","21/21/2022","aaaaaaaa");
        //Reclamation p8 = new Reclamation(1,"rimaaabbc","21/21/2022","aaaaaaaa");
        //Reclamation p1 = new Reclamation(1,"rimaaa","21/21/2022","aaaab");
        //Reclamation p4 = new Reclamation(1,"rimAAA","21/21/2022","aba");

        Reponse p2 = new Reponse(1,"21/21/2022","Rimaaa");
        //Reponse p8 = new Reponse(1,"21/21/2022","bbbbc");
        //Reponse p7 = new Reponse(1,"21/21/2022","rimabbccccccc");



         
         
        ServiceReclamation sp = new ServiceReclamation();
        ServiceReponse sr = new ServiceReponse();

        try {
             //sr.createOne(p2);
            //sr.createOne(p7);

            //sr.updateOne(p2,10);
            sr.deletOne(10);

            //sp.createOne(p2);
             //sp.createOne(p5);
            //sp.createOne(p8);
            //sp.createOne(p7);
            //sp.updateOne(p2,29);
            //sp.deletOne(29);
            //sp.updateOne(p8,2);
           
            System.out.println(sp.selectAll());
            System.out.println(sr.selectAll());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}

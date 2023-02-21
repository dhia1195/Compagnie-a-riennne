/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.ReservationTransfert;
import entities.moyentransfert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;


/**
 *
 * @author firas
 */
public class moyenTService implements IService<moyentransfert> {
         private final Connection conn;

    public moyenTService() {
        conn=MyDB.getInstance().getCnx();
    }
     @Override
    public void insert(moyentransfert t){
        String requete="insert into moyentransfert (model,prix,type,cautionnement,date_depart,date_retour) values (?,?,?,?,?,?)";
        try {
            PreparedStatement rec=conn.prepareStatement(requete);
            rec.setString(1,t.getModel());
            rec.setInt(2, t.getPrix());
            rec.setString(3, t.getType());
            rec.setInt(4, t.getCautionnement());
            rec.setDate(5, t.getDate_depart());
            rec.setDate(6, t.getDate_retour());
            rec.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(reservationTService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     @Override
     public void delete(moyentransfert t) {
        String requete="delete from moyentransfert where id_transfert = "+t.getId_transfert();
        try {
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(reservationTService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     @Override
      public void update(moyentransfert t) {
                 String requete = "update moyentransfert set  model=?,prix=?,type=?,cautionnement=?,date_depart=?,date_retour=? where id_transfert="+t.getId_transfert();
        try {
            PreparedStatement rec = conn.prepareStatement(requete);
             rec.setString(1,t.getModel());
            rec.setInt(2, t.getPrix());
            rec.setString(3, t.getType());
            rec.setInt(4, t.getCautionnement());
            rec.setDate(5, t.getDate_depart());
            rec.setDate(6, t.getDate_retour());
            rec.executeUpdate();
            System.out.println("Transfert mise à jour");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de le Transfert" + ex.getMessage());
        }
         
       
    }

    
     @Override
    public List<moyentransfert> readAll() throws SQLException {
         List<moyentransfert> moyennes = new ArrayList<>();
         String req = "select * from moyentransfert";
         Statement st=conn.createStatement();
         //ensemble de resultat
         ResultSet rst = st.executeQuery(req);

         while (rst.next()) {
             moyentransfert p = new moyentransfert(rst.getInt(1),//or rst.getInt(1)
                     rst.getString(2),rst.getInt(3), rst.getString(4),rst.getInt(5),rst.getDate(6),rst.getDate(7));
             moyennes.add(p);
         }
         return moyennes;
    }


    
}

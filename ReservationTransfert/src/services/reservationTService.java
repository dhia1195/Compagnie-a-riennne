/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.ReservationTransfert;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;
/**
 *
 * @author firas
 */

public class reservationTService implements IService<ReservationTransfert>{
     private final Connection conn;

    public reservationTService() {
        conn=MyDB.getInstance().getCnx();
    }

    
    
     @Override
    public void insert(ReservationTransfert t){
        String requete="insert into reservationtransfer (date,prix,nbr_jours,id_MT) values (?,?,?,?)";
        try {
            PreparedStatement rec=conn.prepareStatement(requete);
            rec.setDate(1, t.getDate());
            rec.setInt(2, t.getPrix());
            rec.setInt(3, t.getNb_jours());
            rec.setInt(4, t.getId_MT());
            rec.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(reservationTService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     @Override
     public void delete(ReservationTransfert t) {
        String requete="delete from reservationtransfer where id_reservationt = "+t.getId_reservationt();
        try {
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(reservationTService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     @Override
      public void update(ReservationTransfert t) {
                 String requete = "update reservationtransfer set  date=?,prix=?,nbr_jours=?,id_MT=? where id_reservationt="+t.getId_reservationt();
        try {
            PreparedStatement rec = conn.prepareStatement(requete);
           rec.setDate(1, t.getDate());
            rec.setInt(2, t.getPrix());
            rec.setInt(3, t.getNb_jours());
            rec.setInt(4, t.getId_MT());
            rec.executeUpdate();
            System.out.println("reservation mise à jour");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de le reservation" + ex.getMessage());
        }
         
       
    }

    
     @Override
    public List<ReservationTransfert> readAll() throws SQLException {
         List<ReservationTransfert> Transferts = new ArrayList<>();
         String req = "select * from reservationtransfer";
         Statement st=conn.createStatement();
         //ensemble de resultat
         ResultSet rst = st.executeQuery(req);

         while (rst.next()) {
             ReservationTransfert p = new ReservationTransfert(rst.getInt(1),//or rst.getInt(1)
                     rst.getDate(2),rst.getInt(3), rst.getInt(4),rst.getInt(5) );
             Transferts.add(p);
         }
         return Transferts;
    }

    
}
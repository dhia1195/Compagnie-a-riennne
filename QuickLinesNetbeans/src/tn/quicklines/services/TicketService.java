/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.quicklines.services;

import tn.quicklines.entities.Ticket;
import tn.quicklines.utils.MaConnexion;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author houce
 */
public class ServiceTicket implements IService<Ticket>{
        private Connection cnx;

    public ServiceTicket(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void createOne(Ticket ticket) throws SQLException {
        String req = "INSERT INTO `ticket`(`idTicket`, `idVolTicket`, `idSiegeTicket` ,`prixTicket`)" +
                " VALUES ('"+ticket.getIdTicket()+"', '"+ticket.getVolTicket()+"','"+ticket.getSiegeTicket()+"','"+ticket.getPrix()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Ticket ajouté !");//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOne(Ticket t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletOne(Ticket t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ticket> selectAll() throws SQLException {
        
         //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Ticket> selectClientVol(int idClient, int idVol) throws SQLException{
        
        String req="SELECT * FROM `ticket` WHERE idClient="+idClient+"AND idVol="+idVol;
        
        PreparedStatement ps = cnx.prepareStatement(req);
        
        List<Ticket> temp = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Ticket t=new Ticket();
            
            temp.add(t);
        }
        
        return temp;
        
    }


    
}

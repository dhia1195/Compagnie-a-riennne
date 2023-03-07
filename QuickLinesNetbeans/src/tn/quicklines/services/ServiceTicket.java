/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.quicklines.services;

import tn.quicklines.entities.*;
import tn.quicklines.utils.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.lang.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author houce
 */
public class ServiceTicket {
    private final String tableName="ticket_vol";
    private final String dataBaseName="quicklines";
    private static final Ticket jeton=new Ticket();
    private static MyORM<Ticket> ticketORM=new MyORM(jeton);
    
    public void create(Ticket ticket) throws SQLException{
        ticketORM.create(ticket, tableName);
    }
    public void update(Ticket ticket) throws SQLException, IllegalAccessException{
        ticketORM.update(ticket, dataBaseName, tableName);
    }
    public List<Entity> findByClientVol(int idVol, int idClient) throws SQLException, InstantiationException, IllegalAccessException{
        ArrayList<Object> values = new ArrayList();
        values.add(idVol);
        values.add(idClient);
        ArrayList<Integer> indexes = new ArrayList();
        values.add(3);
        values.add(6);
        return ticketORM.findByArgs(jeton, tableName,ticketORM.Args(jeton, indexes, values));
    }
    public List<Entity> findByVol(int idVol) throws SQLException, InstantiationException, IllegalAccessException{
        ArrayList<Object> values = new ArrayList();
        values.add(idVol);
        ArrayList<Integer> indexes = new ArrayList();
        values.add(3);
        return ticketORM.findByArgs(jeton, tableName,ticketORM.Args(jeton, indexes, values));
    }
    public List<Entity> findByClient(int idClient) throws SQLException, InstantiationException, IllegalAccessException{
        ArrayList<Object> values = new ArrayList();
        values.add(idClient);
        ArrayList<Integer> indexes = new ArrayList();
        values.add(6);
        return ticketORM.findByArgs(jeton, tableName,ticketORM.Args(jeton, indexes, values));
    }
    
    public List<Entity> findAll() throws SQLException, InstantiationException, IllegalAccessException{
        return ticketORM.findAll(jeton, tableName);
    }
    public Entity findById(int id) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException{
        return ticketORM.findById(id, jeton, tableName);
    }
    public Entity findByEntity(Ticket ticket) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException{
        return ticketORM.findByEntity(ticket, tableName);
    }
    public void delete(Ticket ticket) throws SQLException, InstantiationException, IllegalAccessException{
        ticketORM.delete(ticket, tableName);
    }
    
}

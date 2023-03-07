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
public class ServiceReservationVol {
    private final String tableName="reservation_vol";
    private final String dataBaseName="quicklines";
    private static final ReservationVol jeton=new ReservationVol();
    private static MyORM<Ticket> reservationVolORM=new MyORM(jeton);
    
    public void create(Ticket ticket) throws SQLException{
        reservationVolORM.create(ticket, tableName);
    }
    public void update(Ticket ticket) throws SQLException, IllegalAccessException{
        reservationVolORM.update(ticket, dataBaseName, tableName);
    }
    public List<Entity> findByClientVol(int idVol, int idClient) throws SQLException, InstantiationException, IllegalAccessException{
        ArrayList<Object> values = new ArrayList();
        values.add(idVol);
        values.add(idClient);
        ArrayList<Integer> indexes = new ArrayList();
        values.add(3);
        values.add(6);
        return reservationVolORM.findByArgs(jeton, tableName,reservationVolORM.Args(jeton, indexes, values));
    }
    public List<Entity> findByVol(int idVol) throws SQLException, InstantiationException, IllegalAccessException{
        ArrayList<Object> values = new ArrayList();
        values.add(idVol);
        ArrayList<Integer> indexes = new ArrayList();
        values.add(3);
        return reservationVolORM.findByArgs(jeton, tableName,reservationVolORM.Args(jeton, indexes, values));
    }
    public List<Entity> findByClient(int idClient) throws SQLException, InstantiationException, IllegalAccessException{
        ArrayList<Object> values = new ArrayList();
        values.add(idClient);
        ArrayList<Integer> indexes = new ArrayList();
        values.add(6);
        return reservationVolORM.findByArgs(jeton, tableName,reservationVolORM.Args(jeton, indexes, values));
    }
    
    public List<Entity> findAll() throws SQLException, InstantiationException, IllegalAccessException{
        return reservationVolORM.findAll(jeton, tableName);
    }
    public Entity findById(int id) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException{
        return reservationVolORM.findById(id, jeton, tableName);
    }
    public Entity findByEntity(Ticket ticket) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException{
        return reservationVolORM.findByEntity(ticket, tableName);
    }
    public void delete(Ticket ticket) throws SQLException, InstantiationException, IllegalAccessException{
        reservationVolORM.delete(ticket, tableName);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.services;

/**
 *
 * @author USER
 */


import java.sql.SQLException;
import java.util.List;

public interface IService<T> {

    void createOne(T t) throws SQLException;
    void updateOne(T t,int id) throws SQLException;
    void deletOne(int id) throws SQLException;
     
    List<T> selectAll() throws SQLException;


}


package tn.esprit.jdbc.services;

import tn.esprit.jdbc.entities.Escale;
import tn.esprit.jdbc.entities.Vol;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    void createOne1(T t) throws SQLException;
    void updateOne(T t) throws SQLException;
    void deleteOne(int t) throws SQLException;
    List<T> selectAll() throws SQLException;

    T FindById(int id) throws SQLException;



}

package tn.esprit.jdbc.services;

import java.sql.SQLException;
import java.util.List;

public interface IServiceV<T>{
    void createOne(T t) throws SQLException;
    void updateOne(T t) throws SQLException;
    void deleteOne(int t) throws SQLException;
    List<T> selectAll() throws SQLException;

    T FindById(int id) throws SQLException;



}

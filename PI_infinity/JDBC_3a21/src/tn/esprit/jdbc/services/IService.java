package tn.esprit.jdbc.services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {

    void createOne(T t) throws SQLException;
    boolean updateOne(T t) throws SQLException;
    boolean deletOne(T t) throws SQLException;
    List<T> selectAll() throws SQLException;




}

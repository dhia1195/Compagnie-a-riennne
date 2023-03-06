package services;

import java.sql.SQLException;
import java.util.List;
public interface IService<T> {
    void createOne(T t) throws SQLException;
    void updateOne(T t,int id) throws SQLException;
    void deletOne(int id) throws SQLException;

    List<T> selectAll() throws SQLException;

}

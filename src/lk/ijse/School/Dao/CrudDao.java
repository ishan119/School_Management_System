package lk.ijse.School.Dao;

import java.sql.SQLException;

public interface CrudDao <T,ID>{

    boolean add(T obj)throws SQLException,ClassNotFoundException;

    boolean update(T obj)throws SQLException,ClassNotFoundException;

    boolean delete(String obj)throws SQLException,ClassNotFoundException;

    T search(String obj)throws SQLException,ClassNotFoundException;
}

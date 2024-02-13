package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.Class;

import java.sql.SQLException;

public interface ClassDao extends CrudDao<Class,String> {
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastClassID() throws SQLException, ClassNotFoundException;
    ObservableList getallClassID() throws SQLException, ClassNotFoundException;
}

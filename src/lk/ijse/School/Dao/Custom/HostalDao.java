package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.Hostal;

import java.sql.SQLException;

public interface HostalDao extends CrudDao<Hostal,String> {
    ObservableList getallhostalID() throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastHostalID() throws SQLException, ClassNotFoundException;
}

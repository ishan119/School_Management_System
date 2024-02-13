package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.Teachers;

import java.sql.SQLException;

public interface TeachersDao extends CrudDao<Teachers,String> {
    ObservableList<Teachers> getallstudent()  throws SQLException, ClassNotFoundException;
    String getLastTeacherID() throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;

}

package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.StudentRoom;

import java.sql.SQLException;

public interface StudentRoomDao extends CrudDao<StudentRoom,String> {
    ObservableList getallRoom() throws SQLException, ClassNotFoundException;
}

package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.Room;

import java.sql.SQLException;

public interface RoomDao extends CrudDao<Room,String> {
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastRoomID() throws SQLException, ClassNotFoundException;
    ObservableList<Room> getallrooms()  throws SQLException, ClassNotFoundException;

}

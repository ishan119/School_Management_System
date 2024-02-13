package lk.ijse.School.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.School.To.Room;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface RoomService extends SuperService {
    String getNewModule() throws SQLException, ClassNotFoundException;
    boolean deleteroom(String id) throws SQLException, ClassNotFoundException;
    boolean updateroom(Room room) throws SQLException, ClassNotFoundException;
    boolean save(Room room) throws SQLException, ClassNotFoundException;
    ObservableList<Room> getallrooms()  throws SQLException, ClassNotFoundException;

}

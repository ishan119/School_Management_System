package lk.ijse.School.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.School.To.StudentRoom;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface StudentRoomService extends SuperService {
    ObservableList getallRoom() throws SQLException, ClassNotFoundException;
    boolean save(StudentRoom studentRoom) throws SQLException, ClassNotFoundException;
}

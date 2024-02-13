package lk.ijse.School.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.Impl.StudentRoomdaoImpl;
import lk.ijse.School.Dao.Custom.StudentRoomDao;
import lk.ijse.School.To.StudentRoom;
import lk.ijse.School.service.custom.StudentRoomService;

import java.sql.SQLException;

public class StudentRoomServiceImpl implements StudentRoomService {
    StudentRoomDao studentRoomDao = new StudentRoomdaoImpl();

    @Override
    public ObservableList getallRoom() throws SQLException, ClassNotFoundException {
        return studentRoomDao.getallRoom();
    }

    @Override
    public boolean save(StudentRoom studentRoom) throws SQLException, ClassNotFoundException {
        return studentRoomDao.add(studentRoom);
    }
}

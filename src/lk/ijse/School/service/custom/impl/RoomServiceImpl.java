package lk.ijse.School.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.Impl.RoomDaoImpl;
import lk.ijse.School.Dao.Custom.RoomDao;
import lk.ijse.School.To.Room;
import lk.ijse.School.service.custom.RoomService;

import java.sql.SQLException;

public class RoomServiceImpl implements RoomService {
    RoomDao roomDao = new RoomDaoImpl();

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteroom(String id) throws SQLException, ClassNotFoundException {
        return roomDao.delete(id);
    }

    @Override
    public boolean updateroom(Room room) throws SQLException, ClassNotFoundException {
        return roomDao.update(room);
    }

    @Override
    public boolean save(Room room) throws SQLException, ClassNotFoundException {
        return roomDao.add(room);
    }

    @Override
    public ObservableList<Room> getallrooms() throws SQLException, ClassNotFoundException {
        return roomDao.getallrooms();
    }

}

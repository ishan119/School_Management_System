package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.RoomDao;
import lk.ijse.School.To.Room;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDaoImpl implements RoomDao {
    @Override
    public boolean add(Room room) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO room VALUES (?, ?, ?, ?)");

        pstm.setString(1, room.getRoomID());
        pstm.setString(2, room.getHostalID());
        pstm.setString(3, room.getRoomDetails());
        pstm.setString(4, room.getNoOfBeds());


        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Room room) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE room SET HostalID=? ,RoomDetails=?, NoOfBed=? WHERE RoomID=?",
                room.getHostalID(), room.getRoomDetails(),room.getNoOfBeds(),room.getRoomID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From room where RoomID='"+id+"'")>0;

    }

    @Override
    public Room search(String obj) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastRoomId = getLastRoomID();
        if (lastRoomId == null) {
            return "R-000001";
        } else {
            String[] split = lastRoomId.split("[R][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newRoomID = String.format("R-%06d", lastDigits);
            return newRoomID;
        }
    }

    @Override
    public String getLastRoomID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT RoomID from room order by RoomID DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<Room> getallrooms() throws SQLException, ClassNotFoundException {
        ObservableList<Room> ob = FXCollections.observableArrayList();


        ResultSet rs = CrudUtil.execute("SELECT * FROM room");

        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Room obj = new Room(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            ob.add(obj);


        }
        return ob;
    }
}

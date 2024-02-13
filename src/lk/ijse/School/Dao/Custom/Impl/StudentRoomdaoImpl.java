package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.StudentRoomDao;
import lk.ijse.School.To.Room;
import lk.ijse.School.To.StudentRoom;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRoomdaoImpl implements StudentRoomDao {
    @Override
    public boolean add(StudentRoom studentRoom) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO student_room_details VALUES (?, ?, ?)");

        pstm.setString(1, studentRoom.getStudentID());
        pstm.setString(2, studentRoom.getRoomID());
        pstm.setString(3, studentRoom.getDateOfEntry());


        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(StudentRoom obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public StudentRoom search(String obj) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList getallRoom() throws SQLException, ClassNotFoundException {
        ObservableList<Room> ob = FXCollections.observableArrayList();
        ResultSet rs = CrudUtil.execute("SELECT * from room");
        while (rs.next()){
            ob.add(new Room(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
        }
        return ob;
    }
}

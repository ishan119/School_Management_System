package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.TeachersDao;
import lk.ijse.School.To.Teachers;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeachersDaoImpl implements TeachersDao {
    @Override
    public boolean add(Teachers teacher) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO teachers VALUES (?, ?, ?, ?, ?, ?)");

        pstm.setString(1, teacher.getTeacherID());
        pstm.setString(2, teacher.getTeacherName());
        pstm.setString(3, teacher.getAddress());
        pstm.setString(4, teacher.getGender());
        pstm.setString(5, teacher.getReligion());
        pstm.setString(6, teacher.getContact());


        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Teachers teachers) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE teachers SET TeacherName=? ,TeacherAddress=?, Gender=?, Religion=?, Contact=? WHERE TeacherID=?",
                teachers.getTeacherName(), teachers.getAddress(), teachers.getGender(),teachers.getReligion(),teachers.getContact(),teachers.getTeacherID());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From teachers where TeacherID='"+id+"'")>0;

    }

    @Override
    public Teachers search(String obj) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Teachers> getallstudent() throws SQLException, ClassNotFoundException {
        ObservableList<Teachers> ob = FXCollections.observableArrayList();


        ResultSet rs = CrudUtil.execute("SELECT * from teachers");

        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Teachers obj = new Teachers(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6));
            ob.add(obj);



        }
        return ob;
    }

    @Override
    public String getLastTeacherID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT TeacherID from teachers order by TeacherID DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastTeacherID = getLastTeacherID();
        if (lastTeacherID == null) {
            return "T-000001";
        } else {
            String[] split = lastTeacherID.split("[T][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newTeacherId = String.format("T-%06d", lastDigits);
            return newTeacherId;
        }
    }
}

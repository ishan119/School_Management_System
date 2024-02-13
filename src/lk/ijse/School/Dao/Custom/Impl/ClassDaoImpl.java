package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.ClassDao;
import lk.ijse.School.To.Student;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;
import lk.ijse.School.To.Class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassDaoImpl implements ClassDao {

    @Override
    public boolean add(Class class1) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Class VALUES (?, ?, ?, ?)");

        pstm.setString(1, class1.getClassId());
        pstm.setString(3, class1.getSectionID());
        pstm.setString(2, class1.getClassName());
        pstm.setString(4, class1.getDetails());



        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Class obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Class search(String obj) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastClassId = getLastClassID();
        if (lastClassId == null) {
            return "C-000001";
        } else {
            String[] split = lastClassId.split("[C][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newClassID = String.format("C-%06d", lastDigits);
            return newClassID;
        }
    }

    @Override
    public String getLastClassID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT ClassID from class order by ClassID DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList getallClassID() throws SQLException, ClassNotFoundException {
        ObservableList<Student> ob = FXCollections.observableArrayList();
        ResultSet rs = CrudUtil.execute("SELECT * from student");
        while (rs.next()){
            ob.add(new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getString(7),rs.getString(6),rs.getString(8),rs.getString(9),
                    rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13)));
        }
        return ob;
    }
}

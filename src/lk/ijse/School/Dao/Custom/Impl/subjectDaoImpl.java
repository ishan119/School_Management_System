package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.subjectDao;
import lk.ijse.School.To.Subject;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class subjectDaoImpl implements subjectDao {
    @Override
    public boolean add(Subject subject) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO subject VALUES (?, ?, ?)");

        pstm.setString(1, subject.getSubjectID());
        pstm.setString(2, subject.getSubjectName());
        pstm.setString(3, subject.getMedium());

        return pstm.executeUpdate() > 0;

    }

    @Override
    public boolean update(Subject subject) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE subject SET SubjectID=? , SubjectName=?, Medium=? WHERE SubjectID=?",
                subject.getSubjectID(), subject.getSubjectName(), subject.getMedium(), subject.getSubjectID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From subject where StudentID='"+id+"'")>0;

    }

    @Override
    public Subject search(String obj) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastSubjectID = getLastSubjectID();
        if (lastSubjectID == null) {
            return "SB-000001";
        } else {
            String[] split = lastSubjectID.split("[SB][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newSubjectId = String.format("SB-%06d", lastDigits);
            return newSubjectId;
        }
    }

    @Override
    public String getLastSubjectID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT SubjectId from subject order by SubjectID DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<Subject> getallstudent() throws SQLException, ClassNotFoundException {
        ObservableList<Subject> ob = FXCollections.observableArrayList();


        ResultSet rs = CrudUtil.execute("SELECT * from subject");

        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Subject obj = new Subject(rs.getString(1), rs.getString(2), rs.getString(3));
            ob.add(obj);


        }
        return ob;
    }
}

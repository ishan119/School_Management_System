package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.ExamDao;
import lk.ijse.School.To.Exam;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamDaoImpl implements ExamDao {
    @Override
    public boolean add(Exam exam) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO exam VALUES (?, ?)");

        pstm.setString(1, exam.getExamNumber());
        pstm.setString(2, exam.getExamName());


        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Exam exam) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE exam SET ExamName=? WHERE ExamNumber=?",
                exam.getExamName(),exam.getExamNumber());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From exam where ExamNumber='"+id+"'")>0;

    }

    @Override
    public Exam search(String obj) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList getallexam() throws SQLException, ClassNotFoundException {
        ObservableList<Exam> ob = FXCollections.observableArrayList();
        ResultSet rs = CrudUtil.execute("SELECT * from exam");
        while (rs.next()){
            ob.add(new Exam(rs.getString(1),rs.getString(2)));
        }
        return ob;
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastExamId = getLastExamID();
        if (lastExamId == null) {
            return "E-000001";
        } else {
            String[] split = lastExamId.split("[E][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newExamID = String.format("E-%06d", lastDigits);
            return newExamID;
        }
    }

    @Override
    public String getLastExamID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT ExamNumber from exam order by ExamNumber DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
}

package lk.ijse.School.Dao.Custom.Impl;

import lk.ijse.School.Dao.Custom.StudentExamDao;
import lk.ijse.School.To.StudentExam;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentExamDaoImpl implements StudentExamDao {
    @Override
    public boolean add(StudentExam studentExam) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO student_exam_details VALUES (?, ?, ?, ?, ?)");

        pstm.setString(1, studentExam.getStudentID());
        pstm.setString(2, studentExam.getExamNumber());
        pstm.setString(3, studentExam.getMarks());
        pstm.setString(4, studentExam.getResultType());
        pstm.setString(5, studentExam.getDateHeld());


        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(StudentExam obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public StudentExam search(String obj) throws SQLException, ClassNotFoundException {
        return null;
    }
}

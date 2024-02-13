package lk.ijse.School.service.custom;

import lk.ijse.School.To.StudentExam;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface StudentExamService extends SuperService {
    boolean save(StudentExam studentExam) throws SQLException, ClassNotFoundException;
}

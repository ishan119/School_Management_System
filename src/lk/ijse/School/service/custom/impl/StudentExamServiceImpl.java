package lk.ijse.School.service.custom.impl;

import lk.ijse.School.Dao.Custom.Impl.StudentExamDaoImpl;
import lk.ijse.School.Dao.Custom.StudentExamDao;
import lk.ijse.School.To.StudentExam;
import lk.ijse.School.service.custom.StudentExamService;

import java.sql.SQLException;

public class StudentExamServiceImpl implements StudentExamService {
    StudentExamDao studentExamDao = new StudentExamDaoImpl();

    @Override
    public boolean save(StudentExam studentExam) throws SQLException, ClassNotFoundException {
        return studentExamDao.add(studentExam);
    }
}

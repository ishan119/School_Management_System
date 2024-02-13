package lk.ijse.School.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.Impl.StudentDaoImpl;
import lk.ijse.School.Dao.Custom.StudentDao;
import lk.ijse.School.To.Student;
import lk.ijse.School.service.custom.StudentService;

import java.sql.SQLException;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return studentDao.getNewModule();
    }

    @Override
    public ObservableList<Student> getallstudent() throws SQLException, ClassNotFoundException {
        return studentDao.getallstudent();
    }

    @Override
    public boolean save(Student student) throws SQLException, ClassNotFoundException {
        return studentDao.add(student);
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDao.delete(id);
    }

    @Override
    public String getLaststudentIndex() throws SQLException, ClassNotFoundException {
        return studentDao.getLaststudentIndex();
    }

    @Override
    public ObservableList getallStudentIndex() throws SQLException, ClassNotFoundException {
        return studentDao.getallStudentIndex();
    }

    @Override
    public boolean updateStudent(Student student) throws SQLException, ClassNotFoundException {
        return studentDao.update(student);
    }
}

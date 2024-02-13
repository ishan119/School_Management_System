package lk.ijse.School.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.School.To.Student;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface StudentService extends SuperService {
    String getNewModule() throws SQLException, ClassNotFoundException;
    ObservableList<Student> getallstudent() throws SQLException, ClassNotFoundException;
    boolean save(Student student) throws SQLException, ClassNotFoundException;
    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;
    String getLaststudentIndex() throws SQLException, ClassNotFoundException;
    ObservableList getallStudentIndex() throws SQLException, ClassNotFoundException;
    boolean updateStudent(Student student) throws SQLException, ClassNotFoundException;


}

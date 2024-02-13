package lk.ijse.School.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.School.To.Subject;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface SubjectService extends SuperService {
    String getNewModule() throws SQLException, ClassNotFoundException;
    ObservableList<Subject> getallstudent()  throws SQLException, ClassNotFoundException;
    boolean updateSubject(Subject subject)  throws SQLException, ClassNotFoundException;
    boolean deleteTeachers(String id) throws SQLException, ClassNotFoundException;
    boolean save(Subject subject) throws SQLException, ClassNotFoundException;
}

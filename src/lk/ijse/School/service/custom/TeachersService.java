package lk.ijse.School.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.School.To.Teachers;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface TeachersService extends SuperService {
    String getNewModule() throws SQLException, ClassNotFoundException;
    ObservableList<Teachers> getallstudent()  throws SQLException, ClassNotFoundException;
    boolean save(Teachers teacher) throws SQLException, ClassNotFoundException;
    boolean updateTeachers(Teachers teachers) throws SQLException, ClassNotFoundException;
    boolean deleteTeachers(String id) throws SQLException, ClassNotFoundException;
}

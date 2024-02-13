package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao extends CrudDao<Student,String> {
    ObservableList<Student> getallstudent() throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLaststudentIndex() throws SQLException, ClassNotFoundException;
    ObservableList getallStudentIndex() throws SQLException, ClassNotFoundException;
    List<String> getStudentName() throws SQLException, ClassNotFoundException;

}

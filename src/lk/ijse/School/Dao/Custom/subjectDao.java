package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.Student;
import lk.ijse.School.To.Subject;

import java.sql.SQLException;
import java.util.List;

public interface subjectDao extends CrudDao<Subject,String> {
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastSubjectID()  throws SQLException, ClassNotFoundException;
    ObservableList<Subject> getallstudent()  throws SQLException, ClassNotFoundException;

}

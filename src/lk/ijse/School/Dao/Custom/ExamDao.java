package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.Exam;

import java.sql.SQLException;

public interface ExamDao extends CrudDao<Exam,String> {
    ObservableList getallexam() throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastExamID()  throws SQLException, ClassNotFoundException;
}

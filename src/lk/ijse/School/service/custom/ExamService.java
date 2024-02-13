package lk.ijse.School.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.School.To.Exam;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface ExamService extends SuperService {
    String getNewModule() throws SQLException, ClassNotFoundException;
    ObservableList getallexam() throws SQLException, ClassNotFoundException;
    boolean save(Exam exam) throws SQLException, ClassNotFoundException;
    boolean updateexam(Exam exam) throws SQLException, ClassNotFoundException;
    boolean deleteexam(String id) throws SQLException, ClassNotFoundException;


}

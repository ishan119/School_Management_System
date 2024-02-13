package lk.ijse.School.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.School.To.Section;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface SectionService extends SuperService {
    String getNewModule() throws SQLException, ClassNotFoundException;
    boolean save(Section section) throws SQLException, ClassNotFoundException;
    ObservableList<Section> getallsection()  throws SQLException, ClassNotFoundException;

}

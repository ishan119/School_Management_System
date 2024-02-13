package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.Section;

import java.sql.SQLException;

public interface SectionDao extends CrudDao<Section,String> {
    ObservableList<Section> getallsection()  throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastSectionID() throws SQLException, ClassNotFoundException;

}

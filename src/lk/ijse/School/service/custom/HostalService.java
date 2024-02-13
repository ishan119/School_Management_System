package lk.ijse.School.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.School.To.Hostal;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface HostalService extends SuperService {
    boolean save(Hostal hostal) throws SQLException, ClassNotFoundException;
    boolean updateHostal(Hostal hostal) throws SQLException, ClassNotFoundException;
    boolean deletehostal(String id) throws SQLException, ClassNotFoundException;

    ObservableList getallhostalID() throws SQLException, ClassNotFoundException;

    String getNewModule() throws SQLException, ClassNotFoundException;

    String getLastHostalID() throws SQLException, ClassNotFoundException;
}

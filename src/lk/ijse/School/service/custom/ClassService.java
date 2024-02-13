package lk.ijse.School.service.custom;

import lk.ijse.School.To.Class;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface ClassService extends SuperService {
    String getNewModule() throws SQLException, ClassNotFoundException;
    boolean save(Class class1) throws SQLException, ClassNotFoundException;

}

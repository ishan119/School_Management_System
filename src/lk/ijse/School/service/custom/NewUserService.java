package lk.ijse.School.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.School.To.NewUser;
import lk.ijse.School.service.SuperService;

import java.sql.SQLException;

public interface NewUserService extends SuperService {
    ObservableList<NewUser> getallnewUser() throws SQLException, ClassNotFoundException;
    boolean deleteNewUesr(String text) throws SQLException, ClassNotFoundException;
    boolean updateNewUser(NewUser newUser) throws SQLException, ClassNotFoundException;
    boolean save(NewUser newUser) throws SQLException, ClassNotFoundException;
    NewUser searchUser(String usrName) throws SQLException, ClassNotFoundException;

}

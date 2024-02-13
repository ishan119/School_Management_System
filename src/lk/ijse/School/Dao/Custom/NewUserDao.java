package lk.ijse.School.Dao.Custom;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.CrudDao;
import lk.ijse.School.To.NewUser;

import java.sql.SQLException;

public interface NewUserDao extends CrudDao<NewUser,String> {
    ObservableList<NewUser> getallnewUser() throws SQLException, ClassNotFoundException;
}

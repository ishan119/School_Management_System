package lk.ijse.School.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.Impl.NewUserDaoImpl;
import lk.ijse.School.Dao.Custom.NewUserDao;
import lk.ijse.School.To.NewUser;
import lk.ijse.School.service.custom.NewUserService;

import java.sql.SQLException;

public class NewUserServiceImpl implements NewUserService {
    NewUserDao newUserDao = new NewUserDaoImpl();

    @Override
    public ObservableList<NewUser> getallnewUser() throws SQLException, ClassNotFoundException {
        return newUserDao.getallnewUser();
    }

    @Override
    public boolean deleteNewUesr(String text) throws SQLException, ClassNotFoundException {
        return newUserDao.delete(text);
    }

    @Override
    public boolean updateNewUser(NewUser newUser) throws SQLException, ClassNotFoundException {
        return newUserDao.update(newUser);
    }

    @Override
    public boolean save(NewUser newUser) throws SQLException, ClassNotFoundException {
        return newUserDao.add(newUser);
    }

    @Override
    public NewUser searchUser(String usrName) throws SQLException, ClassNotFoundException {
        return newUserDao.search(usrName);
    }
}

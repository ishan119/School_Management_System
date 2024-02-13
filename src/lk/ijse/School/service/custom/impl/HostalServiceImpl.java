package lk.ijse.School.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.HostalDao;
import lk.ijse.School.Dao.Custom.Impl.ExamDaoImpl;
import lk.ijse.School.Dao.Custom.Impl.HostalDaoImpl;
import lk.ijse.School.To.Hostal;
import lk.ijse.School.service.custom.HostalService;

import java.sql.SQLException;

public class HostalServiceImpl implements HostalService {
    HostalDao hostalDao = new HostalDaoImpl();

    @Override
    public boolean save(Hostal hostal) throws SQLException, ClassNotFoundException {
        return hostalDao.add(hostal);
    }

    @Override
    public boolean updateHostal(Hostal hostal) throws SQLException, ClassNotFoundException {
        return hostalDao.update(hostal);
    }

    @Override
    public boolean deletehostal(String id) throws SQLException, ClassNotFoundException {
        return hostalDao.delete(id);
    }

    @Override
    public ObservableList getallhostalID() throws SQLException, ClassNotFoundException {
        return hostalDao.getallhostalID();
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return hostalDao.getNewModule();
    }

    @Override
    public String getLastHostalID() throws SQLException, ClassNotFoundException {
        return hostalDao.getLastHostalID();
    }
}

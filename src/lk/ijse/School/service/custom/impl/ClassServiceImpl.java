package lk.ijse.School.service.custom.impl;

import lk.ijse.School.Dao.Custom.ClassDao;
import lk.ijse.School.Dao.Custom.Impl.ClassDaoImpl;
import lk.ijse.School.Dao.Custom.Impl.StudentDaoImpl;
import lk.ijse.School.To.Class;
import lk.ijse.School.service.custom.ClassService;

import java.sql.SQLException;

public class ClassServiceImpl implements ClassService {
    ClassDao classDao = new ClassDaoImpl();

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return classDao.getNewModule();
    }

    @Override
    public boolean save(Class class1) throws SQLException, ClassNotFoundException {
        return classDao.add(class1);
    }
}

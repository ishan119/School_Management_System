package lk.ijse.School.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.Impl.TeachersDaoImpl;
import lk.ijse.School.Dao.Custom.TeachersDao;
import lk.ijse.School.To.Teachers;
import lk.ijse.School.service.custom.TeachersService;

import java.sql.SQLException;

public class TeachersServiceImpl implements TeachersService {
    TeachersDao teachersDao = new TeachersDaoImpl();

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return teachersDao.getNewModule();
    }

    @Override
    public ObservableList<Teachers> getallstudent() throws SQLException, ClassNotFoundException {
        return teachersDao.getallstudent();
    }

    @Override
    public boolean save(Teachers teacher) throws SQLException, ClassNotFoundException {
        return teachersDao.add(teacher);
    }

    @Override
    public boolean updateTeachers(Teachers teachers) throws SQLException, ClassNotFoundException {
        return teachersDao.update(teachers);
    }

    @Override
    public boolean deleteTeachers(String id) throws SQLException, ClassNotFoundException {
        return teachersDao.delete(id);
    }
}

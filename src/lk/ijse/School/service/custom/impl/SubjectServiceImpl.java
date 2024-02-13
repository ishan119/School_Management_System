package lk.ijse.School.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.Impl.subjectDaoImpl;
import lk.ijse.School.Dao.DaoFactory;
import lk.ijse.School.Dao.DaoType;
import lk.ijse.School.To.Subject;
import lk.ijse.School.service.custom.SubjectService;
import lk.ijse.School.Dao.Custom.subjectDao;

import java.sql.SQLException;

public class SubjectServiceImpl implements SubjectService {
   // SubjectService subjectService = new SubjectServiceImpl();
    subjectDao subjectDao = new subjectDaoImpl();

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return subjectDao.getNewModule();
    }

    @Override
    public ObservableList<Subject> getallstudent() throws SQLException, ClassNotFoundException {
        return subjectDao.getallstudent();
    }

    @Override
    public boolean updateSubject(Subject subject) throws SQLException, ClassNotFoundException {
        return subjectDao.update(subject);
    }

    @Override
    public boolean deleteTeachers(String id) throws SQLException, ClassNotFoundException {
        return subjectDao.delete(id);
    }

    @Override
    public boolean save(Subject subject) throws SQLException, ClassNotFoundException {
        return subjectDao.add(subject);
    }
}

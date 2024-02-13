package lk.ijse.School.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.ExamDao;
import lk.ijse.School.Dao.Custom.Impl.ExamDaoImpl;
import lk.ijse.School.To.Exam;
import lk.ijse.School.service.custom.ExamService;
import org.apache.poi.hssf.record.formula.functions.T;

import java.sql.SQLException;

public class ExamServiceImpl implements ExamService {
    ExamDao examDao = new ExamDaoImpl();

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return examDao.getNewModule();
    }

    @Override
    public ObservableList getallexam() throws SQLException, ClassNotFoundException {
        return examDao.getallexam();
    }

    @Override
    public boolean save(Exam exam) throws SQLException, ClassNotFoundException {
        return examDao.add(exam);
    }

    @Override
    public boolean updateexam(Exam exam) throws SQLException, ClassNotFoundException {
        return examDao.update(exam);
    }

    @Override
    public boolean deleteexam(String id) throws SQLException, ClassNotFoundException {
        return examDao.delete(id);
    }
}

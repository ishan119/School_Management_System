package lk.ijse.School.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.Impl.SectionDaoImpl;
import lk.ijse.School.Dao.Custom.SectionDao;
import lk.ijse.School.To.Section;
import lk.ijse.School.service.custom.SectionService;

import java.sql.SQLException;

public class SectionServiceImpl implements SectionService {
    SectionDao sectionDao = new SectionDaoImpl();

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return sectionDao.getNewModule();
    }

    @Override
    public boolean save(Section section) throws SQLException, ClassNotFoundException {
        return sectionDao.add(section);
    }

    @Override
    public ObservableList<Section> getallsection() throws SQLException, ClassNotFoundException {
        return sectionDao.getallsection();
    }
}

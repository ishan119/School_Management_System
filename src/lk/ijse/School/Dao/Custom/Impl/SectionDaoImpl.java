package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.SectionDao;
import lk.ijse.School.To.Section;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionDaoImpl implements SectionDao {
    @Override
    public boolean add(Section section) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO section VALUES (?, ?)");

        pstm.setString(1, section.getSectionID());
        pstm.setString(2, section.getSectionName());



        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Section obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Section search(String obj) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Section> getallsection() throws SQLException, ClassNotFoundException {
        ObservableList<Section> ob = FXCollections.observableArrayList();
        ResultSet rs = CrudUtil.execute("SELECT * from section");
        while (rs.next()){
            ob.add(new Section(rs.getString(1),rs.getString(2)));
        }
        return ob;
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastSectionID = getLastSectionID();
        if (lastSectionID == null) {
            return "SE-000001";
        } else {
            String[] split = lastSectionID.split("[SE][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newSectionID = String.format("Se-%06d", lastDigits);
            return newSectionID;
        }
    }

    @Override
    public String getLastSectionID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT SectionID from section order by SectionID DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
}

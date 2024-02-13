package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.HostalDao;
import lk.ijse.School.To.Hostal;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HostalDaoImpl implements HostalDao {
    @Override
    public boolean add(Hostal hostal) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO hostal VALUES (?, ?, ?, ?, ?)");

        pstm.setString(1, hostal.getHostalID());
        pstm.setString(2, hostal.getHostalName());
        pstm.setString(3, hostal.getHostalType());
        pstm.setString(4, hostal.getNoOfRoom());
        pstm.setString(5, hostal.getHostalContact());



        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Hostal hostal) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE hostal SET HostalName=? ,HostalType=?, NoOfRoom=?, HostalContact=? WHERE HostalID=?",
                hostal.getHostalName(), hostal.getHostalType(),hostal.getNoOfRoom(),hostal.getHostalContact(), hostal.getHostalID());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From hostal where HostalID='"+id+"'")>0;

    }

    @Override
    public Hostal search(String obj) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList getallhostalID() throws SQLException, ClassNotFoundException {
        ObservableList<Hostal> ob = FXCollections.observableArrayList();
        ResultSet rs = CrudUtil.execute("SELECT * from hostal");
        while (rs.next()){
            ob.add(new Hostal(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return ob;
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastHostalId = getLastHostalID();
        if (lastHostalId == null) {
            return "H-000001";
        } else {
            String[] split = lastHostalId.split("[H][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newHostalID = String.format("H-%06d", lastDigits);
            return newHostalID;
        }
    }

    @Override
    public String getLastHostalID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT HostalID from hostal order by HostalID DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
}

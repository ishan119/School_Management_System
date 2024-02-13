package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.NewUserDao;
import lk.ijse.School.To.NewUser;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewUserDaoImpl implements NewUserDao {
    @Override
    public boolean add(NewUser newUser) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO user VALUES (?, ?, ?,?)");


        pstm.setString(1, newUser.getUserNIC());
        pstm.setString(2, newUser.getUserName());
        pstm.setString(4, newUser.getPassword());
        pstm.setString(3, newUser.getUserType());


        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(NewUser newUser) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE user SET UserName=? , UserPW=?, UserType=?  WHERE UserNIC=?",
                newUser.getUserName(), newUser.getPassword(), newUser.getUserType(), newUser.getUserNIC());
    }

    @Override
    public boolean delete(String text) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From user where UserNIC='" + text + "'") > 0;

    }

    @Override
    public NewUser search(String usrName) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from user where userName = ?",usrName);
        if(rs.next()) {
            return new NewUser(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3));
        }
        return null;
    }

    @Override
    public ObservableList<NewUser> getallnewUser() throws SQLException, ClassNotFoundException {
        ObservableList<NewUser> ob = FXCollections.observableArrayList();
        ResultSet rs = CrudUtil.execute("SELECT * from user");
        while (rs.next()) {
            NewUser obj = new NewUser(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3));

            ob.add(obj);


        }
        return ob;
    }
}

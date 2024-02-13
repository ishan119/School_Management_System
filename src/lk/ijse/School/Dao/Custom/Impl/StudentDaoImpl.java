package lk.ijse.School.Dao.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.School.Dao.Custom.StudentDao;
import lk.ijse.School.To.Student;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO student VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        pstm.setString(1, student.getStudentIndexNumber());
        pstm.setString(2, student.getStudentFirstName());
        pstm.setString(3, student.getStudentLastName());
        pstm.setString(4, student.getGender());
        pstm.setString(5, student.getReligion());
        pstm.setString(6, student.getBirthDay());
        pstm.setString(7, student.getMotherFirstName());
        pstm.setString(8, student.getMotherLastName());
        pstm.setString(9, student.getFatherFirstName());
        pstm.setString(10, student.getFatherLastName());
        pstm.setString(11, student.getFatherOccupation());
        pstm.setString(12, student.getStudentAddress());
        pstm.setString(13, student.getContact());


        return pstm.executeUpdate() > 0;

    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Student SET StudentFirstname=? , StudentLastName=? , Gender=?, Religion=?, Birthday=?, MotherFirstName=?, MotherLastName=?,FatherFirstName= ?, FatherLastName=?, FartherOccupation=?, StudentAddress=?, Contact=? WHERE StudentID=?",
                student.getStudentFirstName(), student.getStudentLastName(), student.getGender(), student.getReligion(),student.getBirthDay(), student.getMotherFirstName(), student.getMotherLastName(), student.getFatherFirstName(), student.getFatherLastName(), student.getFatherOccupation(), student.getStudentAddress(),student.getContact(),student.getStudentIndexNumber());


    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From student where StudentID='"+id+"'")>0;


    }

    @Override
    public Student search(String obj) throws SQLException, ClassNotFoundException {
        throw new RuntimeException("is to implement");
    }

    @Override
    public ObservableList<Student> getallstudent() throws SQLException, ClassNotFoundException {
        ObservableList<Student> ob= FXCollections.observableArrayList();


        ResultSet rs = CrudUtil.execute("SELECT * from Student");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Student obj = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getString(7),rs.getString(6),rs.getString(8),rs.getString(9),
                    rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));
            ob.add(obj);
            System.out.println(obj.toString()+"  : model");


        }
        return ob;
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastStudentId = getLaststudentIndex();
        if (lastStudentId == null) {
            return "S-000001";
        } else {
            String[] split = lastStudentId.split("[S][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newStudentId = String.format("S-%06d", lastDigits);
            return newStudentId;
        }
    }

    @Override
    public String getLaststudentIndex() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT StudentID from student order by StudentID DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList getallStudentIndex() throws SQLException, ClassNotFoundException {
        ObservableList<Student> ob = FXCollections.observableArrayList();
        ResultSet rs = CrudUtil.execute("SELECT * from student");
        while (rs.next()){
            ob.add(new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getString(7),rs.getString(6),rs.getString(8),rs.getString(9),
                    rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13)));
        }
        return ob;
    }

    @Override
    public List<String> getStudentName() throws SQLException, ClassNotFoundException {
        ResultSet rst=
                DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student").executeQuery();
        List<String>Names=new ArrayList<>();
        while (rst.next()){
            Names.add(rst.getString(2));
        }
        return Names;
    }
}

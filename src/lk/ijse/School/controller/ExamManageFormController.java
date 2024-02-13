package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lk.ijse.School.To.*;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.db.DBConnection;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.ExamService;
import lk.ijse.School.service.custom.StudentExamService;
import lk.ijse.School.service.custom.StudentService;
import lk.ijse.School.service.custom.impl.ExamServiceImpl;
import lk.ijse.School.service.custom.impl.StudentExamServiceImpl;
import lk.ijse.School.service.custom.impl.StudentServiceImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamManageFormController {
    public AnchorPane examManageContext;
    public JFXComboBox cmbStudents;
    public JFXComboBox cmbSelectExams;
    public JFXTextField txtMarks;
    public JFXComboBox cmbResultType;
    public JFXDatePicker dateHeld;
    public JFXTextField txtExamNumber;
    public JFXTextField txtExamName;
    public TableView tblExamList;
    public TableColumn colExamNumber;
    public TableColumn colExamName;
    public Label lblStudent;
    public Label lblExam;
    public Hyperlink hypHome;
    public Hyperlink hypLogOut;
    public JFXButton savebtn;
    public JFXButton btnSearch;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    private DBConnection DbConnection;
    private ExamService examService ;
    private StudentExamService studentExamService;
    private StudentService studentService ;

    public void initialize(){

        studentExamService = ServiceFactory.getInstance().getService(ServiceType.STUDENTEXAM);
        examService =  ServiceFactory.getInstance().getService(ServiceType.EXAM);

        studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT);

        cmbResultType.getItems().addAll("A","B","C","S","F");
        setComboBox();
       // getStudentName();
        setNewExamID();






    }

    public void setNewExamID() {
        try {
            txtExamNumber.setText(examService.getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   /* public void getStudentName(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student " +
                "WHERE StudentIndexNumber=?");
        stm.setObject(1,id);

        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            lblStudent.setText(rst.getString(2)+" "+rst.getString(3));
        }
    }*/



    public void markAddOnAction(ActionEvent actionEvent) throws RuntimeException {
        String studentID = ((Student) cmbStudents.getSelectionModel().getSelectedItem()).getStudentIndexNumber();
        String examID =((Exam) cmbSelectExams.getSelectionModel().getSelectedItem()).getExamNumber();
        String marks = txtMarks.getText();
        String resultType = (String) cmbResultType.getValue();
        String datepicker =String.valueOf(dateHeld.getValue());




        StudentExam studentExam= new StudentExam(studentID, examID, marks, resultType, datepicker);
        StudentExam studentExam1 = new StudentExam("","","","","");


        try {
            boolean isAdded = studentExamService.save(studentExam);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Marks Added!").show();
                //setTableUsers();
                setComboBox();

                txtMarks.clear();cmbResultType.getSelectionModel().clearSelection();dateHeld.setValue(null);

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

    }

    public void setComboBox(){
        cmbStudents.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {return ((Student)object).getStudentIndexNumber();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        //exam
        cmbSelectExams.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {return ((Exam)object).getExamNumber();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        try {
            cmbStudents.setItems(studentService.getallStudentIndex());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            cmbSelectExams.setItems(examService.getallexam());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) {
    }

    public void TableClicked(MouseEvent mouseEvent) {
    }

    public void HomeOnAction(ActionEvent actionEvent)  throws IOException {
        Stage stage = (Stage) hypHome.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("Student");
        s1.show();
        s1.centerOnScreen();
    }

    public void LogoutOnAction(ActionEvent actionEvent)  throws IOException {
        Stage stage = (Stage) hypLogOut.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("Student");
        s1.show();
        s1.centerOnScreen();
    }

    public void examSaveOnAction(ActionEvent actionEvent) throws RuntimeException {
        String examNumber = txtExamNumber.getText();

        String examName = txtExamName.getText();





        Exam exam= new Exam(examNumber, examName);
        Exam exam1 = new Exam("","");


        try {
            boolean isAdded = examService.save(exam);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Class Added!").show();
                //setTableUsers();
               // setComboBox();

                txtExamNumber.clear();txtExamName.clear();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

    }

    public void examSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        ResultSet result = CrudUtil.execute("SELECT * FROM exam WHERE ExamNumber=?",txtExamNumber.getText());
        if (result.next()) {

            txtExamNumber.setText(result.getString(1));
            txtExamName.setText(result.getString(2));

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }

    public void examUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String examNumber = txtExamNumber.getText();

        String examName = txtExamName.getText();


        Exam exam=new Exam(examNumber, examName);
        boolean isUpdated= examService.updateexam(exam);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            //setTableUsers();
            txtExamName.clear();

        }
    }

    public void examDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = examService.deleteexam(txtExamNumber.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                // setTableUsers();

                txtExamName.clear();



            } else {
                new Alert(Alert.AlertType.INFORMATION, "Delete Fail").show();
            }
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }
}

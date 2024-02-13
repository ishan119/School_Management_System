package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import lk.ijse.School.Util.AlertMassage;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.To.Student;
import lk.ijse.School.Util.ValidationUtil;
import lk.ijse.School.db.DBConnection;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.StudentService;
import lk.ijse.School.service.custom.impl.StudentServiceImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class StudentManageFormController {
    public AnchorPane StudentManageContext;
    public TableView tblStudent;
    public TableColumn colIndexNumber;
    public TableColumn colName;
    public TableColumn colGender;
    public TableColumn colReligion;
    public TableColumn colbirthDay;
    public TableColumn colAddress;
    public TableColumn colContact;
    public Hyperlink hypHome;
    public Hyperlink hypLogOut;
    public Hyperlink hypRegisterNewStudent;
    public Hyperlink hypStudentDetail;
    public Hyperlink hypStudentexam;
    public Hyperlink hypStudents;
    public AnchorPane studentPane;

    public Label lblDate;
    public TextField txtStudentFirstName;
    public TextField txtStudentLastName;


    public ComboBox cmbClassID;
    public TextField txtMotherFirstName;



    public TextField txtAddress;
    public TextField txtContact;
    public JFXButton savebtn;
    public JFXButton btnSearch;
    public JFXButton btnUpdateStudent;
    public JFXButton btnDeleteStudent;
    public JFXTextField txtstudentIndex;
    public TextField txtMotherLastName;
    public TextField txtFatherFirstName;
    public TextField txtFatherLastName;
    public TextField txtFatherOccupation;
    public TextField txtGender;
    public TextField txtReligion;
    public JFXDatePicker datePicker1;
    public Hyperlink printStudent;
    private JasperCompileManager JasperCompilerManager;

    private StudentService studentService ;

    LinkedHashMap<TextField, Pattern> allValidations = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(S00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/, ]{6,30}$");
    Pattern contactPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");

    public void initialize() throws SQLException, ClassNotFoundException {
        studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT);

        setTableUsers();
        setNewStudentIndex();
        setNewStudentId();
        validateInit();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy:MM:dd");
            lblDate.setText(LocalDateTime.now().format(formatter1));
        }),new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();



    }
    public void setNewStudentId() {
        try {
            //txtstudentIndex.setText(StudentModel.getNewModule());
            txtstudentIndex.setText(studentService  .getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setTableUsers() throws SQLException, ClassNotFoundException {
        colIndexNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("studentIndexNumber"));
        colName.setCellValueFactory(new PropertyValueFactory<Student, String>("studentFirstName" ));
        colGender.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        colReligion.setCellValueFactory(new PropertyValueFactory<Student, String>("religion"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Student, String>("studentAddress"));
        colbirthDay.setCellValueFactory(new PropertyValueFactory<Student, String>("birthDay"));
        colContact.setCellValueFactory(new PropertyValueFactory<Student, String>("contact"));

        try {
            ObservableList<Student> students = studentService.getallstudent();
            tblStudent.setItems(students);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setNewStudentIndex(){
        /*try {
            txtstudentIndex.setText(StudentModel.getallstudent());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }



    public void HomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) hypHome.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("login");
        s1.show();
        s1.centerOnScreen();
    }

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) hypLogOut.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("Student");
        s1.show();
        s1.centerOnScreen();
    }

    

    public void studentSaveOnAction(ActionEvent actionEvent) throws RuntimeException {
        System.out.println("MotherFirstname");
        String studentIndexnumber = txtstudentIndex.getText();
        String studentFirstname = txtStudentFirstName.getText();
        String studentLastname = txtStudentLastName.getText();
        String gender =  txtGender.getText();
        String religion =  txtReligion.getText();
        String birthDay = String.valueOf(datePicker1.getValue());//cmbBirthYear.getValue() + "-" + cmbBirthMonth.getValue() + "-" + cmbBirthDay.getValue();
        String motherFirstname = txtMotherFirstName.getText();
        System.out.println(motherFirstname);
        String motherLastname = txtMotherLastName.getText();
        String fatherFirstname = txtFatherFirstName.getText();
        String fatherLastname = txtFatherLastName.getText();
        String fartherOccupation = txtFatherOccupation.getText();

        String studentAddress = txtAddress.getText();
        String contact = txtContact.getText();


        Student student = new Student(studentIndexnumber, studentFirstname, studentLastname, gender, religion,motherFirstname, birthDay,   motherLastname, fatherFirstname,fatherLastname, fartherOccupation, studentAddress, contact);
        Student student1 = new Student("","","","","","","","" +
                "","","","","","");
        System.out.println(student.toString()+"  : controller");
        try {
            boolean isAdded = studentService.save(student);
            if(isAdded) {
                new AlertMassage().SaveStudent("Success!!");
                setTableUsers();
                setNewStudentId();
                txtStudentFirstName.clear();txtStudentLastName.clear();datePicker1.setValue(null);txtGender.clear();txtReligion.clear();txtMotherLastName.clear();txtMotherFirstName.clear();txtFatherFirstName.clear();txtFatherLastName.clear();txtFatherOccupation.clear();txtAddress.clear();txtContact.clear();cmbClassID.getSelectionModel().clearSelection();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    //Search
    public void studentSearchOnAction(ActionEvent actionEvent){
        try {
            search();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }




    public void studentUpdateOnAction(ActionEvent actionEvent)throws SQLException, ClassNotFoundException {
        String StudentIndexnumber = txtstudentIndex.getText();
        String StudentFirstname = txtStudentFirstName.getText();
        String StudentLastname = txtStudentLastName.getText();
        String Gender =  txtGender.getText();
        String Religion =  txtReligion.getText();
        String BirthDay = String.valueOf(datePicker1.getValue());//cmbBirthYear.getValue() + "-" + cmbBirthMonth.getValue() + "-" + cmbBirthDay.getValue();
        String MotherFirstname = txtMotherFirstName.getText();

        String MotherLastname = txtMotherLastName.getText();
        String FatherFirstname = txtFatherFirstName.getText();
        String FatherLastname = txtFatherLastName.getText();
        String FartherOccupation = txtFatherOccupation.getText();

        String StudentAddress = txtAddress.getText();
        String Contact = txtContact.getText();

        Student student=new Student(StudentIndexnumber, StudentFirstname, StudentLastname, Gender, Religion, BirthDay, MotherFirstname, MotherLastname, FatherFirstname, FatherLastname, FartherOccupation, StudentAddress, Contact);
        boolean isUpdated=studentService.updateStudent(student);
        Student student1=new Student("","","","","","","","","","","","","");
        if(isUpdated){
            new AlertMassage().updateStudent("Success!!");
            setTableUsers();

        }
    }


    public void studentDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = studentService.deleteStudent(txtstudentIndex.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                txtStudentFirstName.clear();txtStudentLastName.clear();datePicker1.setValue(null);txtGender.clear();txtReligion.clear();txtMotherLastName.clear();txtMotherFirstName.clear();txtFatherFirstName.clear();txtFatherLastName.clear();txtFatherOccupation.clear();txtAddress.clear();txtContact.clear();cmbClassID.getSelectionModel().clearSelection();
                new AlertMassage().deleteStudent("Success!!");
                setTableUsers();
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
            }
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }


    public void txtSearxhOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void search() throws ClassNotFoundException, SQLException {
        throw new RuntimeException("Is to implement");
        /*ResultSet result = CrudUtil.execute("SELECT * FROM Student WHERE StudentID=?",txtstudentIndex.getText());
        if (result.next()) {
            txtStudentFirstName.setText(result.getString(2));
            txtStudentLastName.setText(result.getString(3));
            txtGender.setText(result.getString(4));
            txtReligion.setText(result.getString(5));
            datePicker1.setValue(LocalDate.parse(result.getString(6)));
            //cmbBirthYear.setValue(result.getString(6));
            txtMotherFirstName.setText(result.getString(7));
            txtMotherLastName.setText(result.getString(8));
            txtFatherFirstName.setText(result.getString(9));
            txtFatherLastName.setText(result.getString(10));
            txtFatherOccupation.setText(result.getString(11));
            txtAddress.setText(result.getString(12));
            txtContact.setText(result.getString(13));
            new AlertMassage().SearchStudent("Success!!");
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }*/
    }

    public void studentDetailsOnAction(ActionEvent actionEvent) {

    }

    public void printOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasdi= JRXmlLoader.load("C:\\Users\\IES\\Desktop\\School Management System-20230131T045101Z-001\\School Management System\\src\\lk\\ijse\\School\\report\\StudentReport.jrxml");
        String sql="select * from student where studentID = '"+txtstudentIndex.getText()+"'";

        JRDesignQuery newQuery=new JRDesignQuery();
        newQuery.setText(sql);
        jasdi.setQuery(newQuery);

        JasperReport js=JasperCompilerManager.compileReport(jasdi);
        JasperPrint jp= JasperFillManager.fillReport(js,null, DBConnection.getInstance().getConnection());

        //String path =;
        //JasperExportManager.exportReportToPdfFil(jp,path);

        JasperViewer viewer=new JasperViewer(jp,false);
        viewer.show();
        //return path;/

    }

    public void txtContactReleased(KeyEvent keyEvent) {
    }

    public void textKeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(allValidations, savebtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }
    private void validateInit() {
        savebtn.setDisable(true);
        allValidations.put(txtStudentFirstName, namePattern);
        allValidations.put(txtStudentLastName, namePattern);
        allValidations.put(txtMotherFirstName, namePattern);
        allValidations.put(txtMotherLastName, namePattern);
        allValidations.put(txtFatherFirstName, namePattern);
        allValidations.put(txtFatherFirstName, namePattern);
        allValidations.put(txtAddress, addressPattern);
        allValidations.put(txtContact, contactPattern);
    }
}


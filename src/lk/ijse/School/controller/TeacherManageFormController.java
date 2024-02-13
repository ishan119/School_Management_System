package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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

import lk.ijse.School.To.Student;
import lk.ijse.School.To.Teachers;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.Util.ValidationUtil;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.TeachersService;
import lk.ijse.School.service.custom.impl.TeachersServiceImpl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class TeacherManageFormController {
    public AnchorPane TeacherManagementContext;
    public TableView tblTeacher;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colGender;
    public TableColumn colReligion;
    public TableColumn colContact;

    public Hyperlink btnAddNewTeacher;
    public Hyperlink btnTeacher;
    public Hyperlink hypLogOut;
    public Hyperlink hypHome;
    public TextField txtTeacherFirstName;
    public TextField txtTeacherLastName;
    public TextField txtAddress;
    public TextField txtContact;
    public JFXTextField txtTeacherID;
    public TextField txtGender;
    public TextField txtReligion;
    public TableView tblTeacherSubject;
    public TableColumn colSubject;
    public JFXComboBox cmbSubjectID;
    public Label lblSubjectName;
    public Label lblAlreadyAdded;
    public JFXButton savebtn;
    public JFXButton btnSearch;
    public JFXButton btnUpdateStudent;
    public JFXButton btnDeleteStudent;

    private TeachersService teachersService ;

    LinkedHashMap<TextField, Pattern> allValidations = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(S00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/, ]{6,30}$");
    Pattern contactPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");


    public void initialize() throws SQLException, ClassNotFoundException {

        teachersService = ServiceFactory.getInstance().getService(ServiceType.TEACHER);
        setTableUsers();
        setNewTeacherId();
        validateInit();




    }




    public void setNewTeacherId() {
        try {
            txtTeacherID.setText(teachersService.getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setTableUsers() throws SQLException, ClassNotFoundException {
        colID.setCellValueFactory(new PropertyValueFactory<Teachers, String>("teacherID"));
        colName.setCellValueFactory(new PropertyValueFactory<Teachers, String>("teacherName" ));
        colAddress.setCellValueFactory(new PropertyValueFactory<Teachers, String>("address"));
        colGender.setCellValueFactory(new PropertyValueFactory<Teachers, String>("gender"));
        colReligion.setCellValueFactory(new PropertyValueFactory<Teachers, String>("religion"));
        colContact.setCellValueFactory(new PropertyValueFactory<Teachers, String>("contact"));

        try {
            ObservableList<Teachers> teachers = teachersService.getallstudent();
            tblTeacher.setItems(teachers);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) hypLogOut.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("Student");
        s1.show();
        s1.centerOnScreen();
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) hypHome.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("Student");
        s1.show();
        s1.centerOnScreen();
    }

    public void addNewTeacherOnAction(ActionEvent actionEvent) {
    }

    public void TeacherDetailsOnAction(ActionEvent actionEvent) {
    }


    public void textField_key_Released(KeyEvent keyEvent){
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
        allValidations.put(txtTeacherFirstName, namePattern);
        allValidations.put(txtAddress, addressPattern);
        allValidations.put(txtContact, contactPattern);
    }

    public void addSubjectForTeacher(ActionEvent actionEvent) {
    }

    public void teacherSaveOnAction(ActionEvent actionEvent) throws RuntimeException {
        String teacherID = txtTeacherID.getText();
        String teacherFirstname = txtTeacherFirstName.getText();
        String address =  txtAddress.getText();
        String gender =  txtGender.getText();

        String religion =  txtReligion.getText();
        String contact = txtContact.getText();



        Teachers teacher = new Teachers(teacherID, teacherFirstname, address, gender, religion, contact);
        Teachers teacher1 = new Teachers("","","","","","");


        try {
            boolean isAdded = teachersService.save(teacher);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Teacher Added!").show();
                setTableUsers();
                txtTeacherID.clear();txtTeacherFirstName.clear();txtAddress.clear();txtGender.clear();txtReligion.clear();txtContact.clear();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void teacherSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void teacherUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String TeacherID = txtTeacherID.getText();
        String teacherFirstname = txtTeacherFirstName.getText();
        String Address = txtAddress.getText();
        String Gender =  txtGender.getText();
        String Religion =  txtReligion.getText();
        String Contact = txtContact.getText();

        Teachers teachers=new Teachers(TeacherID,teacherFirstname,Address, Gender, Religion, Contact);
        boolean isUpdated= teachersService.updateTeachers(teachers);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableUsers();

        }
    }


    public void teacherDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = teachersService.deleteTeachers(txtTeacherID.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                setTableUsers();
                txtTeacherFirstName.clear();
                txtAddress.clear();
                txtGender.clear();
                txtReligion.clear();
                txtContact.clear();

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Delete Fail").show();
            }
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }

    private void search() throws ClassNotFoundException, SQLException {

        ResultSet result = CrudUtil.execute("SELECT * FROM Teachers WHERE TeacherID=?",txtTeacherID.getText());
        if (result.next()) {
            txtTeacherFirstName.setText(result.getString(2));
            txtAddress.setText(result.getString(3));
            txtGender.setText(result.getString(4));
            txtReligion.setText(result.getString(5));
            txtContact.setText(result.getString(6));
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }
}

package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.School.To.Subject;
import lk.ijse.School.Util.AlertMassage;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.SubjectService;
import lk.ijse.School.service.custom.impl.SubjectServiceImpl;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectManageFormController {
    public AnchorPane SubjectManageContext;
    public TextField txtSubjectID;
    public TextField txtSubjectName;
    
    public Button btnSave;
    public TableView tblSubject;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colMeadium;
    public Button btncancel;
    public Button btnEditAndDelete;
    public Hyperlink hypHome;
    public Hyperlink hypLogOut;
    public JFXButton savebtn;
    public JFXButton btnSearch;
    public JFXButton btnUpdateStudent;
    public JFXButton btnDeleteStudent;
    public ComboBox cmbMedium;
    private SubjectService subjectService ;

    public void initialize() throws SQLException, ClassNotFoundException {
        subjectService = ServiceFactory.getInstance().getService(ServiceType.SUBJECT);

        cmbMedium.getItems().addAll("Sinhala","English");
        setNewSubjectId();
        setTableUsers();


    }

    public void setNewSubjectId() {
        try {
            txtSubjectID.setText(subjectService.getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setTableUsers() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<Subject, String>("SubjectID"));
        colName.setCellValueFactory(new PropertyValueFactory<Subject, String>("SubjectName" ));
        colMeadium.setCellValueFactory(new PropertyValueFactory<Subject, String>("Medium"));


        try {
            ObservableList<Subject> subject = subjectService.getallstudent();
            tblSubject.setItems(subject);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Key_Released(KeyEvent keyEvent) {
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


    public void subjectSaveOnAction(ActionEvent actionEvent) throws RuntimeException {
        String subjectID = txtSubjectID.getText();
        String subjectName = txtSubjectName.getText();
        String medium = (String) cmbMedium.getValue();




        Subject subject = new Subject(subjectID, subjectName, medium);
        Subject subject1 = new Subject("","","");


        try {
            boolean isAdded = subjectService.save(subject);
            if(isAdded) {
                new AlertMassage().SaveMessage("Saved");
                setNewSubjectId();
                setTableUsers();
                txtSubjectID.clear();txtSubjectName.clear();cmbMedium.getSelectionModel().clearSelection();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void subjectSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void search() throws ClassNotFoundException, SQLException {

        ResultSet result = CrudUtil.execute("SELECT * FROM subject WHERE SubjectID=?",txtSubjectID.getText());
        if (result.next()) {
            txtSubjectName.setText(result.getString(2));
            cmbMedium.setValue(result.getString(3));
            new AlertMassage().searchSubject("Success!!");

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }

    public void subjectUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String SubjectID = txtSubjectID.getText();
        String SubjectName = txtSubjectName.getText();
        String Medium = (String) cmbMedium.getValue();

        Subject subject=new Subject(SubjectID,SubjectName,Medium);
        boolean isUpdated= subjectService.updateSubject(subject);
        if(isUpdated){
            new AlertMassage().UpadateSubjectMessage("Success!!");
            setTableUsers();

        }
    }

    public void subjectDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = subjectService.deleteTeachers(txtSubjectID.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                setTableUsers();
                txtSubjectName.clear();
                txtSubjectName.clear();
                cmbMedium.getSelectionModel().clearSelection();
                new AlertMassage().deleteSubject("Success!!");


            } else {
                new Alert(Alert.AlertType.INFORMATION, "Delete Fail").show();
            }
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }
}

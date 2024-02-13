package lk.ijse.School.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentDetailsInClassesFormController {
    public TableView tblStudent;
    public TableColumn colIndexNumber;
    public TableColumn colSName;
    public TableColumn colDate;
    public TableView tblTeacher;
    public TableColumn colID;
    public TableColumn colTName;
    public JFXComboBox cmbClass;
    public Label lblClassName;
    public Hyperlink hypHome;
    public Hyperlink hypLogOut;

    public void HomeOnAction(ActionEvent actionEvent) {
    }

    public void LogoutOnAction(ActionEvent actionEvent) {
    }
}

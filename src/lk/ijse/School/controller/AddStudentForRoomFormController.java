package lk.ijse.School.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

import lk.ijse.School.To.*;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.StudentRoomService;
import lk.ijse.School.service.custom.StudentService;
import lk.ijse.School.service.custom.impl.StudentRoomServiceImpl;
import lk.ijse.School.service.custom.impl.StudentServiceImpl;

import java.sql.SQLException;

public class AddStudentForRoomFormController {
    public JFXComboBox cmbStudent;
    public JFXComboBox cmbRoom;
    public TableView tblStudentnRoom;
    public TableColumn colIndexNumber;
    public TableColumn colStudentName;
    public TableColumn colRoomID;
    public TableColumn colHostalID;
    public TableColumn colEntryDate;
    public JFXDatePicker datePicker;

    private StudentRoomService studentRoomService ;
    private StudentService studentService ;

    public void initialize(){
        studentRoomService = ServiceFactory.getInstance().getService(ServiceType.STUDENTROOM);
        studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT);
        setComboBox();

    }

    public void addOnAction(ActionEvent actionEvent) throws RuntimeException {
        String studentID = ((Student) cmbStudent.getSelectionModel().getSelectedItem()).getStudentIndexNumber();
        String roomID =((Room) cmbRoom.getSelectionModel().getSelectedItem()).getRoomID();
        String dateOfEntry =String.valueOf(datePicker.getValue());





        StudentRoom studentRoom= new StudentRoom(studentID, roomID, dateOfEntry);
        StudentRoom studentRoom1 = new StudentRoom("","","");


        try {
            boolean isAdded = studentRoomService.save(studentRoom);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Marks Added!").show();
                //setTableUsers();
                setComboBox();

                datePicker.setValue(null);

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

    }
    public void setComboBox(){
        cmbStudent.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {return ((Student)object).getStudentIndexNumber();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        //exam
        cmbRoom.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {return ((Room)object).getRoomID();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        try {
            cmbStudent.setItems(studentService.getallStudentIndex());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            cmbRoom.setItems(studentRoomService.getallRoom());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }
}

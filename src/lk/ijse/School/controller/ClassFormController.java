package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;



import lk.ijse.School.To.Class;
import lk.ijse.School.To.Section;

import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.ClassService;
import lk.ijse.School.service.custom.SectionService;
import lk.ijse.School.service.custom.impl.ClassServiceImpl;
import lk.ijse.School.service.custom.impl.SectionServiceImpl;


import java.io.IOException;
import java.sql.SQLException;

public class ClassFormController {
    public AnchorPane ClassManazgeContext;
    public TextField txtClassName;
    public TextField txtDetails;
    public JFXComboBox cmbSectionID;
    public Label lblClassID;
    public JFXButton btnClassSave;
    public JFXButton btnSave;
    public TextField txtClasssID;

    private ClassService classService ;
    private SectionService sectionService ;


    public void initialize(){

        classService = ServiceFactory.getInstance().getService(ServiceType.CLASS);
        sectionService = ServiceFactory.getInstance().getService(ServiceType.SECTION);

        setComboBox();
        setNewClassID();
    }

    public void setNewClassID() {
        try {
            txtClasssID.setText(classService.getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void textfield_keyReleased(KeyEvent keyEvent) {
    }

    public void SectionAddContextOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage  = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AddSectionForm.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(ClassManazgeContext.getScene().getWindow());
        stage.showAndWait();
        setComboBox();
    }

    public void cancelOnAction(ActionEvent actionEvent) {
    }

    public void setComboBox(){
        cmbSectionID.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                return ((Section)object).getSectionID();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        try {
            cmbSectionID.setItems(sectionService.getallsection());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveClassOnAction(ActionEvent actionEvent) throws RuntimeException {
        String classID = txtClasssID.getText();
        String sectionID =((Section) cmbSectionID.getSelectionModel().getSelectedItem()).getSectionID();
        String className = txtClassName.getText();
        String details =  txtDetails.getText();




        Class class1 = new Class(classID, className, details,sectionID);
        Class class2 = new Class("","","","");


        try {
            boolean isAdded = classService.save(class1);
            if(isAdded) {

                new Alert(Alert.AlertType.CONFIRMATION, "Class Added!").show();
                //setTableUsers();
                txtClasssID.clear();txtClassName.clear();txtDetails.clear();


            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

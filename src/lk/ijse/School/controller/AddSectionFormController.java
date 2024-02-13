package lk.ijse.School.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import lk.ijse.School.To.Section;
;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.SectionService;
import lk.ijse.School.service.custom.impl.SectionServiceImpl;

import java.sql.SQLException;

public class AddSectionFormController {
    public AnchorPane addSectionContext;
    public TextField txtSectionID;
    public TextField txtSectionName;
    public Button btnSectionSave;

    private SectionService sectionService;

    public void initialize(){
        sectionService = ServiceFactory.getInstance().getService(ServiceType.SECTION);
        setNewSectionID();

    }

    public void setNewSectionID() {
        try {
            txtSectionID.setText(sectionService.getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Sectiontextfield_keyReleased(KeyEvent keyEvent) {
    }

    public void addSectionOnAction(ActionEvent actionEvent) throws RuntimeException {
        String sectionID = txtSectionID.getText();
        String sectionName = txtSectionName.getText();


        Section section = new Section(sectionID, sectionName);
        Section section1 = new Section("","");


        try {
            boolean isAdded = sectionService.save(section);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Teacher Added!").show();
                //setTableUsers();
                txtSectionID.clear();txtSectionName.clear();;

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCancelSectionOnAction(ActionEvent actionEvent) {
    }
}

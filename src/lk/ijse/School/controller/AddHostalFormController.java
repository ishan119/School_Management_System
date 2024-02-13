package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;





import lk.ijse.School.To.Hostal;
import lk.ijse.School.To.Room;
import lk.ijse.School.To.Section;
import lk.ijse.School.To.Subject;
import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.HostalService;
import lk.ijse.School.service.custom.impl.HostalServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddHostalFormController {
    public AnchorPane AddClassandContext;
    public TextField txtRoomID;

    public TextField txtRoomDetails;
    public JFXComboBox cmbNoOdBed;
    public TextField txtHostalID;
    public JFXComboBox cmbHostalType;
    public TextField txtHostalName;
    public JFXComboBox cmbNoOfRoom;
    public TextField txtHostalContact;
    public JFXButton btnSaveRoom;
    public JFXButton btnSearchRoom;
    public JFXButton btnUpdateRoom;
    public JFXButton btnDeletRoom;
    public JFXButton btnSaveHostal;
    public JFXButton btnSearchHostal;
    public JFXButton btnUpadateHostal;
    public JFXButton btnDeleteHostal;
    public JFXComboBox cmbHostalID;
    private HostalService hostalService;

    public void initialize(){
        hostalService = ServiceFactory.getInstance().getService(ServiceType.HOSTAL);
        cmbHostalType.getItems().addAll("Girls","Boys");
        cmbNoOfRoom.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
       // cmbNoOdBed.getItems().addAll("1","2","3","4","5","6","7","8","9","10");

        //setComboBox();
        setNewHostalID();
    }

    public void setNewHostalID() {
        try {
            txtHostalID.setText(hostalService.getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Room_KeyRelesed(KeyEvent keyEvent) {
    }

    public void hostalKeyRelese(KeyEvent keyEvent) {
    }

    /*public void roomSaveOnAction(ActionEvent actionEvent) throws RuntimeException {
        String roomID = txtRoomID.getText();
        String hostalID =((Hostal) cmbHostalID.getSelectionModel().getSelectedItem()).getHostalID();
        String roomDetails = txtRoomDetails.getText();
        String noOfBeds = (String) cmbNoOdBed.getValue();




        Room room= new Room(roomID, hostalID, roomDetails, noOfBeds);
        Room room1 = new Room("","","","");


        try {
            boolean isAdded = roomModel.save(room);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Class Added!").show();
                //setTableUsers();
                setComboBox();
                txtRoomID.clear();txtRoomDetails.clear();cmbNoOdBed.getSelectionModel().clearSelection();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

    }*/

    /*public void setComboBox(){
        cmbHostalID.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {return ((Hostal)object).getHostalID();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        try {
            cmbHostalID.setItems(hostalModel.getallhostalID());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void roomSearchOnAction(ActionEvent actionEvent) {
    }

    public void roomUpdateOnAction(ActionEvent actionEvent) {
    }

    public void roomDeleteOnAction(ActionEvent actionEvent) {
    }*/

    public void hostalSaveOnAction(ActionEvent actionEvent) throws RuntimeException {
        String hostalID = txtHostalID.getText();
        String hostalName = txtHostalName.getText();
        String hostalType = (String) cmbHostalType.getValue();
        String noOfRoom = (String) cmbNoOfRoom.getValue();
        String hostalContact = txtHostalContact.getText();



        Hostal hostal = new Hostal(hostalID, hostalName, hostalType, noOfRoom, hostalContact);
        //Hostal hostal1 = new Hostal("","","","","");


        try {
            boolean isAdded = hostalService.save(hostal);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Hostal Added!").show();
                //setTableUsers();

                txtHostalID.clear();txtHostalName.clear();cmbHostalType.getSelectionModel().clearSelection();cmbNoOfRoom.getSelectionModel().clearSelection();txtHostalContact.clear();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void hostalSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        ResultSet result = CrudUtil.execute("SELECT * FROM hostal WHERE HostalID=?",txtHostalID.getText());
        if (result.next()) {
            txtHostalName.setText(result.getString(2));
            cmbHostalType.setValue(result.getString(3));
            cmbNoOfRoom.setValue(result.getString(4));
            txtHostalContact.setText(result.getString(5));

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }

    public void hostalUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String HostalID = txtHostalID.getText();
        String HostalName = txtHostalName.getText();
        String HostalType = (String) cmbHostalType.getValue();
        String NoOfRoom = (String) cmbNoOfRoom.getValue();
        String HostalContact = txtHostalContact.getText();

        Hostal hostal=new Hostal(HostalID, HostalName, HostalType, NoOfRoom, HostalContact);
        boolean isUpdated= hostalService.updateHostal(hostal);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            //setTableUsers();

        }
    }

    public void hostalDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = hostalService.deletehostal(txtHostalID.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
               // setTableUsers();
                txtHostalName.clear();
                cmbHostalType.getSelectionModel().clearSelection();
                cmbNoOfRoom.getSelectionModel().clearSelection();
                txtHostalContact.clear();


            } else {
                new Alert(Alert.AlertType.INFORMATION, "Delete Fail").show();
            }
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }

    public void backToOnAction(ActionEvent actionEvent) {
    }
}

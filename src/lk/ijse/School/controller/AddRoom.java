package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import lk.ijse.School.To.Hostal;
import lk.ijse.School.To.Room;

import lk.ijse.School.Util.CrudUtil;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.HostalService;
import lk.ijse.School.service.custom.RoomService;
import lk.ijse.School.service.custom.impl.HostalServiceImpl;
import lk.ijse.School.service.custom.impl.RoomServiceImpl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddRoom {
    public TextField txtRoomID;
    public JFXComboBox cmbHostalID;
    public TextField txtRoomDetails;
    public JFXComboBox cmbNoOdBed;
    public JFXButton btnSaveRoom;
    public JFXButton btnSearchRoom;
    public JFXButton btnUpdateRoom;
    public JFXButton btnDeletRoom;
    public AnchorPane addroomcontext;

    private RoomService roomService ;
    private HostalService hostalService ;

    public void initialize(){
        roomService = ServiceFactory.getInstance().getService(ServiceType.ROOM);
        hostalService = ServiceFactory.getInstance().getService(ServiceType.HOSTAL);


        cmbNoOdBed.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        setComboBox();
        setNewClassID();
    }

    public void setNewClassID() {
        try {
            txtRoomID.setText(roomService.getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void Room_KeyRelesed(KeyEvent keyEvent) {
    }

    public void roomSaveOnAction(ActionEvent actionEvent) throws RuntimeException {
        String roomID = txtRoomID.getText();
        String hostalID =((Hostal) cmbHostalID.getSelectionModel().getSelectedItem()).getHostalID();
        String roomDetails = txtRoomDetails.getText();
        String noOfBeds = (String) cmbNoOdBed.getValue();




        Room room= new Room(roomID, hostalID, roomDetails, noOfBeds);
        Room room1 = new Room("","","","");


        try {
            boolean isAdded = roomService.save(room);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Class Added!").show();
                setComboBox();
                setNewClassID();

                txtRoomID.clear();txtRoomDetails.clear();cmbNoOdBed.getSelectionModel().clearSelection();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

    }

    public void setComboBox(){
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
            cmbHostalID.setItems(hostalService.getallhostalID());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void roomSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        ResultSet result = CrudUtil.execute("SELECT * FROM room WHERE RoomID=?",txtRoomID.getText());
        if (result.next()) {

            cmbHostalID.setValue(result.getString(2));
            txtRoomDetails.setText(result.getString(3));
            cmbNoOdBed.setValue(result.getString(4));

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }

    public void roomUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String RoomID = txtRoomID.getText();
        String HostalID = ((Hostal) cmbHostalID.getSelectionModel().getSelectedItem()).getHostalID();
        String RoomDetails = txtRoomDetails.getText();
        String NoOfBeds= (String) cmbNoOdBed.getValue();


        Room room=new Room(RoomID, HostalID, RoomDetails, NoOfBeds);
        boolean isUpdated= roomService.updateroom(room);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            //setTableUsers();
            cmbHostalID.getSelectionModel().clearSelection();
            txtRoomDetails.clear();
            cmbNoOdBed.getSelectionModel().clearSelection();

        }
    }

    public void roomDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = roomService.deleteroom(txtRoomID.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                // setTableUsers();
                cmbHostalID.getSelectionModel().clearSelection();
                txtRoomDetails.clear();
                cmbNoOdBed.getSelectionModel().clearSelection();


            } else {
                new Alert(Alert.AlertType.INFORMATION, "Delete Fail").show();
            }
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }

    public void addHostalOnAtion(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AddHostal.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addroomcontext.getScene().getWindow());
        stage.setTitle("Add Hostal");
        stage.showAndWait();
        setComboBox();

    }
}

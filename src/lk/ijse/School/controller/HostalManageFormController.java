package lk.ijse.School.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lk.ijse.School.To.Room;
import lk.ijse.School.To.Subject;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.HostalService;
import lk.ijse.School.service.custom.RoomService;
import lk.ijse.School.service.custom.impl.HostalServiceImpl;
import lk.ijse.School.service.custom.impl.RoomServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class HostalManageFormController {
    public AnchorPane HostalManageContext;
    public TableView tblRooms;
    public TableColumn colHostalID;
    public TableColumn colRoomID;
    public TableColumn colRoomDetails;
    public TableColumn colNoOfBed;
    public Hyperlink hypLogOut;
    public Hyperlink hypHome;

    private HostalService hostalService ;
    private RoomService roomService ;

    public void initialize() throws SQLException, ClassNotFoundException {
        hostalService = ServiceFactory.getInstance().getService(ServiceType.HOSTAL);
        roomService = ServiceFactory.getInstance().getService(ServiceType.ROOM);
        setTableUsers();

    }

    public void setTableUsers() throws SQLException, ClassNotFoundException {
        colHostalID.setCellValueFactory(new PropertyValueFactory<Subject, String>("HostalID"));
        colRoomID.setCellValueFactory(new PropertyValueFactory<Subject, String>("RoomID" ));
        colRoomDetails.setCellValueFactory(new PropertyValueFactory<Subject, String>("RoomDetails"));
        colNoOfBed.setCellValueFactory(new PropertyValueFactory<Subject, String>("NoOfBeds"));


        try {
            ObservableList<Room> rooms = roomService.getallrooms();
            tblRooms.setItems(rooms);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addRoomandHostalOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddRoom.fxml"));
        HostalManageContext.getChildren().clear();
        HostalManageContext.getChildren().add(parent);

    }

    public void addStudentForRoomOnAction(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddStudentForRoomForm.fxml"));
        HostalManageContext.getChildren().clear();
        HostalManageContext.getChildren().add(parent);

}

    public void editandDeleteHostalAndRoomOnAction(ActionEvent actionEvent) {
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
}

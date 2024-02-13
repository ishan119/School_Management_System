package lk.ijse.School.Util;



import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case Main:
                window.setTitle("Main Window");
                initUI("MainForm.fxml");
                break;
            case Login:
                window.setTitle("Login Page");
                initUI("LoginForm.fxml");
                break;
            case DASHBOARD:
                window.setTitle("Dashboard");
                initUI("DashboardForm.fxml");
                break;
            case Student:
                window.setTitle("Student");
                initUI("StudentManageForm.fxml");
                break;

            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/School/view/" + location)));
    }
}

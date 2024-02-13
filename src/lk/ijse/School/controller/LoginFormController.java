package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import lk.ijse.School.To.NewUser;
import lk.ijse.School.Util.AlertMassage;
import lk.ijse.School.Util.Navigation;
import lk.ijse.School.Util.Routes;
import lk.ijse.School.db.DBConnection;
import lk.ijse.School.service.ServiceFactory;
import lk.ijse.School.service.ServiceType;
import lk.ijse.School.service.custom.NewUserService;
import lk.ijse.School.service.custom.impl.NewUserServiceImpl;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public AnchorPane Pane;
    public JFXButton btnLogin;
    public AnchorPane LoginFormContext;
    public Label wrongLbl;
    private DBConnection DbConnection;

    public static String UserName;
    public static String userType;

    private NewUserService newUserService;

    public void  initialize(){
        newUserService = ServiceFactory.getInstance().getService(ServiceType.NEWUSER);

    }


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (actionEvent.getSource()==btnLogin) {
            if (txtUserName.getText().isEmpty() && txtPassword.getText().isEmpty()) {

                new AlertMassage().errorMassage("Please Insert UserName & Password");
            }
            UserName = txtUserName.getText();
            NewUser newUser = newUserService.searchUser(UserName);
            if(newUser==null){
                new AlertMassage().errorMassage("Password or User Name Incorrect");
                return;
            }
            String password = txtPassword.getText();
            if (password.equals(newUser.getPassword())) {
                System.out.println("login success");
                Stage window = (Stage)LoginFormContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
                new AlertMassage().successMassage("Login Success!!..");

            } else  {
                new AlertMassage().errorMassage("Password or User Name Incorrect");
            }

        }
    }
}
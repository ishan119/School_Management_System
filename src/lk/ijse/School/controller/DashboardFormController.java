package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import lk.ijse.School.Util.Navigation;
import lk.ijse.School.Util.Routes;
import lk.ijse.School.db.DBConnection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class DashboardFormController implements Initializable {
    public AnchorPane DashboardFullScreenContext;
    public AnchorPane DashboardMainWindowContext;
    public Button btnStudent;
    public Button btnTeacher;
    public Button btnClass;
    public Button btnSubject;
    public Button btnHostal;
    public Button btnSport;
    public Button btnExam;
    public Button btnHome;
    public Button btnAddnewUser;
    public Button btnLogout;
    public Label lblClass;
    public Label lblStudent;
    public Label lblTeacher;
    public Label lblSport;
    public Label lblDate;
    public Label lblTime;
    public Label lblUserName1;

    public JFXButton btnPlay;
    public JFXButton btnPause;
    public MediaView mv;
    public AnchorPane mediaanchor;
    public AnchorPane mainPane;

    private MediaPlayer mediaplayer;
    private File file;
    private Media media;
    private Object IssueModel;
    private DBConnection DbConnection;

    int male;
    int female;
    int Tmale;
    int Tfemale;


    public void initialize(URL arg0,ResourceBundle arg1){

        file = new File( "E:\\git\\School Management System\\src\\lk\\ijse\\School\\assets\\intro.mp4");
        media = new Media(file.toURI().toString());

        mediaplayer = new MediaPlayer(media);
        mv.setMediaPlayer(mediaplayer);


        mediaplayer.setAutoPlay(true);
        try {
            studentCount();
            TeacherCount();
            //SportCount();
            ClassCount();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    {
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy:MM:dd");
                lblTime.setText(LocalDateTime.now().format(formatter));
                lblDate.setText(LocalDateTime.now().format(formatter1));
            }),new KeyFrame(Duration.seconds(1)));
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();
        }
    }

    public void studentCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student");
        ResultSet rst=stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblStudent.setText(String.valueOf(a));
            if(rst.getString(4).equals("Male")){
                male++;
            }else{female++;}
        }
    }

    public void TeacherCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Teachers");
        ResultSet rst=stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblTeacher.setText(String.valueOf(a));
            if(rst.getString(4).equals("Male")){
                Tmale++;
            }else{Tfemale++;}
        }
    }

    public void ClassCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Class");
        ResultSet rst=stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblClass.setText(String.valueOf(a));

        }
    }



    public void StudentManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/StudentManageForm.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(parent);

    }

    public void TeacherManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/TeacherManageForm.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(parent);

    }

    public void SectionManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ClassForm.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(parent);

    }

    public void SubjectManageOnAction(ActionEvent actionEvent)  throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SubjectManageForm.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(parent);

    }
    public void HostalManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/HostalManageForm.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(parent);
    }

    public void examOnAction(ActionEvent actionEvent)throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ExamManageForm.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(parent);

    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("Student");
        s1.show();
        s1.centerOnScreen();

    }

    public void addNewUserOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnAddnewUser.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/NewUserForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("Student");
        s1.show();
        s1.centerOnScreen();
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("Student");
        s1.show();
        s1.centerOnScreen();
    }



    public void playOnAction(ActionEvent actionEvent) {
    }

    public void pauseOnAction(ActionEvent actionEvent) {
    }
}

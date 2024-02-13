package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.School.Util.Navigation;
import lk.ijse.School.Util.Routes;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainFormController implements Initializable {
    public JFXButton btnStart;
    public AnchorPane mainPane;

    private StackPane rootPane;



   /* String path = "E:\\git\\School Management System\\src\\lk\\ijse\\School\\assets";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaplayer = new MediaPlayer(media)*/;


    /*public void initialize(URL url, ResourceBundle rb){
        mediaplayer.setAutoPlay(true);
        makeFadeIn();

    }
*/
    private void makeFadeIn(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }





    public void Start(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) btnStart.getScene().getWindow();
        stage.close();

        Parent parent  = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        Stage s1 = new Stage();
        s1.setScene(new Scene(parent));
        s1.setTitle("login");
        s1.show();
        s1.centerOnScreen();



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

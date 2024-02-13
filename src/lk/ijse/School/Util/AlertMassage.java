package lk.ijse.School.Util;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class AlertMassage {
    public void successMassage(String text) {
        //Image image = new Image(("/Assets/succecs.png"));
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(text);
        notifications.title("Confirmation Massage");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(3));
        notifications.position(Pos.TOP_CENTER);
        notifications.show();
    }

    public void errorMassage(String text) {
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(text);
        notifications.title("Login Faild!!");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(2));
        notifications.position(Pos.TOP_CENTER);
        notifications.show();
    }

    public void SaveMessage(String text) {
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(text);
        notifications.title("Saved!!");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(2));
        notifications.position(Pos.CENTER);
        notifications.show();
    }
    public void UpadateSubjectMessage(String text) {
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(text);
        notifications.title("Update Suject");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(2));
        notifications.position(Pos.CENTER);
        notifications.show();
    }

    public void searchSubject(String s) {
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(s);
        notifications.title("Search Suject");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(2));
        notifications.position(Pos.CENTER);
        notifications.show();
    }

    public void deleteStudent(String s) {
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(s);
        notifications.title("Delete Student");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(2));
        notifications.position(Pos.CENTER);
        notifications.show();
    }


    public void deleteSubject(String s) {
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(s);
        notifications.title("Delete Suject");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(2));
        notifications.position(Pos.CENTER);
        notifications.show();
    }

    public void SaveStudent(String s) {
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(s);
        notifications.title("Save Student");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(2));
        notifications.position(Pos.CENTER);
        notifications.show();
    }
    public void SearchStudent(String s) {
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(s);
        notifications.title("Search Student");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(2));
        notifications.position(Pos.CENTER);
        notifications.show();
    }
    public void updateStudent(String s) {
        Notifications notifications = Notifications.create();
        //notifications.graphic(new ImageView(image));
        notifications.text(s);
        notifications.title("Update Student");
        notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(2));
        notifications.position(Pos.CENTER);
        notifications.show();
    }
}

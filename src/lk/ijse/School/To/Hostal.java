package lk.ijse.School.To;

public class Hostal {

    private String hostalID;
    private String hostalName;
    private String hostalType;
    private String noOfRoom;
    private String hostalContact;

    public Hostal(String hostalID, String hostalName, String hostalType, String noOfRoom, String hostalContact) {
        this.hostalID = hostalID;
        this.hostalName = hostalName;
        this.hostalType = hostalType;
        this.noOfRoom = noOfRoom;
        this.hostalContact = hostalContact;
    }

    public String getHostalID() {
        return hostalID;
    }

    public void setHostalID(String hostalID) {
        this.hostalID = hostalID;
    }

    public String getHostalName() {
        return hostalName;
    }

    public void setHostalName(String hostalName) {
        this.hostalName = hostalName;
    }

    public String getHostalType() {
        return hostalType;
    }

    public void setHostalType(String hostalType) {
        this.hostalType = hostalType;
    }

    public String getNoOfRoom() {
        return noOfRoom;
    }

    public void setNoOfRoom(String noOfRoom) {
        this.noOfRoom = noOfRoom;
    }

    public String getHostalContact() {
        return hostalContact;
    }

    public void setHostalContact(String hostalContact) {
        this.hostalContact = hostalContact;
    }
}

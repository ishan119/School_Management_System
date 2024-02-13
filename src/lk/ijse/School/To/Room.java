package lk.ijse.School.To;

public class Room {
    private String roomID;
    private String hostalID;
    private String roomDetails;
    private String noOfBeds;

    public Room(String roomID, String hostalID, String roomDetails, String noOfBeds) {
        this.roomID = roomID;
        this.hostalID = hostalID;
        this.roomDetails = roomDetails;
        this.noOfBeds = noOfBeds;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getHostalID() {
        return hostalID;
    }

    public void setHostalID(String hostalID) {
        this.hostalID = hostalID;
    }

    public String getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(String roomDetails) {
        this.roomDetails = roomDetails;
    }

    public String getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(String noOfBeds) {
        this.noOfBeds = noOfBeds;
    }
}

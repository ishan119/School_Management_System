package lk.ijse.School.To;

public class StudentRoom {
    private String studentID;
    private String roomID;
    private String dateOfEntry;

    public StudentRoom(String studentID, String roomID, String dateOfEntry) {
        this.studentID = studentID;
        this.roomID = roomID;
        this.dateOfEntry = dateOfEntry;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }
}

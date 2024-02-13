package lk.ijse.School.To;

public class Teachers {
    private String teacherID;
    private String teacherName;
    private String address;
    private String gender;
    private String religion;
    private String contact;

    public Teachers(String teacherID, String teacherFirstName, String address, String gender, String religion, String contact) {
        this.teacherID = teacherID;
        this.teacherName = teacherFirstName;
        this.address = address;
        this.gender = gender;
        this.religion = religion;
        this.contact = contact;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherFirstName) {
        this.teacherName = teacherFirstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
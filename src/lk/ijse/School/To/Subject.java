package lk.ijse.School.To;

public class Subject {
    private String subjectID;
    private String subjectName;
    private String medium;

    public Subject(String subjectID, String subjectName, String medium) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.medium = medium;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}

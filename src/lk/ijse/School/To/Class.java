package lk.ijse.School.To;

public class Class {
    private String className;
    private String details;
    private String classId;
    private String sectionID;

    public Class(String className, String details, String classId, String sectionID) {
        this.className = className;
        this.details = details;
        this.classId = classId;
        this.sectionID = sectionID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }
}



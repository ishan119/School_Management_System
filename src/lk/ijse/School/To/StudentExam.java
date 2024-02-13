package lk.ijse.School.To;

public class StudentExam {
    private String studentID;
    private String examNumber;
    private String marks;
    private  String resultType;
    private String dateHeld;

    public StudentExam(String studentID, String examNumber, String marks, String resultType, String dateHeld) {
        this.studentID = studentID;
        this.examNumber = examNumber;
        this.marks = marks;
        this.resultType = resultType;
        this.dateHeld = dateHeld;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(String examNumber) {
        this.examNumber = examNumber;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getDateHeld() {
        return dateHeld;
    }

    public void setDateHeld(String dateHeld) {
        this.dateHeld = dateHeld;
    }
}

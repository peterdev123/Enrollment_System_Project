import java.time.LocalDate;

// Student Class
public class Student extends Person {
    private int studentId;
    private String course;
    private boolean isDismiss;
    private boolean isGraduate;
    private boolean isDelete;
    private boolean approveDelete;
    private boolean approveDismiss;
    private boolean approveGraduate;
    private boolean approveChangeCourse;
    private String changeCourse;
    private boolean undoDelete;
    private boolean undoGraduate;
    private boolean undoDismiss;

    // Constructor
    public Student(int studentId, String firstName, String lastName, String middleName, String email, String contact, 
                    LocalDate birthdate, String course) {
        super(firstName, lastName, middleName, email, contact, birthdate);
        this.studentId = studentId;
        this.course = course;
        this.isDismiss = false;
        this.isGraduate = false;
        this.isDelete = false;
        this.approveDelete = false;
        this.approveGraduate = false;
        this.approveDismiss = false;
        this.undoDelete = false;
        this.undoGraduate = false;
        this.undoDismiss = false;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public boolean isDismiss() {
        return isDismiss;
    }

    public void setDismiss(boolean dismiss) {
        isDismiss = dismiss;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public void setGraduate(boolean graduate) {
        isGraduate = graduate;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isApproveDelete() {
        return approveDelete;
    }

    public void setApproveDelete(boolean approveDelete) {
        this.approveDelete = approveDelete;
    }

    public boolean isApproveGraduate() {
        return approveGraduate;
    }

    public void setApproveGraduate(boolean approveGraduate) {
        this.approveGraduate = approveGraduate;
    }

    public boolean isApproveDismiss() {
        return approveDismiss;
    }

    public void setApproveDismiss(boolean approveDismiss) {
        this.approveDismiss = approveDismiss;
    }

    public boolean isApproveChangeCourse() {
        return approveChangeCourse;
    }

    public void setApproveChangeCourse(boolean approveChangeCourse) {
        this.approveChangeCourse = approveChangeCourse;
    }

    public String getChangeCourse() {
        return changeCourse;
    }

    public void setChangeCourse(String changeCourse) {
        this.changeCourse = changeCourse;
    }

    public boolean isUndoDelete() {
        return undoDelete;
    }

    public void setUndoDelete(boolean undoDelete) {
        this.undoDelete = undoDelete;
    }

    public boolean isUndoGraduate() {
        return undoGraduate;
    }

    public void setUndoGraduate(boolean undoGraduate) {
        this.undoGraduate = undoGraduate;
    }

    public boolean isUndoDismiss() {
        return undoDismiss;
    }

    public void setUndoDismiss(boolean undoDismiss) {
        this.undoDismiss = undoDismiss;
    }
}

public class RegistrationAction {
    Course course;
    Student student;
    boolean wasWaitlisted;

    public RegistrationAction(Course course, Student student, boolean wasWaitlisted) {
        this.course = course;
        this.student = student;
        this.wasWaitlisted = wasWaitlisted;
    }
}

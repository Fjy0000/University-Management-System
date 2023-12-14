package entity;

/**
 *
 * @author Fong Jun Yi
 */
public class StudentCourse implements Comparable<StudentCourse> {

    private String courseId;
    private String course;
    private String status;

    public StudentCourse() {
    }

    public StudentCourse(String id, String course) {
        this.courseId = id;
        this.course = course;
    }

    public StudentCourse(String id, String course, String status) {
        this.courseId = id;
        this.course = course;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(StudentCourse object) {
        return this.courseId.compareTo(object.courseId);
    }

    @Override
    public String toString() {
        return courseId + "\t" + course + "\t" + status;
    }
}

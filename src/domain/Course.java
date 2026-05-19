package domain;

public class Course {

    private String courseCode;

    private String courseName;

    private int credits;

    // Constructor
    public Course(
            String courseCode,
            String courseName,
            int credits
    ) {

        this.courseCode = courseCode;

        this.courseName = courseName;

        this.credits = credits;
    }

    // Getters and setters
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
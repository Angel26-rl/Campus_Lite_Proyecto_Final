package domain;

public class Student {

    private String studentId;

    private String name;

    private String email;

    private String career;

    // Constructor
    public Student(
            String studentId,
            String name,
            String email,
            String career
    ) {

        this.studentId = studentId;

        this.name = name;

        this.email = email;

        this.career = career;
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}

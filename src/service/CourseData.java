package service;

import java.util.ArrayList;
import domain.Course;

public class CourseData {

    public static ArrayList<Course> courses = new ArrayList<>();

    public static boolean addCourse(Course course) {
        if (course == null) {
            return false;
        }

        if (existsCourseCode(course.getCourseCode())) {
            return false;
        }

        courses.add(course);
        return true;
    }

    public static boolean existsCourseCode(String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }
}

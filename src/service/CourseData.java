package service;

import java.util.ArrayList;
import domain.Course;

public class CourseData {

    public static ArrayList<Course> courses =
            new ArrayList<>();

    public static boolean addCourse(Course course) {

        if (course == null) {
            return false;
        }

        if (
                existsCourse(
                        course.getCourseCode(),
                        course.getCourseName()
                )
        ) {
            return false;
        }

        courses.add(course);

        return true;
    }

    public static boolean existsCourse(
            String code,
            String name
    ) {

        for (Course c : courses) {

            if (
                    c.getCourseCode().equalsIgnoreCase(code)
                    &&
                    c.getCourseName().equalsIgnoreCase(name)
            ) {
                return true;
            }
        }

        return false;
    }
}
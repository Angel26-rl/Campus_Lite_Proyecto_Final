package persistence;

import domain.Course;

import service.CourseData;

import java.io.*;

public class CourseFileManager {

    private static final String FILE_NAME =
            "courses.csv";

    // Save courses to CSV
    public static void saveCourses() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME)
                    );

            for (Course course :
                    CourseData.courses) {

                writer.write(

                        course.getCourseCode() + "," +

                        course.getCourseName() + "," +

                        course.getCredits()
                );

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // Load courses from CSV
    public static void loadCourses() {

        try {

            File file = new File(FILE_NAME);

            // Create file if it does not exist
            if (!file.exists()) {

                file.createNewFile();

                return;
            }

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FILE_NAME)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 3) {

                    Course course = new Course(

                            data[0],

                            data[1],

                            Integer.parseInt(data[2])
                    );

                    CourseData.courses.add(course);
                }
            }

            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
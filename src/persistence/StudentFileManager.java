package persistence;

import domain.Student;
import service.StudentData;
import java.io.*;
import persistence.StudentFileManager;


public class StudentFileManager {

    private static final String FILE_NAME =
            "students.csv";

    // Save students to CSV
    public static void saveStudents() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME)
                    );

            for (Student student :
                    StudentData.students) {

                writer.write(

                        student.getStudentId() + "," +

                        student.getName() + "," +

                        student.getEmail() + "," +

                        student.getCareer()
                );

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // Load students from CSV
    public static void loadStudents() {

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

                if (data.length == 4) {

                    Student student = new Student(

                            data[0],

                            data[1],

                            data[2],

                            data[3]
                    );

                    StudentData.students.add(student);
                    StudentFileManager.saveStudents();
                }
            }

            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

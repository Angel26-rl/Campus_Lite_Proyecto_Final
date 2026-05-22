package ui;

import javax.swing.*;
import java.awt.*;
import domain.Course;
import service.CourseData;
import persistence.CourseFileManager;

public class AddCourseFrame extends JFrame {

    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtCredits;
    private JTextField txtSchedule;

    public AddCourseFrame() {
        setTitle("Agregar curso");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        panel.add(new JLabel("Codigo del curso:"));
        txtCode = new JTextField();
        panel.add(txtCode);

        panel.add(new JLabel("Nombre del curso:"));
        txtName = new JTextField();
        panel.add(txtName);
        panel.add(new JLabel("Creditos:"));
        txtCredits = new JTextField();
        panel.add(txtCredits);
        panel.add(new JLabel("Horario:"));
        txtSchedule = new JTextField();
        panel.add(txtSchedule);

        JButton btnSave = new JButton("Guardar");
        JButton btnCancel = new JButton("Cancelar");

        panel.add(btnSave);
        panel.add(btnCancel);

        add(panel);

        btnSave.addActionListener(e -> saveCourse());
        btnCancel.addActionListener(e -> dispose());
    }

    private void saveCourse() {
        String code = txtCode.getText().trim();
        String name = txtName.getText().trim();
        String creditsText =
                txtCredits.getText().trim();
        String schedule =
                txtSchedule.getText().trim();

        if (
                code.isEmpty() ||
                name.isEmpty() ||
                creditsText.isEmpty() ||
                schedule.isEmpty()
                
        ) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.");
            return;
        }

        int credits;

        try {

            credits =
                    Integer.parseInt(creditsText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Credits must be a number."
            );

            return;
        }

        Course course =
                new Course(
                        code,
                        name,
                        credits,
                        schedule
                );

        if (CourseData.addCourse(course)) {

            CourseFileManager.saveCourses();

            JOptionPane.showMessageDialog(
                    this,
                    "Curso guardado correctamente."
            );

            txtCode.setText("");
            txtName.setText("");
            txtCredits.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Ya existe un curso con ese codigo."
            );
        }
    }
}
package ui;

import javax.swing.*;
import java.awt.*;
import domain.Course;
import service.CourseData;

public class AddCourseFrame extends JFrame {

    private JTextField txtCode;
    private JTextField txtName;

    public AddCourseFrame() {
        setTitle("Agregar curso");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        panel.add(new JLabel("Codigo del curso:"));
        txtCode = new JTextField();
        panel.add(txtCode);

        panel.add(new JLabel("Nombre del curso:"));
        txtName = new JTextField();
        panel.add(txtName);

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

        if (code.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.");
            return;
        }

        Course course = new Course(code, name);

        if (CourseData.addCourse(course)) {
            JOptionPane.showMessageDialog(this, "Curso guardado correctamente.");
            txtCode.setText("");
            txtName.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Ya existe un curso con ese codigo.");
        }
    }
}
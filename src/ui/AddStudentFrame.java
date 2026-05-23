package ui;

import domain.Student;
import persistence.StudentFileManager;
import service.StudentData;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class AddStudentFrame extends JFrame {

    public AddStudentFrame() {

        setTitle("Agregar Estudiante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon background =
                        new ImageIcon(getClass().getResource("/images/add_student.png"));

                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        mainPanel.setLayout(null);

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField careerField = new JTextField();

        styleField(idField);
        styleField(nameField);
        styleField(emailField);
        styleField(careerField);

        JButton saveButton = createInvisibleButton();
        JButton backButton = createInvisibleButton();

        saveButton.addActionListener(e -> {

            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String career = careerField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || email.isEmpty() || career.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.");
                return;
            }

            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

            if (!Pattern.matches(emailRegex, email)) {
                JOptionPane.showMessageDialog(this, "Formato de correo invalido.");
                return;
            }

            for (Student existingStudent : StudentData.students) {
                if (existingStudent.getStudentId().equalsIgnoreCase(id)) {
                    JOptionPane.showMessageDialog(this, "El ID del estudiante ya existe.");
                    return;
                }
            }

            Student student = new Student(id, name, email, career);

            StudentData.students.add(student);
            StudentFileManager.saveStudents();

            JOptionPane.showMessageDialog(this, "Estudiante guardado correctamente.");

            idField.setText("");
            nameField.setText("");
            emailField.setText("");
            careerField.setText("");
        });

        backButton.addActionListener(e -> dispose());

        mainPanel.add(idField);
        mainPanel.add(nameField);
        mainPanel.add(emailField);
        mainPanel.add(careerField);
        mainPanel.add(saveButton);
        mainPanel.add(backButton);

        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {

                int w = mainPanel.getWidth();
                int h = mainPanel.getHeight();

                idField.setBounds((int)(w * 0.34), (int)(h * 0.37), (int)(w * 0.48), 55);
                nameField.setBounds((int)(w * 0.34), (int)(h * 0.47), (int)(w * 0.48), 55);
                emailField.setBounds((int)(w * 0.34), (int)(h * 0.57), (int)(w * 0.48), 55);
                careerField.setBounds((int)(w * 0.34), (int)(h * 0.67), (int)(w * 0.48), 55);

                saveButton.setBounds((int)(w * 0.35), (int)(h * 0.77), (int)(w * 0.30), 70);
                backButton.setBounds((int)(w * 0.01), (int)(h * 0.90), (int)(w * 0.14), 70);
            }
        });

        add(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        field.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    private JButton createInvisibleButton() {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
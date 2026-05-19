package ui;

import javax.swing.*;
import java.awt.*;
import domain.Student;
import service.StudentData;
import java.util.regex.Pattern;
import persistence.StudentFileManager;

public class AddStudentFrame extends JFrame {

    public AddStudentFrame() {

        // Window configuration
        setTitle("Add Student");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon background =
                        new ImageIcon("src/images/borderthree.jpg");

                g.drawImage(
                        background.getImage(),
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        this
                );
            }
        };

        mainPanel.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Add Student");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Labels
        JLabel idLabel = new JLabel("Student ID:");

        JLabel nameLabel = new JLabel("Name:");

        JLabel emailLabel = new JLabel("Email:");

        JLabel careerLabel = new JLabel("Career:");

        // Text fields
        JTextField idField = new JTextField();

        JTextField nameField = new JTextField();

        JTextField emailField = new JTextField();

        JTextField careerField = new JTextField();

        // Buttons
        JButton saveButton = new JButton("Save");

        JButton backButton = new JButton("Back");

        // Back action
        backButton.addActionListener(e -> {

            dispose();

        });

        // Save action
        saveButton.addActionListener(e -> {

            String id = idField.getText().trim();

            String name = nameField.getText().trim();

            String email = emailField.getText().trim();

            String career = careerField.getText().trim();

            // Empty fields validation
            if (
                    id.isEmpty() ||
                    name.isEmpty() ||
                    email.isEmpty() ||
                    career.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please complete all fields."
                );

                return;
            }

            // Email validation
            String emailRegex =
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

            if (!Pattern.matches(emailRegex, email)) {

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid email format."
                );

                return;
            }

            // Duplicate ID validation
            for (Student existingStudent :
                    StudentData.students) {

                if (
                        existingStudent
                                .getStudentId()
                                .equalsIgnoreCase(id)
                ) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Student ID already exists."
                    );

                    return;
                }
            }

            // Create student object
            Student student = new Student(
                    id,
                    name,
                    email,
                    career
            );

            // Save student
            StudentData.students.add(student);

            StudentFileManager.saveStudents();

            JOptionPane.showMessageDialog(
                    null,
                    "Student saved successfully!"
            );

            // Clear fields
            idField.setText("");

            nameField.setText("");

            emailField.setText("");

            careerField.setText("");
        });

        // Add components
        mainPanel.add(titleLabel);

        mainPanel.add(idLabel);
        mainPanel.add(nameLabel);
        mainPanel.add(emailLabel);
        mainPanel.add(careerLabel);

        mainPanel.add(idField);
        mainPanel.add(nameField);
        mainPanel.add(emailField);
        mainPanel.add(careerField);

        mainPanel.add(saveButton);
        mainPanel.add(backButton);

        // Responsive positions
        mainPanel.addComponentListener(
                new java.awt.event.ComponentAdapter() {

            @Override
            public void componentResized(
                    java.awt.event.ComponentEvent e) {

                int panelWidth = mainPanel.getWidth();

                int centerX = panelWidth / 2;

                // Title
                titleLabel.setBounds(
                        centerX - 250,
                        80,
                        500,
                        50
                );

                // Labels
                idLabel.setBounds(centerX - 220, 180, 100, 30);

                nameLabel.setBounds(centerX - 220, 240, 100, 30);

                emailLabel.setBounds(centerX - 220, 300, 100, 30);

                careerLabel.setBounds(centerX - 220, 360, 100, 30);

                // Fields
                idField.setBounds(centerX - 100, 180, 250, 30);

                nameField.setBounds(centerX - 100, 240, 250, 30);

                emailField.setBounds(centerX - 100, 300, 250, 30);

                careerField.setBounds(centerX - 100, 360, 250, 30);

                // Buttons
                saveButton.setBounds(centerX - 70, 460, 140, 40);

                backButton.setBounds(40, mainPanel.getHeight() - 100,
                        100, 40);
            }
        });

        // Add panel
        add(mainPanel);

        // Open maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }
}


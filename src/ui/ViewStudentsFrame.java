package ui;

import domain.Student;

import service.StudentData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class ViewStudentsFrame extends JFrame {

    public ViewStudentsFrame() {

        // Window configuration
        setTitle("View Students");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel with background
        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon background =
                        new ImageIcon("src/images/bordertwo.jpg");

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

        mainPanel.setLayout(new BorderLayout());

        // Table columns
        String[] columns = {
                "Student ID",
                "Name",
                "Email",
                "Career"
        };

        // Table model
        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        // Load students into table
        for (Student student : StudentData.students) {

            Object[] row = {

                    student.getStudentId(),

                    student.getName(),

                    student.getEmail(),

                    student.getCareer()
            };

            model.addRow(row);
        }

        // Table
        JTable table = new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(table);

        // Buttons
        JButton deleteButton =
                new JButton("Delete Student");

        JButton backButton =
                new JButton("Back");

        // Delete action
        deleteButton.addActionListener(e -> {

            int selectedRow =
                    table.getSelectedRow();

            if (selectedRow != -1) {

                StudentData.students.remove(selectedRow);

                model.removeRow(selectedRow);

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Select a student first."
                );
            }
        });

        // Back action
        backButton.addActionListener(e -> {

            dispose();

        });

        // Bottom panel
        JPanel bottomPanel = new JPanel();

        bottomPanel.add(deleteButton);

        bottomPanel.add(backButton);

        // Title
        JLabel titleLabel =
                new JLabel(
                        "Students List",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        // Add components
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add panel
        add(mainPanel);

        // Open maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }
}


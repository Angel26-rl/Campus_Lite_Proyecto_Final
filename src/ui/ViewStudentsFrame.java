package ui;

import domain.Student;
import service.StudentData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import persistence.StudentFileManager;

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
        JButton editButton =
                new JButton("Edit Student");

        JButton deleteButton =
                new JButton("Delete Student");

        JButton backButton =
                new JButton("Back");
      
        
     // Edit action
        editButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a student first."
                );

                return;
            }

            Student student =
                    StudentData.students.get(selectedRow);

            String newName =
                    JOptionPane.showInputDialog(
                            this,
                            "New Name:",
                            student.getName()
                    );

            if (newName == null) return;

            String newEmail =
                    JOptionPane.showInputDialog(
                            this,
                            "New Email:",
                            student.getEmail()
                    );

            if (newEmail == null) return;

            String newCareer =
                    JOptionPane.showInputDialog(
                            this,
                            "New Career:",
                            student.getCareer()
                    );

            if (newCareer == null) return;

            student.setName(newName.trim());
            student.setEmail(newEmail.trim());
            student.setCareer(newCareer.trim());

            model.setValueAt(newName.trim(), selectedRow, 1);
            model.setValueAt(newEmail.trim(), selectedRow, 2);
            model.setValueAt(newCareer.trim(), selectedRow, 3);

            StudentFileManager.saveStudents();

            JOptionPane.showMessageDialog(
                    this,
                    "Student updated successfully."
            );
        });
        
        
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

        bottomPanel.add(editButton);

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






package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import service.CourseData;
import domain.Course;
import persistence.CourseFileManager;

public class ViewCoursesFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewCoursesFrame() {

        setTitle("Course List");

        setSize(700, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("Code");

        model.addColumn("Name");

        model.addColumn("Credits");

        table = new JTable(model);

        loadCourses();

        JScrollPane scrollPane =
                new JScrollPane(table);

        JButton editButton =
                new JButton("Edit Course");

        JButton deleteButton =
                new JButton("Delete Course");

        JButton backButton =
                new JButton("Back");

        // EDIT
        editButton.addActionListener(e -> {

            int selectedRow =
                    table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a course first."
                );

                return;
            }

            Course course =
                    CourseData.courses.get(selectedRow);

            String newName =
                    JOptionPane.showInputDialog(
                            this,
                            "New Course Name:",
                            course.getCourseName()
                    );

            if (newName == null) return;

            String newCredits =
                    JOptionPane.showInputDialog(
                            this,
                            "New Credits:",
                            course.getCredits()
                    );

            if (newCredits == null) return;

            int credits;

            try {

                credits =
                        Integer.parseInt(
                                newCredits.trim()
                        );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Credits must be a number."
                );

                return;
            }

            course.setCourseName(
                    newName.trim()
            );

            course.setCredits(
                    credits
            );

            model.setValueAt(
                    newName.trim(),
                    selectedRow,
                    1
            );

            model.setValueAt(
                    credits,
                    selectedRow,
                    2
            );

            CourseFileManager.saveCourses();

            JOptionPane.showMessageDialog(
                    this,
                    "Course updated successfully."
            );
        });

        // DELETE
        deleteButton.addActionListener(e -> {

            int selectedRow =
                    table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a course first."
                );

                return;
            }

            CourseData.courses.remove(selectedRow);

            model.removeRow(selectedRow);

            CourseFileManager.saveCourses();

            JOptionPane.showMessageDialog(
                    this,
                    "Course deleted successfully."
            );
        });

        // BACK
        backButton.addActionListener(e -> dispose());

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.add(editButton);

        bottomPanel.add(deleteButton);

        bottomPanel.add(backButton);

        add(scrollPane, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadCourses() {

        model.setRowCount(0);

        for (Course c : CourseData.courses) {

            model.addRow(new Object[] {

                    c.getCourseCode(),

                    c.getCourseName(),

                    c.getCredits()
            });
        }
    }
}

package ui;

import domain.Course;
import persistence.CourseFileManager;
import service.CourseData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewCoursesFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewCoursesFrame() {

        setTitle("Ver Cursos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon background =
                        new ImageIcon(getClass().getResource("/images/view_courses.png"));

                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        mainPanel.setLayout(null);

        String[] columns = {
                "Codigo",
                "Nombre",
                "Creditos",
                "Horario"
        };

        model = new DefaultTableModel(columns, 0);

        loadCourses();

        table = new JTable(model);
        table.setRowHeight(50);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(table);

        JButton editButton = createInvisibleButton();
        JButton deleteButton = createInvisibleButton();
        JButton backButton = createInvisibleButton();

        editButton.addActionListener(e -> editCourse());
        deleteButton.addActionListener(e -> deleteCourse());
        backButton.addActionListener(e -> dispose());

        mainPanel.add(scrollPane);
        mainPanel.add(editButton);
        mainPanel.add(deleteButton);
        mainPanel.add(backButton);

        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {

                int w = mainPanel.getWidth();
                int h = mainPanel.getHeight();

                scrollPane.setBounds(
                        (int) (w * 0.22),
                        (int) (h * 0.34),
                        (int) (w * 0.70),
                        (int) (h * 0.46)
                );

                editButton.setBounds(
                        (int) (w * 0.28),
                        (int) (h * 0.85),
                        260,
                        70
                );

                deleteButton.setBounds(
                        (int) (w * 0.45),
                        (int) (h * 0.85),
                        280,
                        70
                );

                backButton.setBounds(
                        (int) (w * 0.64),
                        (int) (h * 0.85),
                        250,
                        70
                );
            }
        });

        add(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void loadCourses() {

        model.setRowCount(0);

        for (Course c : CourseData.courses) {
            model.addRow(new Object[] {
                    c.getCourseCode(),
                    c.getCourseName(),
                    c.getCredits(),
                    c.getSchedule()
            });
        }
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

    private void editCourse() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso primero.");
            return;
        }

        Course course = CourseData.courses.get(selectedRow);

        String newName = JOptionPane.showInputDialog(this, "Nuevo nombre:", course.getCourseName());
        if (newName == null) return;

        String newCreditsText = JOptionPane.showInputDialog(this, "Nuevos creditos:", course.getCredits());
        if (newCreditsText == null) return;

        String newSchedule = JOptionPane.showInputDialog(this, "Nuevo horario:", course.getSchedule());
        if (newSchedule == null) return;

        int credits;

        try {
            credits = Integer.parseInt(newCreditsText.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los creditos deben ser un numero.");
            return;
        }

        course.setCourseName(newName.trim());
        course.setCredits(credits);
        course.setSchedule(newSchedule.trim());

        model.setValueAt(newName.trim(), selectedRow, 1);
        model.setValueAt(credits, selectedRow, 2);
        model.setValueAt(newSchedule.trim(), selectedRow, 3);

        CourseFileManager.saveCourses();

        JOptionPane.showMessageDialog(this, "Curso actualizado correctamente.");
    }

    private void deleteCourse() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso primero.");
            return;
        }

        CourseData.courses.remove(selectedRow);
        model.removeRow(selectedRow);

        CourseFileManager.saveCourses();

        JOptionPane.showMessageDialog(this, "Curso eliminado correctamente.");
    }
}
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import service.CourseData;
import domain.Course;

public class ViewCoursesFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewCoursesFrame() {
        setTitle("Listado de cursos");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("Nombre");

        table = new JTable(model);
        loadCourses();

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadCourses() {
        model.setRowCount(0);

        for (Course c : CourseData.courses) {
            model.addRow(new Object[] {
                    c.getCourseCode(),
                    c.getCourseName()
            });
        }
    }
}
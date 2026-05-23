package ui;

import javax.swing.*;

import java.awt.*;

public class ReportsMenuFrame extends JFrame {

    private JButton btnStudentReport;

    private JButton btnCourseReport;

    private JButton btnDashboard;

    public ReportsMenuFrame() {

        setTitle("Menú de Reportes");

        setSize(400, 300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());

        // =========================
        // BOTONES
        // =========================

        btnStudentReport =
                new JButton("Student Reports");

        btnCourseReport =
                new JButton("Course Reports");

        btnDashboard =
                new JButton("Dashboard");

        // =========================
        // AGREGAR BOTONES
        // =========================

        add(btnStudentReport);

        add(btnCourseReport);

        add(btnDashboard);

        // =========================
        // EVENTOS
        // =========================

        btnStudentReport.addActionListener(e -> {

            new ReportStudentsFrame();
        });

        btnCourseReport.addActionListener(e -> {

            new ReportCoursesFrame();
        });

        btnDashboard.addActionListener(e -> {

            new DashboardFrame();
        });

        setVisible(true);
    }
}

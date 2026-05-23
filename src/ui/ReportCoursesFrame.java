package ui;

import javax.swing.*;

public class ReportCoursesFrame extends JFrame {

    public ReportCoursesFrame() {

        setTitle("Course Reports");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label =
                new JLabel("Reporte de Cursos");

        add(label);

        setVisible(true);
    }
}

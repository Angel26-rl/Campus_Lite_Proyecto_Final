
package ui;

import javax.swing.*;

public class ReportStudentsFrame extends JFrame {

    public ReportStudentsFrame() {

        setTitle("Student Reports");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label =
                new JLabel("Reporte de Estudiantes");

        add(label);

        setVisible(true);
    }
}

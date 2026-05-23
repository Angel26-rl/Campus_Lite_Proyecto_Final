
package ui;

import javax.swing.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Dashboard");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label =
                new JLabel("Dashboard General");

        add(label);

        setVisible(true);
    }
}
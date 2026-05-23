package ui;

import javax.swing.*;
import java.awt.*;

public class CourseFrame extends JFrame {

    public CourseFrame() {

        setTitle("Gestion de Cursos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon background =
                        new ImageIcon(getClass().getResource("/images/courses_dashboard.png"));

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

        JButton addCourseButton = createInvisibleButton();
        JButton viewCoursesButton = createInvisibleButton();
        JButton backButton = createInvisibleButton();

        addCourseButton.addActionListener(e -> {
            new AddCourseFrame().setVisible(true);
        });

        viewCoursesButton.addActionListener(e -> {
            new ViewCoursesFrame().setVisible(true);
        });

        backButton.addActionListener(e -> {
            dispose();
        });

        mainPanel.add(addCourseButton);
        mainPanel.add(viewCoursesButton);
        mainPanel.add(backButton);

        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {

            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {

                int w = mainPanel.getWidth();
                int h = mainPanel.getHeight();

                addCourseButton.setBounds(
                        (int) (w * 0.33),
                        (int) (h * 0.42),
                        (int) (w * 0.23),
                        (int) (h * 0.37)
                );

                viewCoursesButton.setBounds(
                        (int) (w * 0.59),
                        (int) (h * 0.42),
                        (int) (w * 0.23),
                        (int) (h * 0.37)
                );

                backButton.setBounds(
                        (int) (w * 0.20),
                        (int) (h * 0.86),
                        (int) (w * 0.15),
                        (int) (h * 0.08)
                );
            }
        });

        add(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
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
}
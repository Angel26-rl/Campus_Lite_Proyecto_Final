package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Campus Lite");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                ImageIcon background =
                        new ImageIcon("src/images/dashboard.png");

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

        // BOTONES INVISIBLES
        JButton studentsButton =
                createInvisibleButton();

        JButton coursesButton =
                createInvisibleButton();

        JButton reportsButton =
                createInvisibleButton();

        // FUNCIONES BOTONES
        studentsButton.addActionListener(e -> {

            new StudentFrame();

        });

        coursesButton.addActionListener(e -> {

            new CourseFrame();

        });

        reportsButton.addActionListener(e -> {

            new ReportsMenuFrame();

        });

        // AGREGAR BOTONES
        mainPanel.add(studentsButton);

        mainPanel.add(coursesButton);

        mainPanel.add(reportsButton);

        // POSICIONES
        mainPanel.addComponentListener(
                new java.awt.event.ComponentAdapter() {

                    @Override
                    public void componentResized(
                            java.awt.event.ComponentEvent e
                    ) {

                        int w =
                                mainPanel.getWidth();

                        int h =
                                mainPanel.getHeight();

                        // ESTUDIANTES
                        studentsButton.setBounds(
                                w / 2 - 560,
                                h / 2 - 10,
                                300,
                                320
                        );

                        // CURSOS
                        coursesButton.setBounds(
                                w / 2 - 150,
                                h / 2 - 10,
                                300,
                                320
                        );

                        // REPORTES
                        reportsButton.setBounds(
                                w / 2 + 260,
                                h / 2 - 10,
                                300,
                                320
                        );
                    }
                }
        );

        add(mainPanel);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }

    // BOTON INVISIBLE
    private JButton createInvisibleButton() {

        JButton button =
                new JButton();

        button.setOpaque(false);

        button.setContentAreaFilled(false);

        button.setBorderPainted(false);

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }
}
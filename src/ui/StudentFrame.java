package ui;

import javax.swing.*;
import java.awt.*;

public class StudentFrame extends JFrame {

    public StudentFrame() {

        // Window configuration
        setTitle("Students Management");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel with dynamic background
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

        mainPanel.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Students Module");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Buttons                                             
                                                                    // Add function to the add student button in students
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> {

            new AddStudentFrame();

        });

        JButton viewButton = new JButton("View Students");
        viewButton.addActionListener(e -> {

            new ViewStudentsFrame();

        });

        JButton backButton = new JButton("Back");

        // Back button action
        backButton.addActionListener(e -> {

            dispose();

        });

        // Add components
        mainPanel.add(titleLabel);

        mainPanel.add(addButton);

        mainPanel.add(viewButton);

        mainPanel.add(backButton);

        // Dynamic responsive positions
        mainPanel.addComponentListener(
                new java.awt.event.ComponentAdapter() {

            @Override
            public void componentResized(
                    java.awt.event.ComponentEvent e) {

                int panelWidth = mainPanel.getWidth();

                int panelHeight = mainPanel.getHeight();

                // Title
                titleLabel.setBounds(
                        (panelWidth / 2) - 250,
                        100,
                        500,
                        50
                );

                // Add button
                addButton.setBounds(
                        (panelWidth / 2) - 140,
                        250,
                        130,
                        45
                );

                // View button
                viewButton.setBounds(
                        (panelWidth / 2) + 10,
                        250,
                        150,
                        45
                );

                // Back button
                backButton.setBounds(
                        40,
                        panelHeight - 100,
                        100,
                        40
                );
            }
        });

        // Add panel
        add(mainPanel);

        // Open maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
        
    }
}

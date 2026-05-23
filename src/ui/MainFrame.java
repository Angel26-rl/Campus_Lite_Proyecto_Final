package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public interface btnReport {

		static void addActionListener(Object object) {
			// TODO Auto-generated method stub
			
		}

	}

	private Component btnReport;

	public MainFrame() {

        // Window configuration
        setTitle("Campus Lite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon background = new ImageIcon("src/images/border.jpg");

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
        JLabel titleLabel = new JLabel("Campus Lite");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Buttons
                                                                                   // Add function to the students button
        JButton studentsButton = new JButton("Students");
        studentsButton.addActionListener(e -> {

            new StudentFrame();

        });
        
        
        JButton coursesButton = new JButton("Courses");
        
        coursesButton.addActionListener(e -> {

            String[] options = {
                "Agregar curso",
                "Ver cursos"
            };

            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opcion",
                    "Cursos",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                new AddCourseFrame().setVisible(true);
            }

            if (choice == 1) {
                new ViewCoursesFrame().setVisible(true);
            }
        });
        
        
        JButton btnReports = new JButton("Reports");
        btnReports.addActionListener(e -> {

            new ReportsMenuFrame();
        });
      
        // Add components
        mainPanel.add(titleLabel);
        mainPanel.add(studentsButton);
        mainPanel.add(coursesButton);
        mainPanel.add(btnReports);

        // Dynamic positioning
        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {

            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {

                int panelWidth = mainPanel.getWidth();

                // Title centered
                titleLabel.setBounds(
                        (panelWidth / 2) - 200,
                        120,
                        400,
                        50
                );

                // Buttons centered
                studentsButton.setBounds(
                        (panelWidth / 2) - 200,
                        240,
                        120,
                        40
                );

                coursesButton.setBounds(
                        (panelWidth / 2) - 60,
                        240,
                        120,
                        40
                );

                btnReports.setBounds(
                        (panelWidth / 2) + 80,
                        240,
                        120,
                        40
                );
            }
        });

        // Add panel
        add(mainPanel);

        // Fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }
}

// Solución archivo






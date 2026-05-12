package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        // Configuración ventana
        setTitle("Campus Lite");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Campus Lite", JLabel.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        // Panel botones
        JPanel buttonPanel = new JPanel();

        JButton studentsButton = new JButton("Students");
        JButton coursesButton = new JButton("Courses");
        JButton reportsButton = new JButton("Reports");

        buttonPanel.add(studentsButton);
        buttonPanel.add(coursesButton);
        buttonPanel.add(reportsButton);

        // Agregar componentes
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Agregar panel al frame
        add(mainPanel);
    }
}
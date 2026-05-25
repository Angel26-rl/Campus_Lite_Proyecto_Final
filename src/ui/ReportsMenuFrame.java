package ui;

import javax.swing.*;

import java.awt.*;

public class ReportsMenuFrame extends JFrame {

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

        btnDashboard =
                new JButton("Dashboard");

        // =========================
        // AGREGAR BOTONES
        // =========================


        add(btnDashboard);

        // =========================
        // EVENTOS
        // =========================

      btnDashboard.addActionListener(e -> {
        	
        	

            new DashboardFrame();
        });

        setVisible(true);
    }
}

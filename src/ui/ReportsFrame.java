package ui;

import domain.Course;
import domain.Student;
import service.CourseData;
import service.StudentData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class ReportsFrame extends JFrame {

    private JLabel totalStudentsLabel;
    private JLabel totalCoursesLabel;
    private JLabel totalCreditsLabel;
    private JLabel lastUpdateLabel;

    private JTable reportTable;
    private DefaultTableModel model;

    private JTable recentStudentsTable;
    private DefaultTableModel recentModel;

    private JComboBox<String> reportSelector;

    private JScrollPane mainScrollPane;
    private JScrollPane recentScrollPane;

    public ReportsFrame() {

        setTitle("Reportes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                ImageIcon background =
                        new ImageIcon(
                                getClass().getResource(
                                        "/images/reports_dashboard.png"
                                )
                        );

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

        // LABELS
        totalStudentsLabel = createStatLabel();
        totalCoursesLabel = createStatLabel();
        totalCreditsLabel = createStatLabel();
        lastUpdateLabel = createStatLabel();

        // SELECTOR
        reportSelector = new JComboBox<>(new String[] {
                "Estudiantes por carrera",
                "Cursos por carrera",
                "Listado de estudiantes",
                "Listado de cursos",
                "Curso con más créditos"
        });

        reportSelector.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        reportSelector.addActionListener(
                e -> loadReportData()
        );

        // TABLA PRINCIPAL
        model = new DefaultTableModel();

        reportTable = new JTable(model);

        reportTable.setRowHeight(35);

        reportTable.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        reportTable.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        reportTable.setDefaultRenderer(
                Object.class,
                centerRenderer
        );

        ((DefaultTableCellRenderer)
                reportTable.getTableHeader()
                        .getDefaultRenderer())
                .setHorizontalAlignment(
                        SwingConstants.CENTER
                );

        mainScrollPane =
                new JScrollPane(reportTable);

        // TABLA RECIENTES
        recentModel =
                new DefaultTableModel(
                        new String[] {
                                "ID",
                                "Nombre",
                                "Carrera"
                        },
                        0
                );

        recentStudentsTable =
                new JTable(recentModel);

        recentStudentsTable.setRowHeight(28);

        recentStudentsTable.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        recentStudentsTable.setDefaultRenderer(
                Object.class,
                centerRenderer
        );

        ((DefaultTableCellRenderer)
                recentStudentsTable.getTableHeader()
                        .getDefaultRenderer())
                .setHorizontalAlignment(
                        SwingConstants.CENTER
                );

        recentScrollPane =
                new JScrollPane(recentStudentsTable);

        // BOTONES
        JButton backButton =
                createInvisibleButton();

        JButton refreshButton =
                createInvisibleButton();
        
        JButton viewAllStudentsButton =
                createInvisibleButton();

        backButton.addActionListener(
                e -> dispose()
        );

        refreshButton.addActionListener(
                e -> loadReportData()
                
        );
        
        viewAllStudentsButton.addActionListener(
                e -> new ViewStudentsFrame()
        );

        // ADD
        mainPanel.add(totalStudentsLabel);
        mainPanel.add(totalCoursesLabel);
        mainPanel.add(totalCreditsLabel);
        mainPanel.add(lastUpdateLabel);

        mainPanel.add(reportSelector);

        mainPanel.add(mainScrollPane);
        mainPanel.add(recentScrollPane);

        mainPanel.add(backButton);
        mainPanel.add(refreshButton);
        mainPanel.add(viewAllStudentsButton);

        // POSICIONES (TUS POSICIONES EXACTAS)
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

                        totalStudentsLabel.setBounds(
                                (int)(w * 0.29),
                                (int)(h * 0.29),
                                70,
                                40
                        );

                        totalCoursesLabel.setBounds(
                                (int)(w * 0.48),
                                (int)(h * 0.29),
                                70,
                                40
                        );

                        totalCreditsLabel.setBounds(
                                (int)(w * 0.67),
                                (int)(h * 0.29),
                                70,
                                40
                        );

                        lastUpdateLabel.setBounds(
                                (int)(w * 0.845),
                                (int)(h * 0.29),
                                120,
                                40
                        );

                        reportSelector.setBounds(
                                (int)(w * 0.29),
                                (int)(h * 0.40),
                                260,
                                32
                        );

                        mainScrollPane.setBounds(
                                (int)(w * 0.29),
                                (int)(h * 0.40),
                                (int)(w * 0.60),
                                (int)(h * 0.32)
                        );

                        recentScrollPane.setBounds(
                                (int)(w * 0.58),
                                (int)(h * 0.75),
                                (int)(w * 0.35),
                                (int)(h * 0.13)
                        );

                        backButton.setBounds(
                                (int)(w * 0.22),
                                (int)(h * 0.90),
                                180,
                                70
                        );

                        refreshButton.setBounds(
                                (int)(w * 0.50),
                                (int)(h * 0.90),
                                250,
                                70
                        );
                        
                        viewAllStudentsButton.setBounds(
                                (int)(w * 0.79),
                                (int)(h * 0.865),
                                220,
                                40
                        );
                    }
                }
        );

        loadReportData();

        add(mainPanel);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }

    private JLabel createStatLabel() {

        JLabel label =
                new JLabel();

        label.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        label.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        return label;
    }

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

    private void loadReportData() {

        totalStudentsLabel.setText(
                String.valueOf(
                        StudentData.students.size()
                )
        );

        totalCoursesLabel.setText(
                String.valueOf(
                        CourseData.courses.size()
                )
        );

        int totalCredits = 0;

        for (Course c : CourseData.courses) {
            totalCredits += c.getCredits();
        }

        totalCreditsLabel.setText(
                String.valueOf(totalCredits)
        );

        lastUpdateLabel.setText("Hoy");

        model.setRowCount(0);
        recentModel.setRowCount(0);

        String selected =
                (String) reportSelector.getSelectedItem();

        if (selected.equals("Estudiantes por carrera")) {

            model.setColumnIdentifiers(
                    new String[] {
                            "Carrera",
                            "Cantidad"
                    }
            );

            HashMap<String, Integer> counts =
                    new HashMap<>();

            for (Student s : StudentData.students) {

                String career =
                        normalizeCareer(s.getCareer());

                counts.put(
                        career,
                        counts.getOrDefault(career, 0) + 1
                );
            }

            for (String career : counts.keySet()) {

                model.addRow(new Object[] {
                        career,
                        counts.get(career)
                });
            }
        }

        else if (selected.equals("Cursos por carrera")) {

            model.setColumnIdentifiers(
                    new String[] {
                            "Carrera",
                            "Cantidad"
                    }
            );

            HashMap<String, Integer> counts =
                    new HashMap<>();

            for (Course c : CourseData.courses) {

                String career =
                        formatCareer(c.getCourseCode());

                counts.put(
                        career,
                        counts.getOrDefault(career, 0) + 1
                );
            }

            for (String career : counts.keySet()) {

                model.addRow(new Object[] {
                        career,
                        counts.get(career)
                });
            }
        }

        else if (selected.equals("Listado de estudiantes")) {

            model.setColumnIdentifiers(
                    new String[] {
                            "ID",
                            "Nombre",
                            "Correo",
                            "Carrera"
                    }
            );

            for (Student s : StudentData.students) {

                model.addRow(new Object[] {
                        s.getStudentId(),
                        s.getName(),
                        s.getEmail(),
                        normalizeCareer(s.getCareer())
                });
            }
        }

        else if (selected.equals("Listado de cursos")) {

            model.setColumnIdentifiers(
                    new String[] {
                            "Código",
                            "Curso",
                            "Créditos",
                            "Horario"
                    }
            );

            for (Course c : CourseData.courses) {

                model.addRow(new Object[] {
                        c.getCourseCode(),
                        c.getCourseName(),
                        c.getCredits(),
                        c.getSchedule()
                });
            }
        }

        else if (selected.equals("Curso con más créditos")) {

            model.setColumnIdentifiers(
                    new String[] {
                            "Curso",
                            "Créditos"
                    }
            );

            Course maxCourse = null;

            for (Course c : CourseData.courses) {

                if (maxCourse == null ||
                        c.getCredits() > maxCourse.getCredits()) {

                    maxCourse = c;
                }
            }

            if (maxCourse != null) {

                model.addRow(new Object[] {
                        maxCourse.getCourseName(),
                        maxCourse.getCredits()
                });
            }
        }

        // ÚLTIMOS 3 ESTUDIANTES
        int start =
                Math.max(
                        0,
                        StudentData.students.size() - 3
                );

        for (int i = start;
             i < StudentData.students.size();
             i++) {

            Student s =
                    StudentData.students.get(i);

            recentModel.addRow(new Object[] {
                    s.getStudentId(),
                    s.getName(),
                    normalizeCareer(s.getCareer())
            });
        }
    }

    private String normalizeCareer(String career) {

        if (career.contains("Ingeniería en Sistemas"))
            return "Ingeniería en Sistemas";

        if (career.contains("Ingeniería Civil"))
            return "Ingeniería Civil";

        if (career.contains("Ingeniería Industrial"))
            return "Ingeniería Industrial";

        if (career.contains("Ingeniería Electrónica"))
            return "Ingeniería Electrónica";

        if (career.contains("Arquitectura"))
            return "Arquitectura";

        if (career.contains("Administración"))
            return "Administración";

        if (career.contains("Contaduría"))
            return "Contaduría";

        if (career.contains("Derecho"))
            return "Derecho";

        return career;
    }

    private String formatCareer(String code) {

        if (code.equals("0905"))
            return "Ingeniería en Sistemas";

        if (code.equals("0901"))
            return "Ingeniería Civil";

        if (code.equals("0902"))
            return "Ingeniería Industrial";

        if (code.equals("0903"))
            return "Ingeniería Electrónica";

        if (code.equals("0904"))
            return "Arquitectura";

        if (code.equals("0910"))
            return "Administración";

        if (code.equals("0911"))
            return "Contaduría";

        if (code.equals("0912"))
            return "Derecho";

        return code;
    }
}



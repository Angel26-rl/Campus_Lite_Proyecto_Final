package ui;

import domain.Course;
import persistence.CourseFileManager;
import service.CourseData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewCoursesFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewCoursesFrame() {

        setTitle("Ver Cursos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon background =
                        new ImageIcon(
                                getClass().getResource(
                                        "/images/view_courses.png"
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

        String[] columns = {
                "Carrera",
                "Curso",
                "Créditos",
                "Horario"
        };

        model = new DefaultTableModel(columns, 0);

        loadCourses();

        table = new JTable(model);

        // CENTRAR DATOS
        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0; i < table.getColumnCount(); i++) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        table.setRowHeight(50);

        table.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        // CENTRAR ENCABEZADOS
        ((DefaultTableCellRenderer)
                table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(
                        SwingConstants.CENTER
                );

        JScrollPane scrollPane =
                new JScrollPane(table);

        JButton editButton =
                createInvisibleButton();

        JButton deleteButton =
                createInvisibleButton();

        JButton backButton =
                createInvisibleButton();

        editButton.addActionListener(e -> editCourse());

        deleteButton.addActionListener(e -> deleteCourse());

        backButton.addActionListener(e -> dispose());

        mainPanel.add(scrollPane);
        mainPanel.add(editButton);
        mainPanel.add(deleteButton);
        mainPanel.add(backButton);

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

                        scrollPane.setBounds(
                                (int)(w * 0.22),
                                (int)(h * 0.34),
                                (int)(w * 0.70),
                                (int)(h * 0.46)
                        );

                        editButton.setBounds(
                                (int)(w * 0.28),
                                (int)(h * 0.85),
                                260,
                                70
                        );

                        deleteButton.setBounds(
                                (int)(w * 0.45),
                                (int)(h * 0.85),
                                280,
                                70
                        );

                        backButton.setBounds(
                                (int)(w * 0.64),
                                (int)(h * 0.85),
                                250,
                                70
                        );
                    }
                }
        );

        add(mainPanel);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }

    private void loadCourses() {

        model.setRowCount(0);

        for (Course c : CourseData.courses) {

            model.addRow(new Object[] {

                    formatCareer(c.getCourseCode()),
                    c.getCourseName(),
                    c.getCredits(),
                    c.getSchedule()
            });
        }
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

    private void editCourse() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un curso primero."
            );

            return;
        }

        Course course =
                CourseData.courses.get(selectedRow);

        String[] careers = {
                "Ing.S 0905",
                "Ing.C 0901",
                "Ing.I 0902",
                "Ing.E 0903",
                "Arqui 0904",
                "Admin 0910",
                "Conta 0911",
                "Dere 0912"
        };

        JComboBox<String> careerCombo =
                new JComboBox<>(careers);

        JComboBox<String> courseCombo =
                new JComboBox<>();

        JTextField creditsField =
                new JTextField();

        JTextField scheduleField =
                new JTextField();

        creditsField.setEditable(false);
        scheduleField.setEditable(false);

        careerCombo.addActionListener(e -> {

            courseCombo.removeAllItems();

            String selectedCareer =
                    careerCombo.getSelectedItem().toString();

            if (selectedCareer.equals("Ing.S 0905")) {

                courseCombo.addItem("Programación I");
                courseCombo.addItem("Proceso Administrativo");
                courseCombo.addItem("Derecho Informático");
                courseCombo.addItem("Emprendedores de Negocios");
                courseCombo.addItem("Física I");
                courseCombo.addItem("Cálculo I");
                courseCombo.addItem("Bases de Datos");
                courseCombo.addItem("Redes de Computadoras");

            } else if (selectedCareer.equals("Ing.C 0901")) {

                courseCombo.addItem("Cálculo Estructural");

            } else if (selectedCareer.equals("Ing.I 0902")) {

                courseCombo.addItem("Producción Industrial");

            } else if (selectedCareer.equals("Ing.E 0903")) {

                courseCombo.addItem("Circuitos I");

            } else if (selectedCareer.equals("Arqui 0904")) {

                courseCombo.addItem("Diseño Arquitectónico");

            } else if (selectedCareer.equals("Admin 0910")) {

                courseCombo.addItem("Administración General");

            } else if (selectedCareer.equals("Conta 0911")) {

                courseCombo.addItem("Contabilidad I");

            } else if (selectedCareer.equals("Dere 0912")) {

                courseCombo.addItem("Derecho Constitucional");
            }
        });

        courseCombo.addActionListener(e -> {

            if (courseCombo.getSelectedItem() == null) {
                return;
            }

            String selectedCourse =
                    courseCombo.getSelectedItem().toString();

            if (selectedCourse.equals("Programación I")) {
                creditsField.setText("8");
                scheduleField.setText("7:00 AM - 9:00 AM");
            } else if (selectedCourse.equals("Proceso Administrativo")) {
                creditsField.setText("4");
                scheduleField.setText("9:00 AM - 11:00 AM");
            } else if (selectedCourse.equals("Derecho Informático")) {
                creditsField.setText("5");
                scheduleField.setText("11:00 AM - 1:00 AM");
            } else if (selectedCourse.equals("Emprendedores de Negocios")) {
                creditsField.setText("0");
                scheduleField.setText("2:00 PM - 4:00 PM");
            } else if (selectedCourse.equals("Física I")) {
                creditsField.setText("6");
                scheduleField.setText("4:00 AM - 6:00 AM");
            } else if (selectedCourse.equals("Cálculo I")) {
                creditsField.setText("6");
                scheduleField.setText("00:00 AM - 00:00 PM");
            } else if (selectedCourse.equals("Bases de Datos")) {
                creditsField.setText("6");
                scheduleField.setText("00:00 AM - 00:00 AM");
            } else if (selectedCourse.equals("Redes de Computadoras")) {
                creditsField.setText("5");
                scheduleField.setText("00:00 AM - 00:00 PM");
            } else if (selectedCourse.equals("Cálculo Estructural")) {
                creditsField.setText("6");
                scheduleField.setText("7:00 AM - 9:00 AM");
            } else if (selectedCourse.equals("Producción Industrial")) {
                creditsField.setText("5");
                scheduleField.setText("9:00 AM - 11:00 AM");
            } else if (selectedCourse.equals("Circuitos I")) {
                creditsField.setText("5");
                scheduleField.setText("11:00 AM - 1:00 PM");
            } else if (selectedCourse.equals("Diseño Arquitectónico")) {
                creditsField.setText("7");
                scheduleField.setText("2:00 PM - 4:00 PM");
            } else if (selectedCourse.equals("Administración General")) {
                creditsField.setText("4");
                scheduleField.setText("4:00 PM - 6:00 PM");
            } else if (selectedCourse.equals("Contabilidad I")) {
                creditsField.setText("4");
                scheduleField.setText("6:00 PM - 8:00 PM");
            } else if (selectedCourse.equals("Derecho Constitucional")) {
                creditsField.setText("6");
                scheduleField.setText("8:00 AM - 10:00 AM");
            }
        });

        careerCombo.setSelectedItem("Ing.S 0905");

        JPanel panel =
                new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Carrera:"));
        panel.add(careerCombo);

        panel.add(new JLabel("Curso:"));
        panel.add(courseCombo);

        panel.add(new JLabel("Créditos:"));
        panel.add(creditsField);

        panel.add(new JLabel("Horario:"));
        panel.add(scheduleField);

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Editar Curso",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String selectedCareer =
                careerCombo.getSelectedItem().toString();

        String careerCode =
                selectedCareer.split(" ")[1];

        String selectedCourse =
                courseCombo.getSelectedItem().toString();

        course.setCourseCode(careerCode);
        course.setCourseName(selectedCourse);
        course.setCredits(
                Integer.parseInt(
                        creditsField.getText()
                )
        );
        course.setSchedule(
                scheduleField.getText()
        );

        loadCourses();

        CourseFileManager.saveCourses();

        JOptionPane.showMessageDialog(
                this,
                "Curso actualizado correctamente."
        );
    }

    private void deleteCourse() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un curso primero."
            );

            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Seguro que desea eliminar este curso?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        CourseData.courses.remove(selectedRow);

        model.removeRow(selectedRow);

        CourseFileManager.saveCourses();

        JOptionPane.showMessageDialog(
                this,
                "Curso eliminado correctamente."
        );
    }

    private String formatCareer(String code) {

        if (code.equals("0905")) {
            return "Ingeniería en Sistemas (0905)";
        }

        if (code.equals("0901")) {
            return "Ingeniería Civil (0901)";
        }

        if (code.equals("0902")) {
            return "Ingeniería Industrial (0902)";
        }

        if (code.equals("0903")) {
            return "Ingeniería Electrónica (0903)";
        }

        if (code.equals("0904")) {
            return "Arquitectura (0904)";
        }

        if (code.equals("0910")) {
            return "Administración de Empresas (0910)";
        }

        if (code.equals("0911")) {
            return "Contaduría Pública (0911)";
        }

        if (code.equals("0912")) {
            return "Derecho (0912)";
        }

        return code;
    }
}




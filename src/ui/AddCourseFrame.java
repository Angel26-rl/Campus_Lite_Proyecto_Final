package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import domain.Course;
import persistence.CourseFileManager;
import service.CourseData;

public class AddCourseFrame extends JFrame {

    private JComboBox<String> careerCombo;
    private JComboBox<String> courseCombo;

    private JTextField txtCredits;
    private JTextField txtSchedule;

    private HashMap<String, String[]> courseCatalog;

    public AddCourseFrame() {

        setTitle("Agregar Curso");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initializeCatalog();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 240, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        // TITULO
        JLabel title = new JLabel("Agregar Curso", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));
        title.setForeground(new Color(55, 32, 130));

        JLabel subtitle = new JLabel(
                "Registro de cursos academicos",
                SwingConstants.CENTER
        );

        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitle.setForeground(new Color(100, 90, 140));

        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setOpaque(false);

        headerPanel.add(title);
        headerPanel.add(subtitle);

        // FORMULARIO
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 18, 18));
        formPanel.setOpaque(false);

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        40,
                        30,
                        40
                )
        );

        careerCombo = new JComboBox<>(new String[] {
                "Ing.S 0905",
                "Ing.C 0901",
                "Ing.I 0902",
                "Ing.E 0903",
                "Arqui 0904",
                "Admin 0910",
                "Conta 0911",
                "Dere 0912"
        });

        courseCombo = new JComboBox<>();

        txtCredits = new JTextField();
        txtCredits.setEditable(true);

        txtSchedule = new JTextField();
        txtSchedule.setEditable(true);

        formPanel.add(createLabel("Carrera:"));
        formPanel.add(careerCombo);

        formPanel.add(createLabel("Curso:"));
        formPanel.add(courseCombo);

        formPanel.add(createLabel("Creditos:"));
        formPanel.add(txtCredits);

        formPanel.add(createLabel("Horario:"));
        formPanel.add(txtSchedule);

        styleCombo(careerCombo);
        styleCombo(courseCombo);

        styleField(txtCredits);
        styleField(txtSchedule);

        // BOTONES
        JButton btnSave = new JButton("Guardar");
        JButton btnCancel = new JButton("Regresar");

        styleButton(btnSave, new Color(108, 64, 255));
        styleButton(btnCancel, new Color(45, 125, 245));

        JPanel buttonPanel = new JPanel(
                new GridLayout(1, 2, 20, 0)
        );

        buttonPanel.setOpaque(false);

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        120,
                        10,
                        120
                )
        );

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        // AGREGAR PANELES
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // EVENTOS
        careerCombo.addActionListener(e -> updateCourseList());

        courseCombo.addActionListener(e -> fillCourseData());

        btnSave.addActionListener(e -> saveCourse());

        btnCancel.addActionListener(e -> dispose());

        updateCourseList();

        setVisible(true);
    }

    // LABELS
    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(new Font("Segoe UI", Font.BOLD, 18));

        label.setForeground(new Color(40, 25, 100));

        return label;
    }

    // CAMPOS
    private void styleField(JTextField field) {

        field.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        field.setBackground(Color.WHITE);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(180, 165, 255),
                                2
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );
    }

    // COMBOBOX
    private void styleCombo(JComboBox<String> combo) {

        combo.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        combo.setBackground(Color.WHITE);

        combo.setBorder(
                BorderFactory.createLineBorder(
                        new Color(180, 165, 255),
                        2
                )
        );
    }

    // BOTONES
    private void styleButton(JButton button, Color color) {

        button.setFont(new Font("Segoe UI", Font.BOLD, 18));

        button.setBackground(color);

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // CATALOGO
    private void initializeCatalog() {

        courseCatalog = new HashMap<>();

        // SISTEMAS
        courseCatalog.put(
                "Programación I",
                new String[] {"8", "7:00 AM - 9:00 AM"}
        );

        courseCatalog.put(
                "Proceso Administrativo",
                new String[] {"4", "9:00 AM - 11:00 AM"}
        );

        courseCatalog.put(
                "Derecho Informático",
                new String[] {"5", "11:00 AM - 1:00 PM"}
        );

        courseCatalog.put(
                "Emprendedores de Negocios",
                new String[] {"0", "2:00 PM - 4:00 PM"}
        );

        courseCatalog.put(
                "Física I",
                new String[] {"6", "4:00 PM - 6:00 PM"}
        );

        courseCatalog.put(
                "Cálculo I",
                new String[] {"6", "7:00 AM - 9:00 AM"}
        );

        courseCatalog.put(
                "Bases de Datos",
                new String[] {"6", "10:00 AM - 12:00 PM"}
        );

        courseCatalog.put(
                "Redes de Computadoras",
                new String[] {"5", "1:00 PM - 3:00 PM"}
        );

        // OTRAS
        courseCatalog.put(
                "Cálculo Estructural",
                new String[] {"6", "7:00 AM - 9:00 AM"}
        );

        courseCatalog.put(
                "Producción Industrial",
                new String[] {"5", "9:00 AM - 11:00 AM"}
        );

        courseCatalog.put(
                "Circuitos I",
                new String[] {"5", "11:00 AM - 1:00 PM"}
        );

        courseCatalog.put(
                "Diseño Arquitectónico",
                new String[] {"7", "2:00 PM - 4:00 PM"}
        );

        courseCatalog.put(
                "Administración General",
                new String[] {"4", "4:00 PM - 6:00 PM"}
        );

        courseCatalog.put(
                "Contabilidad I",
                new String[] {"4", "6:00 PM - 8:00 PM"}
        );

        courseCatalog.put(
                "Derecho Constitucional",
                new String[] {"6", "8:00 AM - 10:00 AM"}
        );
    }

    // ACTUALIZAR CURSOS
    private void updateCourseList() {

        courseCombo.removeAllItems();

        String selectedCareer =
                careerCombo.getSelectedItem().toString();

        if (selectedCareer.equals("Ing.S 0905")) {

            courseCombo.addItem("Programación I");
            courseCombo.addItem("Física I");
            courseCombo.addItem("Cálculo I");
            courseCombo.addItem("Proceso Administrativo");
            courseCombo.addItem("Derecho Informático");
            courseCombo.addItem("Emprendedores de Negocios");
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

        fillCourseData();
    }

    // LLENAR DATOS
    private void fillCourseData() {

        if (courseCombo.getSelectedItem() == null) {
            return;
        }

        String selectedCourse =
                courseCombo.getSelectedItem().toString();

        String[] data =
                courseCatalog.get(selectedCourse);

        txtCredits.setText(data[0]);

        txtSchedule.setText(data[1]);
    }

    // GUARDAR CURSO
    private void saveCourse() {

        String selectedCourse =
                courseCombo.getSelectedItem().toString();

        int credits =
                Integer.parseInt(txtCredits.getText());

        String schedule =
                txtSchedule.getText();

        String selectedCareer =
                careerCombo.getSelectedItem().toString();

        String careerCode =
                selectedCareer.split(" ")[1];

        Course course =
                new Course(
                        careerCode,
                        selectedCourse,
                        credits,
                        schedule
                );

        if (CourseData.addCourse(course)) {

            CourseFileManager.saveCourses();

            JOptionPane.showMessageDialog(
                    this,
                    "Curso guardado correctamente."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Ese curso ya existe."
            );
        }
    }
}
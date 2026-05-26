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
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initializeCatalog();

        JPanel panel = new JPanel(new GridLayout(5, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(245, 247, 250));

        panel.add(new JLabel("Carrera:"));

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

        panel.add(careerCombo);

        panel.add(new JLabel("Curso:"));

        courseCombo = new JComboBox<>();
        panel.add(courseCombo);

        panel.add(new JLabel("Créditos:"));

        txtCredits = new JTextField();
        txtCredits.setEditable(false);
        panel.add(txtCredits);

        panel.add(new JLabel("Horario:"));

        txtSchedule = new JTextField();
        txtSchedule.setEditable(false);
        panel.add(txtSchedule);

        JButton btnSave = new JButton("Guardar");
        JButton btnCancel = new JButton("Cancelar");

        panel.add(btnSave);
        panel.add(btnCancel);

        add(panel);

        careerCombo.addActionListener(e -> updateCourseList());

        courseCombo.addActionListener(e -> fillCourseData());

        btnSave.addActionListener(e -> saveCourse());

        btnCancel.addActionListener(e -> dispose());

        updateCourseList();
    }

    private void initializeCatalog() {

        courseCatalog = new HashMap<>();

        // SISTEMAS
        courseCatalog.put("Programación I", new String[] {"8", "7:00 AM - 9:00 AM"});
        courseCatalog.put("Proceso Administrativo", new String[] {"4", "9:00 AM - 11:00 AM"});
        courseCatalog.put("Derecho Informático", new String[] {"5", "11:00 AM - 1:00 AM"});
        courseCatalog.put("Emprendedores de Negocios", new String[] {"0", "2:00 PM - 4:00 PM"});
        courseCatalog.put("Física I", new String[] {"6", "4:00 AM - 6:00 AM"});
        courseCatalog.put("Cálculo I", new String[] {"6", "00:00 AM - 00:00 PM"});
        courseCatalog.put("Bases de Datos", new String[] {"6", "00:00 AM - 00:00 AM"});
        courseCatalog.put("Redes de Computadoras", new String[] {"5", "00:00 AM - 00:00 PM"});

        // OTRAS
        courseCatalog.put("Cálculo Estructural", new String[] {"6", "7:00 AM - 9:00 AM"});
        courseCatalog.put("Producción Industrial", new String[] {"5", "9:00 AM - 11:00 AM"});
        courseCatalog.put("Circuitos I", new String[] {"5", "11:00 AM - 1:00 PM"});
        courseCatalog.put("Diseño Arquitectónico", new String[] {"7", "2:00 PM - 4:00 PM"});
        courseCatalog.put("Administración General", new String[] {"4", "4:00 PM - 6:00 PM"});
        courseCatalog.put("Contabilidad I", new String[] {"4", "6:00 PM - 8:00 PM"});
        courseCatalog.put("Derecho Constitucional", new String[] {"6", "8:00 AM - 10:00 AM"});
    }

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

    private void fillCourseData() {

        if (courseCombo.getSelectedItem() == null) return;

        String selectedCourse =
                courseCombo.getSelectedItem().toString();

        String[] data =
                courseCatalog.get(selectedCourse);

        txtCredits.setText(data[0]);
        txtSchedule.setText(data[1]);
    }

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


package ui;

import domain.Student;
import persistence.StudentFileManager;
import service.StudentData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import javax.swing.table.DefaultTableCellRenderer;

public class ViewStudentsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewStudentsFrame() {

        setTitle("Ver Estudiantes");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon background =
                        new ImageIcon(
                                getClass().getResource("/images/view_students.png")
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
                "ID Estudiante",
                "Nombre",
                "Correo",
                "Carrera"
        };

        model = new DefaultTableModel(columns, 0);

        for (Student student : StudentData.students) {

            model.addRow(new Object[] {

                    student.getStudentId(),
                    student.getName(),
                    student.getEmail(),
                    formatCareer(student.getCareer())
            });
        }

        table = new JTable(model);
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
        ((DefaultTableCellRenderer)
                table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
        
        JScrollPane scrollPane =
                new JScrollPane(table);

        // BOTONES INVISIBLES
        JButton editButton =
                createInvisibleButton();

        JButton deleteButton =
                createInvisibleButton();

        JButton backButton =
                createInvisibleButton();

        // FUNCIONES
        editButton.addActionListener(e -> {

            editStudent();

        });

        deleteButton.addActionListener(e -> {

            deleteStudent();

        });

        backButton.addActionListener(e -> {

            dispose();

        });

        mainPanel.add(scrollPane);

        mainPanel.add(editButton);

        mainPanel.add(deleteButton);

        mainPanel.add(backButton);

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

                        scrollPane.setBounds(
                                (int)(w * 0.025),
                                (int)(h * 0.32),
                                (int)(w * 0.95),
                                (int)(h * 0.53)
                        );

                        // EDITAR
                        editButton.setBounds(
                                (int)(w * 0.18),
                                (int)(h * 0.90),
                                300,
                                70
                        );

                        // ELIMINAR
                        deleteButton.setBounds(
                                (int)(w * 0.40),
                                (int)(h * 0.90),
                                320,
                                70
                        );

                        // REGRESAR
                        backButton.setBounds(
                                (int)(w * 0.64),
                                (int)(h * 0.90),
                                260,
                                70
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

    // EDITAR ESTUDIANTE
    private void editStudent() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un estudiante."
            );

            return;
        }

        Student student =
                StudentData.students.get(selectedRow);

        String newName =
                JOptionPane.showInputDialog(
                        this,
                        "Nuevo nombre:",
                        student.getName()
                );

        if (newName == null) return;

        String newEmail =
                JOptionPane.showInputDialog(
                        this,
                        "Nuevo correo:",
                        student.getEmail()
                );

        if (newEmail == null) return;

        String[] careers = {
                "0905 - Ingeniería en Sistemas",
                "0901 - Ingeniería Civil",
                "0902 - Ingeniería Industrial",
                "0903 - Ingeniería Electrónica",
                "0904 - Arquitectura",
                "0910 - Administración de Empresas",
                "0911 - Contaduría Pública",
                "0912 - Derecho"
        };

        JComboBox<String> careerCombo =
                new JComboBox<>(careers);

        careerCombo.setSelectedItem(student.getCareer());

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        careerCombo,
                        "Seleccione nueva carrera",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result != JOptionPane.OK_OPTION) return;

        String newCareer =
                careerCombo.getSelectedItem().toString();

        student.setName(newName);

        student.setEmail(newEmail);

        student.setCareer(newCareer);

        model.setValueAt(newName, selectedRow, 1);

        model.setValueAt(newEmail, selectedRow, 2);

        model.setValueAt(
                formatCareer(newCareer),
                selectedRow,
                3
        );

        StudentFileManager.saveStudents();

        JOptionPane.showMessageDialog(
                this,
                "Estudiante actualizado."
        );
    }

    // ELIMINAR ESTUDIANTE
    private void deleteStudent() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un estudiante."
            );

            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Seguro que desea eliminar este estudiante?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        StudentData.students.remove(selectedRow);

        model.removeRow(selectedRow);

        StudentFileManager.saveStudents();

        JOptionPane.showMessageDialog(
                this,
                "Estudiante eliminado."
        );
    }
    private String formatCareer(String career) {

        if (career.equals("0905 - Ingeniería en Sistemas")) {
            return "Ingeniería en Sistemas (0905)";
        }

        if (career.equals("0901 - Ingeniería Civil")) {
            return "Ingeniería Civil (0901)";
        }

        if (career.equals("0902 - Ingeniería Industrial")) {
            return "Ingeniería Industrial (0902)";
        }

        if (career.equals("0903 - Ingeniería Electrónica")) {
            return "Ingeniería Electrónica (0903)";
        }

        if (career.equals("0904 - Arquitectura")) {
            return "Arquitectura (0904)";
        }

        if (career.equals("0910 - Administración de Empresas")) {
            return "Administración de Empresas (0910)";
        }

        if (career.equals("0911 - Contaduría Pública")) {
            return "Contaduría Pública (0911)";
        }

        if (career.equals("0912 - Derecho")) {
            return "Derecho (0912)";
        }

        return career;
    }
}



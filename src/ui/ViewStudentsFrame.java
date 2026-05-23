package ui;

import domain.Student;
import persistence.StudentFileManager;
import service.StudentData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
                    student.getCareer()
            });
        }

        table = new JTable(model);

        table.setRowHeight(50);

        table.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

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

        String newCareer =
                JOptionPane.showInputDialog(
                        this,
                        "Nueva carrera:",
                        student.getCareer()
                );

        if (newCareer == null) return;

        student.setName(newName);

        student.setEmail(newEmail);

        student.setCareer(newCareer);

        model.setValueAt(newName, selectedRow, 1);

        model.setValueAt(newEmail, selectedRow, 2);

        model.setValueAt(newCareer, selectedRow, 3);

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

        StudentData.students.remove(selectedRow);

        model.removeRow(selectedRow);

        StudentFileManager.saveStudents();

        JOptionPane.showMessageDialog(
                this,
                "Estudiante eliminado."
        );
    }
}



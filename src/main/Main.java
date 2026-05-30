package main;

import ui.MainFrame;
import persistence.StudentFileManager;
import persistence.CourseFileManager;

public class Main {

	public static void main(String[] args) {

	    StudentFileManager.loadStudents();

	    CourseFileManager.loadCourses();

	    MainFrame frame = new MainFrame();

	    frame.setVisible(true);
	}
}
//Mostrar Ventana

//Persistencia aplicada a student y course

//Se agrego la nueva funcion de horario para course  

//Se mejoró toda la interfaz  grafica y visual del sistema 







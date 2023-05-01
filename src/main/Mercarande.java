package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.SwingUtilities;

import control.Controller;
import view.MainWindow;

public class Mercarande {

	
	//atributos
	
	private static final String DIRBD = "C:\\Users\\alexs\\eclipse-workspace\\Mercarande\\src\\resources\\BaseDeDatos.txt";
	
	
	private static void startGuiMode() throws IOException {
		
		//creamos controller:
		Controller controller = new Controller(DIRBD);
	
		controller.cargarDatos();
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow(controller);
				
			}
		});
	}
	
	public static void main(String[] args) {
		try {
			startGuiMode();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}

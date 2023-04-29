package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.SwingUtilities;

import control.Controller;

public class Mercarande {

	
	//atributos
	
	private static final String DIRBD = "baseDeDatos.txt";
	
	
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

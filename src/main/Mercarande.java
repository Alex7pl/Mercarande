package main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.SwingUtilities;

import control.Controller;
import view.MainWindow;

public class Mercarande {

	
	//atributos
	
	private static final String DIRBD = "resources\\BaseDeDatos.txt";
	
	
	private static void startGuiMode() throws IOException {
		
		ClassLoader classLoader = Mercarande.class.getClassLoader();
		File file;
		try {
			file = new File(classLoader.getResource(DIRBD).toURI());
			Controller controller = new Controller(file);
			controller.cargarDatos();
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new MainWindow(controller);
					
				}
			});
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//creamos controller:
		
	}
	
	public static void main(String[] args) {
		try {
			startGuiMode();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}

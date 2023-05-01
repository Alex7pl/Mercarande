package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.GestionSuperMercado;

public class Controller {
	
	//Atributos
	
	private GestionSuperMercado gestion;
	private String DB;
	
	//Constructor
	
	public Controller(String DB) {
		
		gestion = GestionSuperMercado.getInstance();
		this.DB = DB;
	}
	
	public void cargarDatos() throws IOException {
		
		try {
			
			File archivo = new File(DB);
	        Scanner scanner = new Scanner(archivo);
	        
			gestion.cargarDatos(scanner);
		} catch (IOException e) {
			throw new IllegalArgumentException("No se han podido cargar los datos");
		}
	}
	
	public void guardarDatos() throws Exception {
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) {
			
			gestion.guardarDatos(writer);
		} catch (Exception e) {
			throw new IllegalArgumentException("No se han podido guardar los datos");
		}	
	}
}

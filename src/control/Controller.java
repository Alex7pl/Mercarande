package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.Supermercado;

public class Controller {
	
	//Atributos
	
	private Supermercado supermercado;
	private String DB;
	
	//Constructor
	
	public Controller(String DB) {
		
		supermercado = new Supermercado();
		this.DB = DB;
	}
	
	public void cargarDatos() throws IOException {
		
		try {
			
			File archivo = new File(DB);
	        Scanner scanner = new Scanner(archivo);
	        
			supermercado.cargarDatos(scanner);
		} catch (IOException e) {
			throw new IllegalArgumentException("No se han podido cargar los datos");
		}
	}
	
	public void guardarDatos() throws Exception {
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) {
			
			supermercado.guardarDatos(writer);
		} catch (Exception e) {
			throw new IllegalArgumentException("No se han podido guardar los datos");
		}	
	}
}

package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
		
		try (BufferedReader br = new BufferedReader(new FileReader(DB))) {
			
			supermercado.cargarDatos(br);
		} catch (IOException e) {
			throw new IllegalArgumentException("No se han podido cargar los datos");
		}
	}
	
	public void guardarDatos() throws IOException {
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) {
			
			supermercado.guardarDatos(writer);
		} catch (IOException e) {
			throw new IllegalArgumentException("No se han podido guardar los datos");
		}	
	}
}

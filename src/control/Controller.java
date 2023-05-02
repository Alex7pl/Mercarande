package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.GestionMercado;
import model.Trabajador;

public class Controller {

	// Atributos

	private GestionMercado gestion;
	private String DB;

	// Constructor

	public Controller(String DB) {

		gestion = GestionMercado.getInstance();
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

	// NUEVOS DANI:

	public boolean comprobarEsTrabajador(String user) {
		return gestion.esTrabajador(user);
	}

	public boolean comprobarCredencialesCorrectas(String user, String password) {
		return gestion.credencialesCorrectas(user, password);
	}

	public Trabajador getTrabajadorConUsuario(String user) {
		return gestion.getTrabajadorConUsuario(user);
	}

	public Trabajador getTrabajadorConDNI(String dni) {
		return gestion.getTrabajador(dni);
	}

	public boolean nuevoTrabajador(String tipoUsuario, String user, String password, String name, String dni,
			float salary, int entrada, int salida) {
		return gestion.nuevoTrabajador(tipoUsuario, user, password, name, dni, salary, entrada, salida);
	}

	public boolean eliminarTrabajador(String DNI) {
		return gestion.eliminarTrabajador(DNI);
	}

	public List<Trabajador> getTrabajadores() {
		return gestion.getTrabajadores();
	}

	public boolean modificarTrabajador(String tipoUsuario, String nombre, String dni, float salary, int entrada, int salida) {
		return gestion.modificarTrabajador(tipoUsuario, nombre, dni, salary, entrada, salida);
	}
}

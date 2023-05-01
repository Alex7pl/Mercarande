package model;

import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class GestionMercado {
	
	private static GestionMercado instance = null;
	private List<Trabajador> trabajadores;
	private Supermercado supermer;

	private GestionMercado() {
		trabajadores = new ArrayList<Trabajador>();
		supermer = Supermercado.getInstance();
	}

	public List<Trabajador> listarTrabajadores() {
		return trabajadores;
	}
	
	public static synchronized GestionMercado getInstance() {
        if (instance == null) {
            instance = new GestionMercado();
        }
        return instance;
    }
	
	public void cargarDatos(Scanner scanner) {
		
		String linea = scanner.nextLine();
		String[] datos;
		
		if(linea.equals("TRABAJADORES")) {
			
			linea = scanner.nextLine();
			datos = linea.split(";");
			
			while(!datos[0].equals(".")) {
				
				String tipo = datos[0];
				String usuario = datos[1];
				String contrasena = datos[2];
				String nombre = datos[3];
				String DNI = datos[4];
				float salario = Float.parseFloat(datos[5]);
				int horaEntrada = Integer.parseInt(datos[6]);
				int horaSalida = Integer.parseInt(datos[7]);
				
				nuevoTrabajador(tipo, usuario, contrasena, nombre, DNI, salario, horaEntrada, horaSalida);
				linea = scanner.nextLine();
				datos = linea.split(";");
			}
		}
		
		supermer.cargarDatos(scanner);
	}
	
	public void guardarDatos(BufferedWriter writer) throws Exception{
		
		writer.write("TRABAJADORES");
		writer.newLine();
		
		for(Trabajador t : this.trabajadores) {
			
			writer.write(t.getTipo() + ";" + t.getUsuario() + ";" + t.getContrasena() + ";" + t.getNombre() + ";" + t.getDNI() + ";" + t.getSalario() + ";" + t.getHoraEntrada() + ";" + t.getHoraSalida());
			writer.newLine();
		}
		
		writer.write(".");
		writer.newLine();
		
		supermer.guardarDatos(writer);
	}

	// tipoUsuario debe ser: Gerente, Reponedor, DirectorRRHH, Limpiador o Cajero
	public void nuevoTrabajador(String tipoUsuario, String user, String password, String name, String dni, float salary,
			int entrada, int salida) {

		Trabajador t;

		switch (tipoUsuario) {
		case "Gerente":

			t = new Gerente(supermer, tipoUsuario, user, password, name, dni, salary, entrada, salida);
			break;

		case "Reponedor":

			t = new Reponedor(supermer, tipoUsuario, user, password, name, dni, salary, entrada, salida);
			break;

		case "DirectorRRHH":

			t = new DirectorRRHH(this, supermer, tipoUsuario, user, password, name, dni, salary, entrada, salida);
			break;

		case "Limpiador":

			t = new Limpiador(supermer, tipoUsuario, user, password, name, dni, salary, entrada, salida);
			break;

		case "Cajero":

			t = new Cajero(supermer, tipoUsuario, user, password, name, dni, salary, entrada, salida);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + tipoUsuario);
		}

		trabajadores.add(t);
	}

	public void eliminarTrabajador(String DNI) {
		trabajadores.remove(getTrabajador(DNI));
	}
	
	public Trabajador getTrabajador(String DNI) {
		Trabajador t = null;
		for(int i = 0; i < trabajadores.size(); i++) {
			if(DNI == trabajadores.get(i).getDNI()) {
				t = trabajadores.get(i);
			}
		}
		
		return t;
	}
}

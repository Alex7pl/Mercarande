package model;

import java.util.List;
import java.util.ArrayList;

public class GestionTrabajadores {

	private List<Trabajador> trabajadores;

	public GestionTrabajadores() {
		trabajadores = new ArrayList<Trabajador>();
	}

	public List<Trabajador> listarTrabajadores() {
		return trabajadores;
	}

	// tipoUsuario debe ser: Gerente, Reponedor, DirectorRRHH, Limpiador o Cajero
	public void nuevoTrabajador(Supermercado s, String tipoUsuario, String user, String password, String name, String dni, float salary,
			int entrada, int salida) {

		Trabajador t;

		switch (tipoUsuario) {
		case "Gerente":

			t = new Gerente(s, tipoUsuario, user, password, name, dni, salary, entrada, salida);
			break;

		case "Reponedor":

			t = new Reponedor(s, tipoUsuario, user, password, name, dni, salary, entrada, salida);
			break;

		case "DirectorRRHH":

			t = new Reponedor(s, tipoUsuario, user, password, name, dni, salary, entrada, salida);
			break;

		case "Limpiador":

			t = new Limpiador(s, tipoUsuario, user, password, name, dni, salary, entrada, salida);
			break;

		case "Cajero":

			t = new Cajero(s, tipoUsuario, user, password, name, dni, salary, entrada, salida);
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

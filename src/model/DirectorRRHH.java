package model;

import java.util.List;

import resources.Pair;

public class DirectorRRHH {
	
	//ATRIBUTOS:
	
		private Supermercado supermercado;
		
		private String usuario;
		
		private String contrasena;
		
		private String nombre;
		
		private String DNI;
		
		private float salario;
		
		private int horaEntrada;
		
		private int horaSalida;

	
	
	public DirectorRRHH(Supermercado supermercado, String usuario, String contrasena, String nombre, String dNI,
				float salario, int horaEntrada, int horaSalida) {
			super();
			this.supermercado = supermercado;
			this.usuario = usuario;
			this.contrasena = contrasena;
			this.nombre = nombre;
			DNI = dNI;
			this.salario = salario;
			this.horaEntrada = horaEntrada;
			this.horaSalida = horaSalida;
		}


	//Metodos
	
	public String listarTrabajadores() {
		return null;
	}
	
	
	public void nuevoTrabajador(Supermercado s, String user, String password, String name, String dni, float salary, int entrada, int salida) {
		
	}
	
	public void eliminarTrabajador(String nombre) {
		
	}
	
	public void editarTrabajador(Trabajador trabajador) {
		
	}
}

package model;

public abstract class Trabajador {

	//ATRIBUTOS:
	
	private String usuario;
	
	private String contrasena;
	
	private String nombre;
	
	private String DNI;
	
	private float salario;
	
	private int horaEntrada;
	
	private int horaSalida;
	
	private String pruebaELIMINAR;
	
	
	//M�TODOS:
	
	public Trabajador(String user, String password, String name, String dni, float salary, int entrada, int salida) {
		this.usuario = user;
		this.contrasena = password;
		this.nombre = name;
		this.DNI = dni;
		this.salario = salary;
		this.horaEntrada = entrada;
		this.horaSalida = salida;
	}
	
	public String mostrarPerfil() {
		String res = " ";
		
		
		return res;
	}
	
	//cualquier trabajador puede pedir que se limpie un pasillo.
	public void solicitarLimpieza(Pasillo p) {
		p.setSucio();
		
	}
	
	
	
	
	
	
	
}

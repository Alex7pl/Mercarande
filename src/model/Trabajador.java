package model;

import java.util.List;

import resources.Pair;

public abstract class Trabajador {

	// ATRIBUTOS:

	protected Supermercado supermercado;

	protected String tipoT;

	protected String usuario;

	protected String contrasena;

	protected String nombre;

	protected String DNI;

	protected float salario;

	protected int horaEntrada;

	protected int horaSalida;

	// M TODOS:

	public Trabajador(Supermercado s, String tipo, String user, String password, String name, String dni, float salary, int entrada,
			int salida) {
		this.supermercado = s;
		this.tipoT = tipo;
		this.usuario = user;
		this.contrasena = password;
		this.nombre = name;
		this.DNI = dni;
		this.salario = salary;
		this.horaEntrada = entrada;
		this.horaSalida = salida;
	}

	// cualquier trabajador puede pedir que se limpie un pasillo.
	public void solicitarLimpieza(String nombre) {
		// busco el pasillo
		int i = 0;
		boolean encontrado = false;

		while (!encontrado && i < supermercado.getListaPasillos().size()) {
			if (nombre == supermercado.getListaPasillos().get(i).getNombre()) {
				encontrado = true;
				supermercado.getListaPasillos().get(i).anunciarSucio();

			} else {
				i++;
			}
		}
		;
	}

	// gerente
	public abstract void generarPedido(List<Pair<String, Integer>> producto, String proveedor, Categoria c);

	// gerente
	public abstract void recepcionarPedido(String idPedido);

	// gerente
	public abstract void anyadirProveedor(String NIF, String nombre, String domic, String email, int telefono,
			Categoria categoria, List<String> productos);

	// gerente
	public abstract void eliminarProveedor(String idProveedor);

	// gerente -- Si la ocupacion del pasillo es igual a su capacidad, no se crea,
	// salta error
	public abstract void crearProducto(String Id, String nombre, String IDproveedor, String marca, String categoria,
			float precio, int unidades);

	// gerente -- Reduces tambien ocupacion
	public abstract void eliminarProducto(String iD);

	// cajero
	public abstract void crearVenta(List<Pair<String, Integer>> productos, String iDCajero);

	// cajero
	public abstract void gestionarVenta(Venta v);

	// CRU Trabajador
	// ...anyadir
	// ...eliminar

	// reponedor
	public abstract void reponerExistencias(List<Pair<String, Integer>> l, Pasillo pasillo);

	// limpiador
	public abstract void limpiar(String idPasillo);

	// directorRRHH
	public abstract void anyadirTrabajador(Supermercado s, String tipoUsuario, String user, String password,
			String name, String dni, float salary, int entrada, int salida);

	// directorRRHH
	public abstract void eliminarTrabajador(String DNI);

	// directorRRHH
	public abstract Trabajador getTrabajador(String DNI);

	public String getUsuario() {
		return usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDNI() {
		return DNI;
	}

	public float getSalario() {
		return salario;
	}

	public int getHoraEntrada() {
		return horaEntrada;
	}

	public int getHoraSalida() {
		return horaSalida;
	}

	public String getTipo() {
		return this.tipoT;
	};
	
	public Supermercado getSupermercado() {
		return supermercado;
	}

	public void setSupermercado(Supermercado supermercado) {
		this.supermercado = supermercado;
	}

	public String getTipoT() {
		return tipoT;
	}

	public void setTipoT(String tipoT) {
		this.tipoT = tipoT;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public void setHoraEntrada(int horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public void setHoraSalida(int horaSalida) {
		this.horaSalida = horaSalida;
	}

}

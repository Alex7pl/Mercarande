package model;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import resources.Pair;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Collections;

public class GestionMercado {

	private static GestionMercado instance = null;
	private List<Trabajador> trabajadores;
	private Supermercado supermer;

	private GestionMercado() {
		trabajadores = new ArrayList<Trabajador>();
		supermer = Supermercado.getInstance();
	}
	
	public Supermercado getSuper() {
		return supermer;
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

		if (linea.equals("TRABAJADORES")) {

			linea = scanner.nextLine();
			datos = linea.split(";");

			while (!datos[0].equals(".")) {

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

	public void guardarDatos(BufferedWriter writer) throws Exception {

		writer.write("TRABAJADORES");
		writer.newLine();

		for (Trabajador t : this.trabajadores) {

			writer.write(t.getTipo() + ";" + t.getUsuario() + ";" + t.getContrasena() + ";" + t.getNombre() + ";"
					+ t.getDNI() + ";" + t.getSalario() + ";" + t.getHoraEntrada() + ";" + t.getHoraSalida());
			writer.newLine();
		}

		writer.write(".");
		writer.newLine();

		supermer.guardarDatos(writer);
	}

	// tipoUsuario debe ser: Gerente, Reponedor, DirectorRRHH, Limpiador o Cajero
	public boolean nuevoTrabajador(String tipoUsuario, String user, String password, String name, String dni, float salary,
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
			return false;
		}

		trabajadores.add(t);
		return true;
	}

	public Trabajador getTrabajador(String DNI) {
		Trabajador t = null;
		for (int i = 0; i < trabajadores.size(); i++) {
			if (DNI.equals(trabajadores.get(i).getDNI())) {
				t = trabajadores.get(i);
			}
		}

		return t;
	}

	// NUEVOS DANI:

	public boolean esTrabajador(String user) {
		return trabajadores.stream().anyMatch(t -> t.getUsuario().equals(user));
	}

	public boolean credencialesCorrectas(String user, String password) {
		Optional<Trabajador> trabajadorOptional = trabajadores.stream().filter(t -> t.getUsuario().equals(user))
				.findFirst();

		if (trabajadorOptional.isPresent()) {
			Trabajador trabajador = trabajadorOptional.get();
			return trabajador.getContrasena().equals(password);
		}

		return false;
	}

	public Trabajador getTrabajadorConUsuario(String user) {
		Trabajador t = null;
		for (int i = 0; i < trabajadores.size(); i++) {
			String aux = trabajadores.get(i).getUsuario();
			if (user.equals(aux)) {
				t = trabajadores.get(i);
			}
		}
		return t;
	}
	
	public boolean eliminarTrabajador(String DNI) {
		if (trabajadores.contains(getTrabajador(DNI))) {
			trabajadores.remove(getTrabajador(DNI));
			return true;
		}
		
		return false;
	}

	public List<Trabajador> getTrabajadores() {
        // Devolver una vista no modificable de la lista
        return Collections.unmodifiableList(trabajadores);
    }
	
	public boolean modificarTrabajador(String tipoUsuario, String nombre, String dni, float salary, int entrada, int salida){
		
		Trabajador trabajador = getTrabajador(dni);
		
		if(trabajador == null) {
			return false;
		}
		
		trabajador.setTipoT(tipoUsuario);
		trabajador.setNombre(nombre);
		trabajador.setDNI(dni);
		trabajador.setSalario(salary);
		trabajador.setHoraEntrada(entrada);
		trabajador.setHoraSalida(salida);
		
		return true;
	}

	public List<String> getPasillos() {
		// TODO Auto-generated method stub
		return this.supermer.getCategoriaPasillo();
	}
	
	public List<String> getproductosID(String pasillo) {
		
		return this.supermer.getproductosID(pasillo);
	}
	
	public int getUnidadesProducto(String id, String categoria, boolean buscaTienda) {
		
		return this.supermer.buscarConCategoria(id, categoria, buscaTienda).getUnidades();
	}
	
	public int getUnidadesProductoSin(String id, boolean buscaTienda) {
		return this.supermer.buscarSinCategoria(id, buscaTienda).getUnidades();
	}
	
	public void reponerExistencias(String DNI, List<Pair<String, Integer>> l, String pasillo) {
		
		this.getTrabajador(DNI).reponerExistencias(l, pasillo);
	}
	
	public List<Pedido> getListaPedidos() {
		// TODO Auto-generated method stub
		return this.supermer.getListaPedidos();
	}
	
	public void recepcionarPedido(String dni, String id, String Categoria) {
		this.getTrabajador(dni).recepcionarPedido(id, Categoria);
	}
	
	public List<Venta> getVentas() {
		return this.supermer.getVentas();
	}
	
	public List<String> getTodosProductos(){
		return this.supermer.getTodosProductos();
	}
	
	public void crearVenta(List<Pair<String, Integer>> productos, String dni) {
		this.getTrabajador(dni).crearVenta(productos, dni);
	}
	
	public Pasillo getPasillo(String nombre) {
		boolean aux = false;
		int i = 0;

		while (!aux && i < supermer.getListaPasillos().size()) {
			if (supermer.getListaPasillos().get(i).getNombre().equals(nombre)) {
				aux = true;
				return supermer.getListaPasillos().get(i);
			}

			i++;
		}

		return null;
	}
	
	public boolean limpiarPasillo(String pasillo) {

		if (!getPasillo(pasillo).isLimpio()) {

			getPasillo(pasillo).limpiar();
			return true;
		}

		return false;
	}

	public boolean estadoPasillo(String pasillo) {
		return getPasillo(pasillo).isLimpio();
	}

	public boolean cambiarPasilloASucio(String pasillo) {
		if (getPasillo(pasillo).isLimpio()) {
			getPasillo(pasillo).anunciarSucio();
			return true;
		}
		return false;
	}

	public boolean existeUsuraio(String user) {
		boolean existe = false;
		int i = 0;

		while (!existe && i < trabajadores.size()) {
			if (trabajadores.get(i).getUsuario().equals(user)) {
				return true;
			} else {
				i++;
			}
		}

		return existe;
	}

	public boolean existeTrabajadorConDNI(String dni) {
		boolean existe = false;
		int i = 0;

		while (!existe && i < trabajadores.size()) {
			if (trabajadores.get(i).getDNI().equals(dni)) {
				return true;
			} else {
				i++;
			}
		}

		return existe;
	}

	public void modificarProducto(String id, String marca, float precio, String nombre) {
		Producto p = null;
		for (int i = 0; i < supermer.getListaPasillos().size(); i++) {
			for (int j = 0; j < supermer.getListaPasillos().get(i).getListaProductos().size(); j++) {
				if (supermer.getListaPasillos().get(i).getListaProductos().get(j).getID().equals(id)) {
					p = supermer.getListaPasillos().get(i).getListaProductos().get(j);
				}
			}
		}
		p.setMarca(marca);
		p.setPrecio(precio);
		p.setNombre(nombre);
	}

	public void modificarProveedor(String ID, String nombre, String NIF, String email, String domicilio, int telefono) {
		Proveedor p;
		for (int i = 0; i < supermer.getProveedores().size(); i++) {
			if (supermer.getProveedores().get(i).getID().equals(ID)) {
				p = supermer.getProveedores().get(i);
				p.setEmail(email);
				p.setNombre(nombre);
				p.setTelefono(telefono);
				p.setDomicilioFiscal(domicilio);
				p.setNIF(NIF);
			}
		}
	}

	public boolean pasilloOcupado(String pasillo) {
		return supermer.pasilloOcupado(pasillo);
	}
	
	public String getIDProductoConNombre(String nombre) {
		return supermer.getIDProducto(nombre);
	}
	
	public String getIDProveedorConNombre(String nombre) {
		return supermer.getIDProveedor(nombre);
	}
}

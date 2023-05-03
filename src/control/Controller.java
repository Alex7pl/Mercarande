package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.Categoria;
import model.GestionMercado;
import model.Pasillo;
import model.Pedido;
import model.Proveedor;
import model.Trabajador;
import model.Venta;
import resources.Pair;

public class Controller {

	// Atributos

	private GestionMercado gestion;
	private File DB;

	// Constructor

	public Controller(File DB) {

		gestion = GestionMercado.getInstance();
		this.DB = DB;
	}

	public void cargarDatos() throws IOException {

		try {
			
			File archivo = DB;
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
	
	public List<String> getPasillos(){
		return this.gestion.getPasillos();
	}
	
	public List<String> getProductosID(String pasillo){
		return this.gestion.getproductosID(pasillo);
	}
	
	public int getUnidadesProducto(String id, String categoria, boolean buscaTienda) {
		
		return this.gestion.getUnidadesProducto(id, categoria, buscaTienda);
	}
	
	public int getUnidadesProductoSin(String id, boolean buscaTienda) {
		return this.gestion.getUnidadesProductoSin(id, buscaTienda);
	}
	
	public void reponerExistencias(String DNI, List<Pair<String, Integer>> l, String pasillo) {
		this.gestion.reponerExistencias(DNI, l, pasillo);
	}
	
	public List<Pedido> getListaPedidos() {
		// TODO Auto-generated method stub
		return this.gestion.getListaPedidos();
	}
	
	public void recepcionarPedido(String dni, String id, String Categoria) {
		this.gestion.recepcionarPedido(dni, id, Categoria);
	}
	
	public List<Venta> getVentas() {
		return this.gestion.getVentas();
	}
	
	public List<String> getTodosProductos(){
		return this.gestion.getTodosProductos();
	}
	
	public void crearVenta(List<Pair<String, Integer>> productos, String dni) {
		this.gestion.crearVenta(productos, dni);
	}

	public boolean limpiarPasillo(String pasillo) {
		return gestion.limpiarPasillo(pasillo);
	}

	public boolean estadoPasillo(String pasillo) {
		return gestion.estadoPasillo(pasillo);
	}

	public boolean cambiarPasilloASucio(String pasillo) {
		return gestion.cambiarPasilloASucio(pasillo);
	}
	
	public boolean existeUsuraio(String user) {
		return gestion.existeUsuraio(user);
	}

	public boolean existeTrabajadorConDNI(String dni) {
		return gestion.existeTrabajadorConDNI(dni);
	}
	
	public List<Proveedor> getProveedores(){
		return gestion.getSuper().getProveedores();
	}
	
	
	public void nuevoProveedor(String DNI, String NIF, String nombre, String domic, String email, int telefono,
			Categoria categoria, String[] productos ) {
		 gestion.getTrabajador(DNI).anyadirProveedor(NIF, nombre, domic, email, telefono, categoria, productos);
	}
	
	public void modificarProveedor(String ID, String nombre,String NIF, String email, String domicilio, int telefono) {
		gestion.modificarProveedor( ID, nombre ,NIF, email, domicilio,  telefono);
	}
	
	
	public void modificarProducto(String id, String idP, String nombre, Categoria categoria, String marca, float precio, int unidades) {
		gestion.modificarProducto(id, marca, precio, nombre);
	}
	
	public void eliminarProveedor(String id) {
		gestion.getSuper().eliminarProveedor(id);
	}
	

	public List<Pasillo> getTienda(){
		return gestion.getSuper().getListaPasillos();
	}
	
	public Proveedor getProveedorID(String id) {
		return gestion.getSuper().getProveedorPorNombre(id);
	}
	
	public int getIndice(String categoria) {
		return gestion.getSuper().buscaPasillo(categoria);
	}
	
	public boolean pasilloOcupado(String pasillo) {
		return gestion.pasilloOcupado(pasillo);
	}
	
	public String getIDProductoConNombre(String nombre) {
		return gestion.getIDProductoConNombre(nombre);
	}
	
	public String getIDProveedorConNombre(String nombre) {
		return gestion.getIDProveedorConNombre(nombre);
	}
}

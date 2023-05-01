package model;

import java.util.List;

import resources.Pair;

public class DirectorRRHH extends Trabajador {

	public DirectorRRHH(Supermercado s, String tipo, String user, String password, String name, String dni, float salary,
			int entrada, int salida) {
		super(s, tipo, user, password, name, dni, salary, entrada, salida);
	}

	// Metodos

	@Override
	public void generarPedido(List<Pair<String, Integer>> producto, String proveedor, Categoria c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recepcionarPedido(String idPedido) {
		// TODO Auto-generated method stub

	}

	@Override
	public void anyadirProveedor(String NIF, String nombre, String domic, String email, int telefono,
			Categoria categoria, List<String> productos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarProveedor(String idProveedor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void crearProducto(String Id, String nombre, String IDproveedor, String marca, String categoria,
			float precio, int unidades) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarProducto(String iD) {
		// TODO Auto-generated method stub

	}

	@Override
	public void crearVenta(List<Pair<String, Integer>> productos, String iDCajero) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gestionarVenta(Venta v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reponerExistencias(List<Pair<String, Integer>> l, Pasillo pasillo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void limpiar(String idPasillo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void anyadirTrabajador(Supermercado s, String tipoUsuario, String user, String password, String name,
			String dni, float salary, int entrada, int salida) {
		supermercado.getTrabajadores().nuevoTrabajador(s, tipoUsuario, user, password, name, dni, salary, entrada, salida);
	}

	@Override
	public void eliminarTrabajador(String DNI) {
		supermercado.getTrabajadores().eliminarTrabajador(DNI);
		
	}

	@Override
	public Trabajador getTrabajador(String DNI) {
		return supermercado.getTrabajadores().getTrabajador(DNI);
	}

}
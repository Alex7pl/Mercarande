package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import resources.Pair;

public class Gerente extends Trabajador{

	public Gerente(Supermercado s, String tipo, String user, String password, String name, String dni, float salary, int entrada,
			int salida) {
		super(s, tipo, user, password, name, dni, salary, entrada, salida);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void generarPedido(List<Pair<String, Integer>> producto, String proveedor, Categoria c) {
		// TODO Auto-generated method stub
		int longitud = 8;
		// caracteres que podran formar el ID
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		// Bucle para ir generando el ID
		StringBuilder id = new StringBuilder(longitud);
		for (int i = 0; i < longitud; i++) {
			int index = (int) (Math.random() * caracteres.length());
			char randomChar = caracteres.charAt(index);
			id.append(randomChar);
		}
		
		float precio= 0;
		for (int i =0;i<producto.size();i++) {
			precio = precio + producto.get(i).getSecond();
		}
		
		LocalDate fecha = LocalDate.now();
		Pedido p = new Pedido(id.toString(),proveedor,c,precio,fecha,producto );
		supermercado.anyadirPedido(p);
	}

	@Override
	public void recepcionarPedido(String idPedido, String Categoria) {
		// TODO Auto-generated method stub
		boolean encontrado = false;
		int i=0;
		Pedido p = null;
		while (!encontrado && i<supermercado.getListaPedidos().size()) {
			if (supermercado.getListaPedidos().get(i).getIDPedido().equals(idPedido)) {
				encontrado = true;
				 p = supermercado.getListaPedidos().get(i);
			}
			else {
				i++;
			}
		}
		for (int j=0;j<p.getProductos().size();j++) {
		String idPr = p.getProductos().get(j).getFirst();
		String categoria = p.getCategoria();
		Producto pr = supermercado.buscarConCategoria(idPr, categoria, false);
		pr.setUnidades(p.getProductos().get(j).getSecond());
		}
	}

	
	
	@Override
	public void anyadirProveedor(String NIF, String nombre, String domic, String email, int telefono,
			Categoria categoria, String[] productos) {
		// Longitud que tendra el id del proveedor
		int longitud = 8;
		// caracteres que podran formar el ID
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		// Bucle para ir generando el ID
		StringBuilder id = new StringBuilder(longitud);
		for (int i = 0; i < longitud; i++) {
			int index = (int) (Math.random() * caracteres.length());
			char randomChar = caracteres.charAt(index);
			id.append(randomChar);
		}
		
		Proveedor p = new Proveedor(id.toString(), NIF, nombre, domic, email, telefono, categoria,
				new ArrayList<String>(Arrays.asList(productos)));
		supermercado.anyadirProveedor(p);

	}

	@Override
	public void eliminarProveedor(String idProveedor) {
		// TODO Auto-generated method stub
		supermercado.eliminarProveedor(idProveedor);
	}

	@Override
	public void crearProducto(String Id, String nombre, String IDproveedor, String marca, String categoria,
			float precio, int unidades) {
		// TODO Auto-generated method stub
		Categoria c= Categoria.valueOf(categoria);
		Producto p = new Producto(Id, nombre,IDproveedor, marca, c, precio,unidades);
		supermercado.crearProducto(p);
	}

	@Override
	public void eliminarProducto(String iD) {
		// TODO Auto-generated method stub
		supermercado.eliminarProducto(iD);
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
	public void reponerExistencias(List<Pair<String, Integer>> l, String pasillo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limpiar(String idPasillo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void anyadirTrabajador(Supermercado s, String tipoUsuario, String user, String password, String name,
			String dni, float salary, int entrada, int salida) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eliminarTrabajador(String DNI) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Trabajador getTrabajador(String DNI) {
		// TODO Auto-generated method stub
		return null;
	}	

}

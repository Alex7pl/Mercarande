	package model;

import java.time.LocalDate;
import java.util.List;

import resources.Pair;


public class Cajero extends Trabajador{

	
	public Cajero(Supermercado s, String tipo, String user, String password, String name, String dni, float salary, int entrada, int salida) {
		super(s, tipo, user, password, name, dni, salary, entrada, salida);
	}


		// TODO Auto-generated constructor stub

	public void crearVenta(List<Pair<String, Integer>> productos, String iDCajero) {
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
		
		
		float precioVenta = 0;
		int index = 0;
		while(index < productos.size()) {
			Producto prod = supermercado.buscarSinCategoria(productos.get(index).getFirst(), true);
			precioVenta = precioVenta + productos.get(index).getSecond() * prod.getPrecio();
			index++;
		}
		
		String id_v =id.toString();
		LocalDate date = LocalDate.now();
		
		
		Venta venta = new Venta(id_v, iDCajero, precioVenta, date, productos);
		//supermercado.getTesoreria().anyadirVenta(venta);
		gestionarVenta(venta);
	}


	@Override
	public void gestionarVenta(Venta v) {
		for(int i = 0; i < v.getProductos().size(); i++) {
			Producto p = supermercado.buscarSinCategoria(v.getProductos().get(i).getFirst(), true);
			if(p.getUnidades() > v.getProductos().get(i).getSecond()) {
				p.setUnidades(v.getProductos().get(i).getSecond() * (-1));
				@SuppressWarnings("unused")
				String pasillo = p.getCategoria();			
			}
			else {
				throw new IllegalArgumentException("No hay unidades suficientes");
			}
		}
		supermercado.anyadirVenta(v);
	}
	

	
	//NO SE TOCAN
	
	public void anyadirProveedor(String NIF, String nombre, String domic, String email, int telefono,
			List<String> productos) {
		
		
	}
	public void eliminarProveedor(String idProveedor) {
		
		
	}
	public void crearProducto(String nombre, String IDproveedor, String marca, String categoria, float precio,
			int unidades) {
		// TODO Auto-generated method stub
		
	}
	public void eliminarProducto(String iD) {
		// TODO Auto-generated method stub
		
	}

	public void limpiar(String idPasillo) {
		

	}
	
	public void generarPedido(List<Pair<String, Integer>> producto, String proveedor) {
		
	}
	
	public void recepcionarPedido(String idPedido, String Categoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generarPedido(List<Pair<String, Integer>> producto, String proveedor, Categoria c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anyadirProveedor(String NIF, String nombre, String domic, String email, int telefono,
			Categoria categoria, String[] productos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearProducto(String Id, String nombre, String IDproveedor, String marca, String categoria,
			float precio, int unidades) {
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


	@Override
	public void reponerExistencias(List<Pair<String, Integer>> l, String pasillo) {
		// TODO Auto-generated method stub
		
	}

}
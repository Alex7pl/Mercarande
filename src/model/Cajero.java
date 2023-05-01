	package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import resources.Pair;


//IMPORTS
import model.Pedido;

public class Cajero extends Trabajador{

	//Supermercado supermercado;
	//lista de las ventas hechas por el cajero
	private List<String> ventas;
	
	public Cajero(Supermercado s, String user, String password, String name, String dni, float salary, int entrada, int salida) {
		super(s, user, password, name, dni, salary, entrada, salida);
		// TODO Auto-generated constructor stub
		
		ventas = new ArrayList<String>();
	}

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
		String idProd =id.toString();
		LocalDate date = LocalDate.now();
		Venta venta = new Venta(supermercado, idProd, iDCajero, productos, date);
		//supermercado.getTesoreria().anyadirVenta(venta);
		gestionarVenta(venta);
		ventas.add(idProd);
		
	}


	@Override
	public void gestionarVenta(Venta v) {
		for(int i = 0; i < v.getProductos().size(); i++) {
			Producto p = supermercado.buscarSinCategoria(v.getProductos().get(i).getFirst(), true);
			if(p.getUnidades() > v.getProductos().get(i).getSecond()) {
				p.setUnidades(p.getUnidades() - v.getProductos().get(i).getSecond());
				String pasillo = p.getCategoria();
				
				/*
				int index = 0;
				boolean encontrado = false;
				while(!encontrado && index < supermercado.getListaPasillos().size()) {
					if(pasillo == supermercado.getListaPasillos().get(index).getNombre()) {
						supermercado.getListaPasillos().get(index).reducirOcupacion(v.getProductos().get(i).getSecond());
					}
				}
				*/
				
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
	public void reponerExistencias(List<Pair<String, Integer>> l, Pasillo pasillo) {
		// TODO Auto-generated method stub
		
		List<Producto> lista = pasillo.getListaProductos();
		
	}
	public void limpiar(String idPasillo) {
		
		
		
	}
	
	public void generarPedido(List<Pair<String, Integer>> producto, String proveedor) {
		
	}
	
	public void recepcionarPedido(String idPedido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generarPedido(List<Pair<String, Integer>> producto, String proveedor, Categoria c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anyadirProveedor(String NIF, String nombre, String domic, String email, int telefono,
			Categoria categoria, List<String> productos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearProducto(String Id, String nombre, String IDproveedor, String marca, String categoria,
			float precio, int unidades) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	

}

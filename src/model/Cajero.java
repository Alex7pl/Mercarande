	package model;

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
		String id = "v" + supermercado.getTesoreria().getNumeroVentas();
		Venta venta = new Venta(supermercado, id, iDCajero, productos);
		//supermercado.getTesoreria().anyadirVenta(venta);
		gestionarVenta(venta);
		ventas.add(id);
		
	}


	@Override
	public void gestionarVenta(Venta v) {
		
		for(int i = 0; i < v.getProductos().size(); i++) {
			Producto p = supermercado.buscarSinCategoria(v.getProductos().get(i).getFirst(), true);
			if(p.getUnidades() >= v.getProductos().get(i).getSecond()) {
				p.setUnidades(p.getUnidades() - v.getProductos().get(i).getSecond());
			}
			else {
				throw new IllegalArgumentException("no hay suficientes unidades del producto " + p.getNombre());
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
	
	
	
	
	
	

}

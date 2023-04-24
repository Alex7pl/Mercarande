package model;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import resources.Pair;

public class Supermercado {

	//ATRIBUTOS:
	private Tesoreria tesoreria;
	
	private HashMap<String, List<Producto>> almacen;
	
	private List<Pasillo> tienda;
	
	private HashMap<String, Trabajador> trabajadores; //identifica key<ID_trabajador> con value<Trbajador>
	
	private List<Pedido> pedidos;
		
	
	//MÉTODOS
	
	public Supermercado(Tesoreria tesoreria, HashMap<String, List<Producto>> almacen, List<Pasillo> tienda,
			HashMap<String, Trabajador> trabajadores, List<Pedido> pedidos) {
		this.tesoreria = tesoreria;
		this.almacen = almacen;
		this.tienda = tienda;
		this.trabajadores = trabajadores;
		this.pedidos = pedidos;
	}
	
	//llegado un pedido, reviso lo que me ha llegado y aumento las existencias en Almacén
	void aumentarExistenciasAlmacen(String IDPedido) {
			//miro el ID de los productos que hay dentro del pedido. 
			/*
			 * Si existe en mi BD (miro por string::ID)
			 * 		aumento existencias
			 * Si no...
			 * 		CREO producto y aumento existencias
			 * 
			 */
	}
	
	void reponerTienda(List<Pair<String, Integer>> pendientesReponer, String categoria) {
		// miro en el mapa de almacen con la categoria -> de la lista que me da cojo los productos ptes. de reponer
		
		
	}
	
	void reducirExistencias(Venta v) {
		//recorro la lista que tenga Venta y disminuya
		
	}
		
	void crearProducto(Producto p){
		//se crea tanto en tienda como en almacen
		
	}
	
	void eliminarProducto(Producto p) {
		//se elimina tanto en tienda como en almacen
	}
	
	void anyadirPedido(Pedido p) {
		
	}
	void eliminarPedido(Pedido p) {
		
	}
	
	//reviso si hay algún pasillo sin reponer y mando aviso al primer reponedor disponible en ese pasillo
	List<Producto> reponer(String pasillo) {
		//Reviso tooodas las capacidades y ocupaciones de los pasillos:
		//si hay algún producto a 0, lo mando reponer hasta cubrir la capacidad del pasillo.

		//hago busqueda del pasillo
		//solo repongo si hay algun producto a 0 en ese pasillo
		
		
			if(p.getOcupacion() < p.getCapacidad()) {
				//reviso si hay algún producto a 0:
				for(Producto producto : tienda.get(p)) {
					if(producto.getUnidades() == 0) {
						//mando reponer ese producto
						
					}
				}
			}
		}
	}
	
	
	
}

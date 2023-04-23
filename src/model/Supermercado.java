package model;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Supermercado {

	//ATRIBUTOS:
	
	private Tesoreria tesoreria;
	
	private HashMap<String, List<Producto>> almacen;
	
	private HashMap<Pasillo, List<Producto>> tienda;
	
	//me almacena los trabajadores de la tienda POR PASILLOS (key<string::pasillo>)
	//puede haber varios por pasillo, pero NO TODOS tienen por qué estar disponibles, pueden estar
	//haciendo una reposición en ese momento
	private HashMap<String, List<Trabajador>> trabajadoresTienda;
	
	//me almacena los trabajadores del ALMACEN por CATEGORIAS
	private HashMap<String, List<Trabajador>> trabajadoresAlmacen;
	
	
	//MÉTODOS
	
	//llegado un pedudo, reviso lo que me ha llegado y aumento las existencias en Almacén
	void aumentarExistencias(Pedido p) {
		//miro el ID de los productos que hay dentro del pedido. 
		/*
		 * Si existe en mi BD (miro por string::ID)
		 * 		aumento existencias
		 * Si no...
		 * 		CREO producto y aumento existencias
		 * 
		 */
	}
	
	//reviso si hay algún pasillo sin reponer y mando aviso al primer reponedor disponible en ese pasillo
	void reponerTienda() {
		//Reviso tooodas las capacidades y ocupaciones de los pasillos:
		//si hay algún producto a 0, lo mando reponer hasta cubrir la capacidad del pasillo.
		
		for(Pasillo p : tienda.keySet()) {
			if(p.getOcupacion() < p.getCapacidad()) {
				//reviso si hay algún producto a 0:
				for(Producto producto : tienda.get(p)) {
					if(producto.getUnidades() == 0) {
						//mando reponer
						
					}
				}
			}
		}
	}
	
	
	
}

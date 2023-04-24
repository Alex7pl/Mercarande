package model;

import java.util.ArrayList;
import java.util.List;

public class Pasillo {

	//ATRIBUTOS:
	private List <Producto> listaProductos;

	private String nombre;
	
	private boolean limpio;
	
	private int capacidad;
	
	private int ocupacion;
	
	
	//METODOS:
	public Pasillo(String nombre, boolean limpio, int capacidad) {
		this.listaProductos = new ArrayList<Producto>();
		this.nombre = nombre;
		this.limpio = limpio;
		this.capacidad = capacidad;
	}
		
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean isLimpio() {
		return limpio;
	}
	
	public int getCapacidad() {
		return this.capacidad;
	}
	
	public int getOcupacion() {
		return this.ocupacion;
	}
	
	public void anunciarSucio(){
		this.limpio = false;
	}
	
	public void limpiar(){
		this.limpio = true;
	}
	
	public void anyadirProducto(Producto p) {
		listaProductos.add(p);
	}
	public void eliminarProducto(String p) {
		//busco el producto en la lista y lo elimino con la clave 
		
		
		
	}
	
	
}

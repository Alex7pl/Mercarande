package model;

import java.util.ArrayList;
import java.util.List;

public class Pasillo {

	private List <Producto> listaProductos;

	private String nombre;
	
	private boolean limpio;
	
	private int capacidad;
	private int ocupacion;
	
	
	public Pasillo(String nombre, boolean limpio, int capacidad) {
		this.listaProductos = new ArrayList<Producto>();
		this.nombre = nombre;
		this.limpio = limpio;
		this.capacidad = capacidad;
	}
	
	//METODOS
	
	public int getCapacidad() {
		return this.capacidad;
	}
	
	public int getOcupacion() {
		return this.ocupacion;
	}
	
	
	
	
}

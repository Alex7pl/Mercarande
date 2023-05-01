package model;

import java.util.ArrayList;
import java.util.List;

public class Pasillo {

	//ATRIBUTOS:
	private List <Producto> listaProductos;

	private Categoria nombre;
	
	private boolean limpio;
	
	private int capacidad;
	
	private int ocupacion;
	
	
	//METODOS:
	public Pasillo(Categoria nombre, boolean limpio, int capacidad, int ocupacion) {
		this.listaProductos = new ArrayList<Producto>();
		this.nombre = nombre;
		this.limpio = limpio;
		this.capacidad = capacidad;
		this.ocupacion = ocupacion;
	}
		
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public String getNombre() {
		return nombre.toString();
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
	
	public void cambiarOcupacion(int n) {
		this.ocupacion += n;
	}
	
	public void anyadirProducto(Producto p) {
		listaProductos.add(p);
	}
	public void eliminarProducto(String p) {
		//busco el producto en la lista y lo elimino con la clave 
		
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < listaProductos.size()) {
			
			if(listaProductos.get(i).getID() == p) {
				encontrado = true;
				listaProductos.remove(i);
			}
			else {
				i++;
			}
		}
	}
	
	public Producto getProducto(String id) {
		Producto p = null;
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < this.listaProductos.size()) {
			if(listaProductos.get(i).getID() == id) {
				encontrado = true;
				p = listaProductos.get(i);
			}
		}
		
		return p;
	}

	public void anyadirLista(List<Producto> productos) {
		// TODO Auto-generated method stub
		
		this.listaProductos = productos;
	}
	
	
}

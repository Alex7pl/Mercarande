package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import resources.Pair;

public class Venta {
	
	//ATRIBUTOS:
	private String ID;
	
	private String IDCajero;
	
<<<<<<< HEAD
=======
	private Supermercado supermercado;

>>>>>>> baa3ac873bf87dad449408b4ddf397cb1d2b3d3a
	private float importe;
	
	private LocalDate fecha;
	
	private List<Pair<String, Integer>> productos;

		
<<<<<<< HEAD
	//METODOS:
	public Venta(String iD, String iDCajero, float importe, LocalDate date, List<Pair<String, Integer>> productos) {
		this.ID = iD;
		this.IDCajero = iDCajero;
		this.importe = importe;
=======
	//M�TODOS:
	
	public Venta(Supermercado s, String iD, String iDCajero, List<Pair<String, Integer>> productos, LocalDate date) {
		super();
		this.supermercado = s;
		this.ID = iD;
		this.IDCajero = iDCajero;
		calcularCoste();
>>>>>>> baa3ac873bf87dad449408b4ddf397cb1d2b3d3a
		this.fecha = date;
		this.productos = productos;
		
	}

	public String getID() {
		return ID;
	}

	public String getIDCajero() {
		return IDCajero;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}

	public float getImporte() {
		return importe;
	}

	public List<Pair<String, Integer>> getProductos() {
		return productos;
	}
<<<<<<< HEAD
=======
	public void calcularCoste() {
		float precioPedido = 0;
		int index = 0;
		while(index < productos.size()) {
			Producto prod = supermercado.buscarSinCategoria(productos.get(index).getFirst(), true);
			precioPedido = precioPedido + productos.get(index).getSecond() * prod.getPrecio();
		}
		importe = precioPedido;
	}
>>>>>>> baa3ac873bf87dad449408b4ddf397cb1d2b3d3a
}


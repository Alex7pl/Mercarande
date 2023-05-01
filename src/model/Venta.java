package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import resources.Pair;

public class Venta {
	
	//ATRIBUTOS:
	private String ID;
	
	private String IDCajero;
	
	private Supermercado supermercado;

	private float importe;
	
	private LocalDate fecha;
	
	private List<Pair<String, Integer>> productos;

		
	//M�TODOS:
	
	public Venta(Supermercado s, String iD, String iDCajero, List<Pair<String, Integer>> productos) {
		super();
		this.supermercado = s;
		this.ID = iD;
		this.IDCajero = iDCajero;
		calcularCoste();
		this.fecha = LocalDate.now();
		this.productos = productos;
		
	}


	public String getID() {
		return ID;
	}


	public String getIDCajero() {
		return IDCajero;
	}


	public float getImporte() {
		return importe;
	}

	public List<Pair<String, Integer>> getProductos() {
		return productos;
	}
	public void calcularCoste() {
		float precioPedido = 0;
		int index = 0;
		while(index < productos.size()) {
			Producto prod = supermercado.buscarSinCategoria(productos.get(index).getFirst(), true);
			precioPedido = precioPedido + productos.get(index).getSecond() * prod.getPrecio();
		}
		importe = precioPedido;
	}
}

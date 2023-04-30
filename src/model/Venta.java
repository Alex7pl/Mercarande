package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import resources.Pair;

public class Venta {
	
	//ATRIBUTOS:
	private String ID;
	
	private String IDCajero;

	private float importe;
	
	private LocalDate fecha;
	
	private List<Pair<String, Integer>> productos;

		
	//MÉTODOS:
	
	public Venta(String iD, String iDCajero, float importe, LocalDate fecha, List<Pair<String, Integer>> productos) {
		super();
		ID = iD;
		IDCajero = iDCajero;
		this.importe = importe;
		this.fecha = fecha;
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


	
	
	
	
	
	

}

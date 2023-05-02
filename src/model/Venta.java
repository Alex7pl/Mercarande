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

		

	//METODOS:
	public Venta(String iD, String iDCajero, float importe, LocalDate date, List<Pair<String, Integer>> productos) {
		this.ID = iD;
		this.IDCajero = iDCajero;
		this.importe = importe;
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
}


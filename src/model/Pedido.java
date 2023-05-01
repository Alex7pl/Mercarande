package model;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import resources.Pair;

public class Pedido {
	
	//ATRIBUTOS:
	
	//ya existe en mi BD, no lo construyo, solo busco
	private String IDPedido;
		
	private String proveedor;
	
	private Categoria categoria;
	
	private float precioPedido;
	
	//clase de java.time para gestionar las fechas. LocalTime tiene comparadores, sumadores y actualizadores para 
	//las fechas:
	private LocalDate fechaPedido;
		
	private LocalDate fechaEsperada;
		
	//gestiono por ID del producto y luego lo CREO en mi BD, en el método de la class Supermercado.
	private List<Pair<String, Integer>> productos;

	
	//M�TODOS:
	
	public Pedido(String iDPedido, String proveedor, Categoria categoria, float precioPedido, LocalDate fechaPedido, List<Pair<String, Integer>> productos) {
		IDPedido = iDPedido;
		this.proveedor = proveedor;
		this.categoria = categoria;
		this.precioPedido = precioPedido;
		this.productos = productos;
		
		this.fechaPedido = fechaPedido;
		this.fechaEsperada = fechaPedido.plusDays(10);
	}

	public LocalDate getFechaPedido() {
		return fechaPedido;
	}


	public LocalDate getFechaEsperada() {
		return fechaEsperada;
	}


	public String getIDPedido() {
		return IDPedido;
	}


	public String getProveedor() {
		return proveedor;
	}


	public float getPrecioPedido() {
		return precioPedido;
	}

	public List<Pair<String, Integer>> getProductos() {
		return productos;
	}

	public String getCategoria() {
		// TODO Auto-generated method stub
		return categoria.toString();
	}




}

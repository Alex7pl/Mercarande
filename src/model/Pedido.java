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
	
<<<<<<< HEAD
	private Categoria categoria;
	
=======
>>>>>>> baa3ac873bf87dad449408b4ddf397cb1d2b3d3a
	private float precioPedido;
	
	//clase de java.time para gestionar las fechas. LocalTime tiene comparadores, sumadores y actualizadores para 
	//las fechas:
	private LocalDate fechaPedido;
		
	private LocalDate fechaEsperada;
		
	//gestiono por ID del producto y luego lo CREO en mi BD, en el método de la class Supermercado.
	private List<Pair<String, Integer>> productos;

	
	//M�TODOS:
	
<<<<<<< HEAD
	public Pedido(String iDPedido, String proveedor, Categoria categoria, float precioPedido, LocalDate fechaPedido, List<Pair<String, Integer>> productos) {
		IDPedido = iDPedido;
=======
	public Pedido(String iDPedido2, String proveedor, int precioPedido, LocalDate date) {
		this.IDPedido = iDPedido2;
>>>>>>> baa3ac873bf87dad449408b4ddf397cb1d2b3d3a
		this.proveedor = proveedor;
		this.precioPedido = precioPedido;
		this.productos = new ArrayList<Pair<String, Integer>>();
		this.fechaPedido = date;
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




}

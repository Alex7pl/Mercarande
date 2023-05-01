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
	
	private float precioPedido;
	
	//clase de java.time para gestionar las fechas. LocalTime tiene comparadores, sumadores y actualizadores para 
	//las fechas:
	private LocalDate fechaPedido;
		
	private LocalDate fechaEsperada;
		
	//gestiono por ID del producto y luego lo CREO en mi BD, en el método de la class Supermercado.
	private List<Pair<String, Integer>> productos;
	Supermercado supermercado;

	
	//M�TODOS:
	
	public Pedido(Supermercado s,String iDPedido, String proveedor) {
		this.supermercado = s;
		this.IDPedido = iDPedido;
		this.proveedor = proveedor;
		this.precioPedido = 0;
		this.productos = new ArrayList<Pair<String, Integer>>();
		
		this.fechaPedido = LocalDate.now();
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
	
	public void meterProductos(List<Pair<String, Integer>> producto) {
		int index = 0;
		productos = producto;
		while(index < productos.size()) {
			Producto prod = supermercado.buscarSinCategoria(producto.get(index).getFirst());
			precioPedido = precioPedido + producto.get(index).getSecond() * prod.getPrecio();
		}
		
	}




}


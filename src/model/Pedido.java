package model;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import resources.Pair;

public class Pedido {
	
	//ATRIBUTOS:
	
	
	//ya existe en mi BD, no lo construyo, solo busco
	private String gerente;
	
	private String prov;
	
	//clase de java.time para gestionar las fechas. LocalTime tiene comparadores, sumadores y actualizadores para 
	//las fechas:
	private LocalTime fechaPedido;
	
	private LocalTime fechaLlegada;
		
	//gestiono por ID del producto y luego lo CREO en mi BD, en el método de la class Supermercado.
	private List<Pair<String, Integer>> productos;
	
	
	//MÉTODOS:
	
	public Pedido() {
		
	}
	
	
	
	

}

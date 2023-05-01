package model;

import java.util.ArrayList;
import java.util.List;

public class Tesoreria {

	//ATRIBUTOS:
	private int ingresos;
	
	private int gastos;
	
	private List<Venta> ventas;
	
	private List<Pedido> pedidos;
	
	private int numeroVentasGeneradas;
	
	
	//M TODOS:
	public Tesoreria() {
		ingresos = 0;
		gastos = 0;
		ventas = new ArrayList<Venta>();
		pedidos = new ArrayList<Pedido>();
		numeroVentasGeneradas = 0;
	}
	
	public int getSaldo() {
		return ingresos - gastos;
	}

	public int getIngresos() {
		return ingresos;
	}

	public int getGastos() {
		return gastos;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	public void anyadirVenta(Venta v) {
		ventas.add(v);
		ingresos += v.getImporte();
	}
	public void anyadirPedido(Pedido p) {
		pedidos.add(p);
		gastos += p.getPrecioPedido();
	}

	
	
	
}
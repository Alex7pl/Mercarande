package model;

import java.util.ArrayList;
import java.util.List;

public class Tesoreria {

	//ATRIBUTOS:
	private int ingresos;
	
	private int gastos;
	
	private List<Venta> ventas;
	
	private List<Pedido> pedidos;
	
	
	//MÉTODOS:
	public Tesoreria(int ingresos, int gastos) {
		this.ingresos = ingresos;
		this.gastos = gastos;
		ventas = new ArrayList<Venta>();
		pedidos = new ArrayList<Pedido>();
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

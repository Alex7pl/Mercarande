package model;

import java.util.List;

import resources.Pair;

public class Reponedor extends Trabajador{

	// ATRIBUTOS DEL HIJO:

	public Reponedor(Supermercado supermercado, String tipo, String user, String password, String name, String dni, float salary,
				int entrada, int salida) {
			super(supermercado, tipo, user, password, name, dni, salary, entrada, salida);
			// TODO Auto-generated constructor stub
	}

		@Override
	public void generarPedido(List<Pair<String, Integer>> producto, String proveedor, Categoria c) {
			// TODO Auto-generated method stub

	}

		@Override
	public void recepcionarPedido(String idPedido, String Categoria) {
			int i = 0;
			boolean encontrado = false;
			while (!encontrado && i < supermercado.getListaPedidos().size()) {
				Pedido p = supermercado.getListaPedidos().get(i);
				if (p.getIDPedido().equals(idPedido)) {
					encontrado = true;
					for (int j = 0; j < p.getProductos().size(); j++)
						supermercado.buscarConCategoria(p.getProductos().get(j).getFirst(), Categoria, false).setUnidades(p.getProductos().get(j).getSecond());
					supermercado.getListaPedidos().remove(i);

				} else {
					i++;
				}
			}
	}

		@Override
	public void eliminarProveedor(String idProveedor) {
			// TODO Auto-generated method stub

	}

		@Override
	public void eliminarProducto(String iD) {
			// TODO Auto-generated method stub

	}

		@Override
	public void crearVenta(List<Pair<String, Integer>> productos, String iDCajero) {
			// TODO Auto-generated method stub

	}

		@Override
	public void gestionarVenta(Venta v) {
			// TODO Auto-generated method stub

	}
	@Override
	public void reponerExistencias(List<Pair<String, Integer>> l, String pasillo) {

		for (int i=0;i<l.size();i++) {
			int rep ;
			Producto p = supermercado.buscarConCategoria(l.get(i).getFirst(), pasillo, false);
			rep = l.get(i).getSecond();
			int repN = rep * (-1);
			p.setUnidades(repN);

	
			Producto pTienda = supermercado.buscarConCategoria(l.get(i).getFirst(), pasillo, true);
			pTienda.setUnidades(rep);
		}

	}
	@Override
	public void limpiar(String idPasillo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void crearProducto(String Id, String nombre, String IDproveedor, String marca, String categoria,
			float precio, int unidades) {
		// TODO Auto-generated method stub

	}

	@Override
	public void anyadirProveedor(String NIF, String nombre, String domic, String email, int telefono,
			Categoria categoria, String[] productos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void anyadirTrabajador(Supermercado s, String tipoUsuario, String user, String password, String name,
			String dni, float salary, int entrada, int salida) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarTrabajador(String DNI) {
		// TODO Auto-generated method stub

	}

	@Override
	public Trabajador getTrabajador(String DNI) {
		// TODO Auto-generated method stub
		return null;
	}

}
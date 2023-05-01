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
	public void recepcionarPedido(String idPedido) {
			int i = 0;
			boolean encontrado = false;
			while (!encontrado && i < supermercado.getListaPedidos().size()) {
				Pedido p = supermercado.getListaPedidos().get(i);
				if (p.getIDPedido().equals(idPedido)) {
					encontrado = true;
					for (int j = 0; j < p.getProductos().size(); j++)
						supermercado.buscarSinCategoria(p.getProductos().get(j).getFirst(), false)
								.setUnidades(p.getProductos().get(j).getSecond());

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
	public void reponerExistencias(List<Pair<String, Integer>> l, Pasillo pasillo) {
		// TODO Auto-generated method stub
		/*int i=0;
		boolean encontrado = false;
		while (i<)
			*/
		for (int i=0;i<l.size();i++) {
			String categoria = pasillo.getNombre();
			int rep ;
			Producto p = supermercado.buscarConCategoria(l.get(i).getFirst(), categoria, false);
			if (p.getUnidades()>l.get(i).getSecond()) {
				rep = l.get(i).getSecond();
				p.setUnidades(l.get(i).getSecond() * -1);
			}
			else {
				rep = p.getUnidades();
				p.setUnidades(rep*-1);
			}
			Producto pTienda = supermercado.buscarConCategoria(l.get(i).getFirst(), categoria, true);
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
			Categoria categoria, List<String> productos) {
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
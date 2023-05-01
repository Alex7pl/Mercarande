package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import resources.Pair;

public class Supermercado {

	//ATRIBUTOS:
	private Tesoreria tesoreria;
	
	private HashMap<Categoria, List<Producto>> almacen;
	
	private List<Pasillo> tienda;
	
	private GestionTrabajadores trabajadores;
	
	private List<Pedido> pedidos_pendientes;
	
	private List<Proveedor> proveedores;
		
	
	//Mï¿½TODOS
	public Supermercado() {}
	
	
	public void cargarDatos(Scanner scanner) {
		
		while (scanner.hasNextLine()) {
			
			String linea = scanner.nextLine();
			String[] datos;
			
			if(linea.equals("TESORERIA")) {
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				this.tesoreria = new Tesoreria();
				
				while(!datos[0].equals(".")) {
					
					String ID = datos[0];
					String IDCajero = datos[1];
					float importe = Float.parseFloat(datos[2]);
					LocalDate fecha = LocalDate.parse(datos[3]);
					List<Pair<String, Integer>> productos = new ArrayList<Pair<String, Integer>>();
					
					linea = scanner.nextLine();
					datos = linea.split(";");
					
					while(!datos[0].equals(".")) {
						
						productos.add(new Pair<String, Integer>(datos[0], Integer.parseInt(datos[1])));
						linea = scanner.nextLine();
						datos = linea.split(";");
					}
					
					this.tesoreria.anyadirVenta(new Venta(ID, IDCajero, importe, fecha, productos));
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
				
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(!datos[0].equals(".")) {
					String IDPedido = datos[0];
					String proveedor = datos[1];
					Categoria categoria = Categoria.valueOf(datos[2]);
					int precioPedido = Integer.parseInt(datos[3]);
					LocalDate fechaPedido = LocalDate.parse(datos[4]);
					List<Pair<String, Integer>> productos = new ArrayList<Pair<String, Integer>>();
					
					linea = scanner.nextLine();
					datos = linea.split(";");
					
					while(!datos[0].equals(".")) {
						
						productos.add(new Pair<String, Integer>(datos[0], Integer.parseInt(datos[1])));
						linea = scanner.nextLine();
						datos = linea.split(";");
					}
					
					this.tesoreria.anyadirPedido(new Pedido(IDPedido, proveedor, categoria, precioPedido, fechaPedido, productos));
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
			}
			else if(linea.equals("PASILLOS")) {
				
				this.tienda = new ArrayList<Pasillo>();
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(!datos[0].equals(".")) {
					
					Categoria Nombre = Categoria.valueOf(datos[0]);
					boolean limpio = Boolean.parseBoolean(datos[1]);
					int capacidad = Integer.parseInt(datos[2]);
					int ocupacion = Integer.parseInt(datos[3]);
					
					this.tienda.add(new Pasillo(Nombre, limpio, capacidad, ocupacion));
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
			}
			else if(linea.equals("PRODUCTOS")) {
				
				List<Producto> FVA = new ArrayList<Producto>();
				List<Producto> FVT = new ArrayList<Producto>();
				
				List<Producto> CAA = new ArrayList<Producto>();
				List<Producto> CAT = new ArrayList<Producto>();
				
				List<Producto> PEA = new ArrayList<Producto>();
				List<Producto> PET = new ArrayList<Producto>();
				
				List<Producto> COA = new ArrayList<Producto>();
				List<Producto> COT = new ArrayList<Producto>();
				
				List<Producto> BEA = new ArrayList<Producto>();
				List<Producto> BET = new ArrayList<Producto>();
				
				List<Producto> HOA = new ArrayList<Producto>();
				List<Producto> HOT = new ArrayList<Producto>();
				
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(!datos[0].equals(".")) {
					
					String ID = datos[0];
					String nombre = datos[1];
					String IDproveedor = datos[2];
					String marca = datos[3];
					Categoria categoria = Categoria.valueOf(datos[4]);;
					float precio = Float.parseFloat(datos[5]);
					int UT = Integer.parseInt(datos[6]);
					int UA = Integer.parseInt(datos[7]);
					
					Producto auxA = new Producto(ID, nombre, IDproveedor, marca, categoria, precio, UA);
					Producto auxT = new Producto(ID, nombre, IDproveedor, marca, categoria, precio, UT);
					
					if(categoria.equals(Categoria.FRUTA_VERDURA)) {
						
						FVA.add(auxA);
						FVT.add(auxT);
					}
					else if(categoria.equals(Categoria.CARNICERIA)) {
						
						CAA.add(auxA);
						CAT.add(auxT);
					}
					else if(categoria.equals(Categoria.PESCADERIA)) {
						
						PEA.add(auxA);
						PET.add(auxT);
					}
					else if(categoria.equals(Categoria.CONGELADOS)) {
						
						COA.add(auxA);
						COT.add(auxT);
					}
					else if(categoria.equals(Categoria.BEBIDAS)) {
						
						BEA.add(auxA);
						BET.add(auxT);
					}
					else if(categoria.equals(Categoria.HOGAR)) {
						
						HOA.add(auxA);
						HOT.add(auxT);
					}
					
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
				
				this.almacen = new HashMap<Categoria, List<Producto>>();
				this.almacen.put(Categoria.FRUTA_VERDURA, FVA);
				this.almacen.put(Categoria.CARNICERIA, CAA);
				this.almacen.put(Categoria.PESCADERIA, PEA);
				this.almacen.put(Categoria.CONGELADOS, COA);
				this.almacen.put(Categoria.BEBIDAS, BEA);
				this.almacen.put(Categoria.HOGAR, HOA);
				
				
				this.tienda.get(0).anyadirLista(FVT);
				this.tienda.get(1).anyadirLista(CAT);
				this.tienda.get(2).anyadirLista(PET);
				this.tienda.get(3).anyadirLista(COT);
				this.tienda.get(4).anyadirLista(BET);
				this.tienda.get(5).anyadirLista(HOT);
				
			}
			else if(linea.equals("TRABAJADORES")) {
				
				this.trabajadores = new GestionTrabajadores();
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(!datos[0].equals(".")) {
					
					String tipo = datos[0];
					String usuario = datos[1];
					String contrasena = datos[2];
					String nombre = datos[3];
					String DNI = datos[4];
					float salario = Float.parseFloat(datos[5]);
					int horaEntrada = Integer.parseInt(datos[6]);
					int horaSalida = Integer.parseInt(datos[7]);
					
					this.trabajadores.nuevoTrabajador(this, tipo, usuario, contrasena, nombre, DNI, salario, horaEntrada, horaSalida);
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
			}
			else if(linea.equals("PEDIDOS")) {
				
				this.pedidos_pendientes = new ArrayList<Pedido>();
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(!datos[0].equals(".")) {
					String IDPedido = datos[0];
					String proveedor = datos[1];
					Categoria categoria = Categoria.valueOf(datos[2]);
					int precioPedido = Integer.parseInt(datos[3]);
					LocalDate fechaPedido = LocalDate.parse(datos[4]);
					List<Pair<String, Integer>> productos = new ArrayList<Pair<String, Integer>>();
					
					linea = scanner.nextLine();
					datos = linea.split(";");
					
					while(!datos[0].equals(".")) {
						
						productos.add(new Pair<String, Integer>(datos[0], Integer.parseInt(datos[1])));
						linea = scanner.nextLine();
						datos = linea.split(";");
					}
					
					this.pedidos_pendientes.add(new Pedido(IDPedido, proveedor, categoria, precioPedido, fechaPedido, productos));
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
			}
			else if(linea.equals("PROVEEDORES")) {
				
				this.proveedores = new ArrayList<Proveedor>();
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(!datos[0].equals(".")) {
					
					String ID = datos[0];
					String NIF = datos[1];
					String nombre = datos[2];
					String domicilioFiscal = datos[3];
					String email = datos[4];
					int telefono = Integer.parseInt(datos[5]);
					Categoria categoria = Categoria.valueOf(datos[6]);
					List<String> productos = new ArrayList<String>();
					
					linea = scanner.nextLine();
					datos = linea.split(";");
					
					while(!datos[0].equals(".")) {
						productos.add(datos[0]);
						linea = scanner.nextLine();
						datos = linea.split(";");
					}
					
					this.proveedores.add(new Proveedor(ID, NIF, nombre, domicilioFiscal, email, telefono, categoria, productos));
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
			}
		}
		
		scanner.close();
	}
	
	public void guardarDatos(BufferedWriter writer) throws Exception{
		
		writer.write("TESORERIA");
		writer.newLine();
		
		for(Venta v : tesoreria.getVentas()) {
			
			writer.write(v.getID() + ";" + v.getIDCajero() + ";" + v.getImporte() + ";" + v.getFecha());
			writer.newLine();
			
			for(Pair<String, Integer> par : v.getProductos()) {
				writer.write(par.getFirst() + ";" + par.getSecond());
				writer.newLine();
			}
			
			writer.write(".");
			writer.newLine();
		}
		
		writer.write(".");
		writer.newLine();
		
		for(Pedido p : tesoreria.getPedidos()) {
			
			writer.write(p.getIDPedido() + ";" + p.getProveedor() + ";" + p.getCategoria() + ";" + p.getPrecioPedido() + ";" + p.getFechaPedido());
			writer.newLine();
			
			for(Pair<String, Integer> par : p.getProductos()) {
				writer.write(par.getFirst() + ";" + par.getSecond());
				writer.newLine();
			}
			
			writer.write(".");
			writer.newLine();
		}
		
		writer.write(".");
		writer.newLine();
		
		writer.write("PASILLOS");
		writer.newLine();
		
		for(Pasillo p : tienda) {
			
			writer.write(p.getNombre() + ";" + p.isLimpio() + ";" + p.getCapacidad() + ";" + p.getOcupacion());
			writer.newLine();
		}
		
		writer.write(".");
		writer.newLine();
		
		writer.write("PRODUCTOS");
		writer.newLine();
		
		for(Pasillo p : tienda) {
			
			for(int i = 0; i < p.getListaProductos().size(); i++) {
				
				Producto pr = p.getListaProductos().get(i);
				
				writer.write(pr.getID() + ";" + pr.getNombre() + ";" + pr.getProveedor() + ";" + pr.getMarca() + ";" + pr.getCategoria() + ";" + pr.getPrecio() + ";" + pr.getUnidades() + ";");
				writer.write(this.almacen.get(Categoria.valueOf(p.getNombre())).get(i).getUnidades());
				writer.newLine();
			}
		}
		
		writer.write(".");
		writer.newLine();
		
		writer.write("TRABAJADORES");
		writer.newLine();
		
		for(Trabajador t : this.trabajadores.listarTrabajadores()) {
			
			writer.write(t.getTipo() + ";" + t.getUsuario() + ";" + t.getContrasena() + ";" + t.getNombre() + ";" + t.getDNI() + ";" + t.getSalario() + ";" + t.getHoraEntrada() + ";" + t.getHoraSalida());
			writer.newLine();
		}
		
		writer.write(".");
		writer.newLine();
		
		writer.write("PEDIDOS");
		writer.newLine();
		
		for(Pedido p : this.pedidos_pendientes) {
			
			writer.write(p.getIDPedido() + ";" + p.getProveedor() + ";" + p.getCategoria() + ";" + p.getPrecioPedido() + ";" + p.getFechaPedido());
			writer.newLine();
			
			for(Pair<String, Integer> par : p.getProductos()) {
				writer.write(par.getFirst() + ";" + par.getSecond());
				writer.newLine();
			}
			
			writer.write(".");
			writer.newLine();
		}
		
		writer.write(".");
		writer.newLine();
		
		writer.write("PROVEEDORES");
		writer.newLine();
		
		for(Proveedor p : this.proveedores) {
			
			writer.write(p.getID() + ";" + p.getNIF() + ";" + p.getNombre() + ";" + p.getDomicilioFiscal() + ";" + p.getEmail() + ";" + p.getTelefono() + ";" + p.getCategoria());
			writer.newLine();
			
			for(String pr : p.getProductos()) {
				writer.write(pr);
				writer.newLine();
			}
			
			writer.write(".");
			writer.newLine();
		}
		
		writer.write(".");
		writer.newLine();
	}
	
	//Se necesita la lista de pasillos para cogerla en la clase Reponedor y poder reponer
	public List<Pasillo> getListaPasillos(){
		return tienda; 
		
	}
	
	public Producto buscarConCategoria(String id, String categoria, boolean buscaTienda) {
		Producto p = null;
		boolean encontrado = false;
		int i = 0;
		
		if(buscaTienda) {
			//devuelvo el producto de la TIENDA						
			while(!encontrado && i < tienda.size()) {
				
				Pasillo pasillo = tienda.get(i);
				
				if(pasillo.getNombre().equals(categoria)) {
					//busco en su lista de productos el mio:
					p = pasillo.getProducto(id);
					encontrado = true; // xxx
					
				}
				else
					i++;
			}

		}
		else {
			//devuelvo el producto del ALMACÉN
			List<Producto> lista = almacen.get(Categoria.valueOf(categoria));
			
			while(!encontrado && i < lista.size()) {
				
				if(lista.get(i).getID() == id) {
					encontrado = true;
					p = lista.get(i);
				}
				i++;
			}
		}
		
		return p;
	}
	
	// se le llamará cuando el cajero vaya a realizar una venta por ejemplo:
	public Producto buscarSinCategoria(String id, boolean buscaTienda) {
		//busqueda por fuerza bruta:
		Producto p = null;
		
		if(buscaTienda) {
			//busco en la tienda:
			boolean encontrado = false;
			int i = 0;
			int j = 0;
			
			while(!encontrado && i < this.tienda.size()) {
				//i-ésimo pasillo: recorro todos los productos:
				j= 0;
				List<Producto> l = tienda.get(i).getListaProductos();
				
				while(!encontrado && j < l.size()) {
					if(l.get(j).getID() == id) {
						encontrado = true; 
						p = l.get(j);
					}
					else {
						j++;
					}
				}
				i++;
			}
		
			
		}
		else {
			//busco en el almacén:
			Producto productoEncontrado = null;
			Iterator<Map.Entry<Categoria, List<Producto>>> iteradorMapa = almacen.entrySet().iterator();

			while (iteradorMapa.hasNext() && productoEncontrado == null) {
			    Map.Entry<Categoria, List<Producto>> entrada = iteradorMapa.next();
			    List<Producto> productos = entrada.getValue();
			    Iterator<Producto> iteradorProductos = productos.iterator();

			    while (iteradorProductos.hasNext() && productoEncontrado == null) {
			        Producto producto = iteradorProductos.next();
			        if (producto.getID() == id) {
			            p = producto;
			        }
			    }
			}

		}
		
		return p;
	}
	
	//solo tengo que devover el índice, XXX innecesarios???
	public int buscaPedido(String id) {
		boolean encontrado = false;
		int i = 0;
		int res = 0;
		
		while (!encontrado && i < pedidos_pendientes.size()) {
			if(pedidos_pendientes.get(i).getIDPedido() == id) {
				encontrado = false;
				res = i;
			}
			else {
				i++;
			}
		}
		
		return res;
	}
	public int buscaProveedor(String id) {
		boolean encontrado = false;
		int i = 0;
		int res = 0;
		
		while (!encontrado && i < proveedores.size()) {
			if(proveedores.get(i).getID() == id) {
				encontrado = false;
				res = i;
			}
			else {
				i++;
			}
		}
		
		return res;
	}
	public int buscaPasillo(String categoria) {
		boolean encontrado = false;
		int i = 0;
		int res = 0;
		
		while(!encontrado && i < tienda.size()) {
			if(tienda.get(i).getNombre() == categoria) {
				encontrado = true;
				res = i;
			}
		}
		return res;
	}
	
	
	
	public void crearProducto(Producto p){
		//se crea tanto en tienda como en almacen
		//añadir el producto tanto en almacen como en la tienda en su categoría correspondiente
		this.almacen.get(Categoria.valueOf(p.getCategoria())).add(p);
		
		Producto p_aux = new Producto(p);
		this.tienda.get(buscaPasillo(p.getCategoria())).anyadirProducto(p_aux);
		
		
		//subo ocupacion:
		int indicePasillo = this.buscaPasillo(p.getCategoria());
		tienda.get(indicePasillo).cambiarOcupacion(1);
	}
	
	public void eliminarProducto(String id) {
		//se elimina tanto en tienda como en almacen
		Producto p_almacen = buscarSinCategoria(id, false);
		Producto p_tienda = buscarSinCategoria(id, true);
		
		// XXX OJO con las claves aqui:
		tienda.get(buscaPasillo(p_tienda.getCategoria())).eliminarProducto(id);
		almacen.get(Categoria.valueOf(p_almacen.getCategoria())).remove(p_tienda);
		

		//bajo ocupacion:
		int indicePasillo = this.buscaPasillo(p_tienda.getCategoria());
		tienda.get(indicePasillo).cambiarOcupacion(-1);
	}
	
	public void anyadirPedido(Pedido p) { 
		this.pedidos_pendientes.add(p);
		tesoreria.anyadirPedido(p);
		
	}
	public void eliminarPedido(String id) {
		//primero busco y luego elimino: // XXX ya hay un metodo que me permitiría eliminar directamente el Object sin buscar el indice,
										//		si le pasara el objeto en vez del Id
		int indice = buscaPedido(id);
		pedidos_pendientes.remove(indice);
		
	}
	public void anyadirProveedor(Proveedor p) {
		proveedores.add(p);
		
	}
	
	public void anyadirVenta(Venta v) {
		tesoreria.anyadirVenta(v);
	}
	
	public void eliminarProveedor(String id) {
		int indice = buscaProveedor(id);
		proveedores.remove(indice);
		
		
	}
	
	//reviso si hay algï¿½n pasillo sin reponer y mando aviso al primer reponedor disponible en ese pasillo
	public List<Producto> buscarUnidadesaCero(String pasillo) {
		//Reviso tooodas las capacidades y ocupaciones de los pasillos:
		//si hay algï¿½n producto a 0, lo mando reponer hasta cubrir la capacidad del pasillo.

		//hago busqueda del pasillo
		//solo repongo si hay algun producto a 0 en ese pasillo
		
		boolean encontrado = false;
		int i = 0;
		List<Producto> productosACero = new ArrayList<Producto>(); //DevolverÃ© una lista con los productos que
																//tienen cantidad = 0.
		while (!encontrado && i < tienda.size()) {
			if (tienda.get(i).getNombre().equals(pasillo)) {
				encontrado = true;
			} else {
				i++;
			}
		}

		for (int j = 0; j < tienda.get(i).getListaProductos().size(); j++) {
			if (tienda.get(i).getListaProductos().get(j).getUnidades() == 0) {
				productosACero.add(tienda.get(i).getListaProductos().get(j));
			}
		}
		return productosACero;
	}


	public List<Pedido> getListaPedidos() {
		// TODO Auto-generated method stub
		return this.pedidos_pendientes;
	}


	public GestionTrabajadores getTrabajadores() {
		// TODO Auto-generated method stub
		return this.trabajadores;
	}
}

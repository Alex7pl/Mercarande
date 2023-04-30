package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import resources.Pair;

public class Supermercado {

	//ATRIBUTOS:
	private Tesoreria tesoreria;
	
	private HashMap<Categoria, List<Producto>> almacen;
	
	private List<Pasillo> tienda;
	
	private GestionTrabajadores trabajadores;
	
	private List<Pedido> pedidos;
	
	private List<Proveedor> proveedores;
		
	
	//Mï¿½TODOS
	public Supermercado() {}
	
	
	public void cargarDatos(Scanner scanner) {
		
		while (scanner.hasNextLine()) {
			
			String linea = scanner.nextLine();
			String[] datos;
			
			if(linea == "TESORERIA") {
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				this.tesoreria = new Tesoreria();
				
				while(datos[0] != ".") {
					
					String ID = datos[0];
					String IDCajero = datos[1];
					float importe = Float.parseFloat(datos[2]);
					LocalDate fecha = LocalDate.parse(datos[3]);
					List<Pair<String, Integer>> productos = new ArrayList<Pair<String, Integer>>();
					
					linea = scanner.nextLine();
					datos = linea.split(";");
					
					while(datos[0] != ".") {
						
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
				
				while(datos[0] != ".") {
					String IDPedido = datos[0];
					String proveedor = datos[1];
					Categoria categoria = Categoria.valueOf(datos[2]);
					int precioPedido = Integer.parseInt(datos[3]);
					LocalDate fechaPedido = LocalDate.parse(datos[4]);
					List<Pair<String, Integer>> productos = new ArrayList<Pair<String, Integer>>();
					
					linea = scanner.nextLine();
					datos = linea.split(";");
					
					while(datos[0] != ".") {
						
						productos.add(new Pair<String, Integer>(datos[0], Integer.parseInt(datos[1])));
						linea = scanner.nextLine();
						datos = linea.split(";");
					}
					
					this.tesoreria.anyadirPedido(new Pedido(IDPedido, proveedor, categoria, precioPedido, fechaPedido, productos));
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
			}
			else if(linea == "PASILLOS") {
				
				this.tienda = new ArrayList<Pasillo>();
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(datos[0] != ".") {
					
					Categoria Nombre = Categoria.valueOf(datos[0]);
					boolean limpio = Boolean.parseBoolean(datos[1]);
					int capacidad = Integer.parseInt(datos[2]);
					int ocupacion = Integer.parseInt(datos[3]);
					
					this.tienda.add(new Pasillo(Nombre, limpio, capacidad, ocupacion));
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
			}
			else if(linea == "PRODUCTOS") {
				
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
				
				while(datos[0] != ".") {
					
					String ID = datos[0];
					String nombre = datos[1];
					String IDproveedor = datos[2];
					String marca = datos[3];
					Categoria categoria = Categoria.valueOf(datos[4]);;
					float precio = Float.parseFloat(datos[5]);
					int UA = Integer.parseInt(datos[6]);
					int UT = Integer.parseInt(datos[7]);
					
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
					else if(categoria.equals(Categoria.HOJAR)) {
						
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
				this.almacen.put(Categoria.HOJAR, HOA);
				
				
				this.tienda.get(0).anyadirLista(FVT);
				this.tienda.get(1).anyadirLista(CAT);
				this.tienda.get(2).anyadirLista(PET);
				this.tienda.get(3).anyadirLista(COT);
				this.tienda.get(4).anyadirLista(BET);
				this.tienda.get(5).anyadirLista(HOT);
				
			}
			else if(linea == "TRABAJADORES") {
				
				this.trabajadores = new GestionTrabajadores();
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(datos[0] != ".") {
					
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
			else if(linea == "PEDIDOS") {
				
				this.pedidos = new ArrayList<Pedido>();
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(datos[0] != ".") {
					String IDPedido = datos[0];
					String proveedor = datos[1];
					Categoria categoria = Categoria.valueOf(datos[2]);
					int precioPedido = Integer.parseInt(datos[3]);
					LocalDate fechaPedido = LocalDate.parse(datos[4]);
					List<Pair<String, Integer>> productos = new ArrayList<Pair<String, Integer>>();
					
					linea = scanner.nextLine();
					datos = linea.split(";");
					
					while(datos[0] != ".") {
						
						productos.add(new Pair<String, Integer>(datos[0], Integer.parseInt(datos[1])));
						linea = scanner.nextLine();
						datos = linea.split(";");
					}
					
					this.pedidos.add(new Pedido(IDPedido, proveedor, categoria, precioPedido, fechaPedido, productos));
					linea = scanner.nextLine();
					datos = linea.split(";");
				}
			}
			else if(linea == "PROVEEDORES") {
				
				this.proveedores = new ArrayList<Proveedor>();
				linea = scanner.nextLine();
				datos = linea.split(";");
				
				while(datos[0] != ".") {
					
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
					
					while(datos[0] != ".") {
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
	
	public void guardarDatos(BufferedWriter writer) {
		
	}
	
	//Se necesita la lista de pasillos para cogerla en la clase Reponedor y poder reponer
	public List<Pasillo> getListaPasillos(){
		return tienda; 
	}
	
	public Producto buscarConCategoria(String id, String categoria, boolean enTienda) {
		return null;
		
	}
	
	public Producto buscarSinCategoria(String id, boolean enTienda) {
		return null;
		
	}
		
	public void crearProducto(Producto p){
		//se crea tanto en tienda como en almacen
		//añadir el producto tanto en almacen como en la tienda en su cat correspondiente
	}
	
	public void eliminarProducto(String Id) {
		//se elimina tanto en tienda como en almacen
	}
	
	public void anyadirPedido(Pedido p) { 
		
	}
	public void eliminarPedido(String Id) {
		
	}
	public void anyadirProveedor(Proveedor p) {
		
	}
	public void eliminarProveedor(String Id) {
		
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
		return this.pedidos;
	}
}

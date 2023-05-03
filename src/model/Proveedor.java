package model;

import java.util.ArrayList;
import java.util.List;

public class Proveedor {

	//ATRIBUTOS:
	private String ID;
	
	private String NIF;
	
	private String nombre;
	
	private String domicilioFiscal;
	
	private String email;
	
	private int telefono;
	
	private List<String> productos;
	
	private Categoria categoria;
	
	// private int puntuacion; //cada pedido recibido bien +5 puntos
							// cada pedido recinido con productosd restants +3 puntos
	

	
	//M TODOS:

	public Proveedor(
			String iD, String nIF, String nombre, String domicilioFiscal, String email, int telefono,
			Categoria categoria, List<String> productos) {
		this.ID = iD;
		this.NIF = nIF;
		this.nombre = nombre;
		this.domicilioFiscal = domicilioFiscal;
		this.email = email;
		this.telefono = telefono;
		this.productos = productos;
		this.categoria = categoria;
	}

public void setNIF(String NIF) {
	this.NIF=NIF;
}
	public String getID() {
		return ID;
	}

	public String getNIF() {
		return NIF;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDomicilioFiscal() {
		return domicilioFiscal;
	}


	public void setDomicilioFiscal(String domicilioFiscal) {
		this.domicilioFiscal = domicilioFiscal;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public List<String> getProductos() {
		return productos;
	}

	public String getCategoria() {
		return categoria.toString();
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void anyadirProducto(String idP) {
		this.productos.add(idP);


	}
	public void eliminarProducto(String idP) {
		int index = 0;
		boolean encontrado = false;
		while(!encontrado && index < productos.size()){
			if(productos.get(index) ==  idP){
				encontrado = true;
			}
			else{
				index++;
			}
		}
		if(encontrado){
			productos.remove(index);
		}
		else{
			throw new IllegalArgumentException("el proveedor no tiene este prodcuto");
		}
		//buscar el indice 
		//y eliminar el producto en cuestion
		
	}
	
}


package model;

public class Producto {
	
	//ATRIBUTOS:
	
	private String ID;
	
	private String nombre;
	
	private String IDproveedor;
	
	private String marca;
	
	private Categoria categoria;
	
	private float precio;
	
	private int unidades;

	
	//M�TODOS:
	
	//Getters y setters:
	
	public Producto(String iD, String nombre, String IDproveedor, String marca, Categoria categoria, float precio, int unidades) {
		this.nombre = nombre;
		ID = iD;
		this.IDproveedor = IDproveedor;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.unidades = unidades;
	}
	
	public String getID() {
		return ID;
	}

	public String getProveedor() {
		return IDproveedor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setProveedor(String proveedor) {
		this.IDproveedor = proveedor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCategoria() {
		return categoria.toString();
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	
	
	
	
	
	
	
	
	

}

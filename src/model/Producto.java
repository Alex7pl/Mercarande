package model;

public class Producto {
	
	//ATRIBUTOS:
	
	private String ID;
	
	private String IDproveedor;
	
	private String marca;
	
	private String categoria;
	
	private float precio;
	
	private int unidades;

	
	//MÉTODOS:
	
	//Getters y setters:
	
	public Producto(String iD, String IDproveedor, String marca, String categoria, float precio, int unidades) {
		super();
		ID = iD;
		this.IDproveedor = IDproveedor;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.unidades = unidades;
	}

	public String getProveedor() {
		return IDproveedor;
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
		return categoria;
	}

	public void setCategoria(String categoria) {
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

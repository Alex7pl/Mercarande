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

	
	//MÉTODOS:
		
	public Producto(String iD, String nombre, String IDproveedor, String marca, Categoria categoria, float precio, int unidades) {
		this.nombre = nombre;
		ID = iD;
		this.IDproveedor = IDproveedor;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.unidades = unidades;
	}
	// constructor para copia 
	public Producto(Producto p) {
		this.nombre = p.getID();
		this.ID = p.getID();
		this.IDproveedor = p.getProveedor();
		this.marca = p.getMarca();
		this.categoria = Categoria.valueOf(p.getCategoria());
		this.precio = p.getPrecio();
		this.unidades = p.getUnidades();
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
<<<<<<< HEAD
}
=======
	
}
>>>>>>> baa3ac873bf87dad449408b4ddf397cb1d2b3d3a

package model;

import java.util.List;

import resources.Pair;

public class Limpiador extends Trabajador{

  
  
  
  public void limpiar(Pasillo p){
  
  }

@Override
public void generarPedido(List<Pair<String, Integer>> producto, String proveedor) {
	// TODO Auto-generated method stub
	
}

@Override
public void recepcionarPedido(String idPedido) {
	// TODO Auto-generated method stub
	
}

@Override
public void anyadirProveedor(String NIF, String nombre, String domic, String email, int telefono,
		List<String> productos) {
	// TODO Auto-generated method stub
	
}

@Override
public void eliminarProveedor(String idProveedor) {
	// TODO Auto-generated method stub
	
}

@Override
public void crearProducto(String nombre, String IDproveedor, String marca, String categoria, float precio,
		int unidades) {
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
public void reponerExistencias(List<Pair<String, Integer>> l, String categoria) {
	// TODO Auto-generated method stub
	
}

@Override
public void limpiar(String idPasillo) {
	// TODO Auto-generated method stub
	
}
  
  
}

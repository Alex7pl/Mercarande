package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import control.Controller;
import main.Mercarande;
import resources.Pair;



public class GerenteTest {
	@Test
	void test_1() throws URISyntaxException {
		
		ClassLoader classLoader = GerenteTest.class.getClassLoader();
        File file = new File(classLoader.getResource("model\\BaseDeDatosPruebas.txt").toURI());
		Controller ctrl = new Controller(file);
		try {
			ctrl.cargarDatos();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		Trabajador gerente = ctrl.getTrabajadorConDNI("12345746A");
		
		
		gerente.crearProducto("PER2", "Pera", "49150771H", "Test", "FRUTA_VERDURA", 2, 39);
		
		//miramos si el tamaño de la tienda ha aumentado en 1, en la BD tenemos que
		//en el pasillo 0 de la tienda hay 2 productos y comprobamos si ahora hay tres
		assertEquals(ctrl.getTienda().get(0).getListaProductos().size(),3);
		
		//Miramos si elimina bien un producto
	gerente.eliminarProducto("PER2");
	assertEquals(ctrl.getTienda().get(0).getListaProductos().size(),2);

	List<String> lista = new ArrayList<>();

    // Agregar valores a la lista
    lista.add("per");
    lista.add("man");
    lista.add("w");
    String[] listaArray = lista.toArray(new String[0]);

    //Creo un proveedor y miro si aumenta en una unidad la lista de proveedores
	gerente.anyadirProveedor("12354G", "PROVEEDOR SA", "PLAZA MAYOR","email@email.com", 5556664, Categoria.valueOf("FRUTA_VERDURA"),listaArray);
	assertEquals(ctrl.getProveedores().size(),5);
	
	//elimino uno de los proveedores que tengo en mi Base de Datos y compruebo que 
	//la lista de proveedores se reduce en 1
	gerente.eliminarProveedor("2345671H");
	assertEquals(ctrl.getProveedores().size(),4);
	
	
	List<Pair<String,Integer>>pares = new ArrayList();
	Pair p = new Pair("MAN1", 5);
	pares.add(p);
	Pair pa = new Pair("PLA", 6);
	pares.add(pa);
	
	gerente.generarPedido(pares,"AsociadosSL",Categoria.valueOf("FRUTA_VERDURA"));
	assertEquals(ctrl.getListaPedidos().size(),5);


		
	}
}

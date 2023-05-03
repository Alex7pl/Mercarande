package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import control.Controller;
import resources.Pair;

public class CajeroTest {
	
	@Test
	void test_1() throws URISyntaxException {
		
		ClassLoader classLoader = ReponedorTest.class.getClassLoader();
        File file = new File(classLoader.getResource("model\\BaseDeDatosPruebas.txt").toURI());
		Controller ctrl = new Controller(file);
		try {
			ctrl.cargarDatos();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		List<Pair<String, Integer>> productos = new ArrayList<>();
		
		productos.add(new Pair<>("POL1", 5));
		productos.add(new Pair<>("MAN1", 10));
		
		ctrl.crearVenta(productos, "49150121H");
		
		assertEquals(ctrl.getVentas().size(), 6);
		
		assertEquals(ctrl.getUnidadesProducto("POL1", "CARNICERIA", true), 5);
		assertEquals(ctrl.getUnidadesProducto("MAN1", "FRUTA_VERDURA", true), 29);
		
		assertEquals(ctrl.getVentas().get(5).getIDCajero(), "49150121H");
	}
}

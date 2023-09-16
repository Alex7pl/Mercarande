package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import control.Controller;


public class LimpiadorTest {
	
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
		
		ctrl.cambiarPasilloASucio("HOGAR");
		assertEquals(ctrl.getTienda().get(5).isLimpio(), false);
		assertEquals(ctrl.getTienda().get(0).isLimpio(), false);
		assertEquals(ctrl.getTienda().get(2).isLimpio(), false);
		
		ctrl.limpiarPasillo("FRUTA_VERDURA");
		ctrl.limpiarPasillo("PESCADERIA");
		ctrl.limpiarPasillo("HOGAR");
		
		assertEquals(ctrl.getTienda().get(0).isLimpio(), true);
		assertEquals(ctrl.getTienda().get(2).isLimpio(), true);
		assertEquals(ctrl.getTienda().get(5).isLimpio(), true);
	}
}

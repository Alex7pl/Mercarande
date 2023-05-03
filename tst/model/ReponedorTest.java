package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;


import org.junit.jupiter.api.Test;

import control.Controller;
import main.Mercarande;



public class ReponedorTest {
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
		
		ctrl.recepcionarPedido("23456789P", "213TAUIE", "FRUTA_VERDURA");
		
		assertEquals(ctrl.getListaPedidos().size(), 3);
		
		assertEquals(ctrl.getUnidadesProducto("MAN1", "FRUTA_VERDURA", false), (54 + 23));
	}
}

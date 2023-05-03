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
		
		ClassLoader classLoader = Mercarande.class.getClassLoader();
		Controller ctrl = new Controller(new File(classLoader.getResource("resources\\BaseDeDatos.txt").toURI()));
		try {
			ctrl.cargarDatos();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		ctrl.recepcionarPedido("23456789P", "213TAUIE", "FRUTA_VERDURA");
		
		assertEquals(ctrl.getListaPedidos().size(), 3);
	}
}

package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;


import org.junit.jupiter.api.Test;

import control.Controller;



public class ReponedorTest {
	@Test
	void test_1() {
		
		Controller ctrl = new Controller("C:\\Users\\alexs\\eclipse-workspace\\Mercarande\\src\\resources\\BaseDeDatos.txt");
		try {
			ctrl.cargarDatos();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		ctrl.recepcionarPedido("23456789P", "213TAUIE", "FRUTA_VERDURA");
		
		assertEquals(ctrl.getListaPedidos().size(), 3);
	}
}

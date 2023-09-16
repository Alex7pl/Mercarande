package model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.File;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import control.Controller;
import resources.Pair;



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
		assertEquals(ctrl.getUnidadesProducto("PLA3", "FRUTA_VERDURA", false), (27 + 12));
	}
	
	@Test
	void test_2() throws URISyntaxException {
		
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
		
		ctrl.reponerExistencias("23456789P", productos, "CARNICERIA");
		
		assertEquals(ctrl.getUnidadesProducto("POL1", "CARNICERIA", false), 25);
		assertEquals(ctrl.getUnidadesProducto("POL1", "CARNICERIA", true), 15);
	}
}

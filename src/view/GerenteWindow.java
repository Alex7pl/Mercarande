package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Controller;
import model.Trabajador;

public class GerenteWindow extends JPanel {
	private static final long serialVersionUID = 1L;
	private String dni;

	public GerenteWindow(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
		this.dni = dniTrabajador;
		setLayout(new BorderLayout());

		Trabajador trabajador = ctrl.getTrabajadorConDNI(dni);
		TopPanel topPanel = new TopPanel(trabajador, ctrl, cardLayout, mainPanel);
		add(topPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JButton newProveedorButton = new JButton("Añadir Proveedor");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		buttonPanel.add(newProveedorButton, gbc);

		JButton deleteProveedorButton = new JButton("Eliminar Proveedor");
		gbc.gridx = 0;
		gbc.gridy = 1;
		buttonPanel.add(deleteProveedorButton, gbc);

		JButton generarPedidoButton = new JButton("Generar Pedido");
		gbc.gridx = 0;
		gbc.gridy = 2;
		buttonPanel.add(generarPedidoButton, gbc);

		JButton anyadirProductButton = new JButton("Añadir Producto");
		gbc.gridx = 0;
		gbc.gridy = 3;
		buttonPanel.add(anyadirProductButton, gbc);

		JButton modificarProveedorButton = new JButton("Modificar Proveedor");
		gbc.gridx = 0;
		gbc.gridy = 4;
		buttonPanel.add(modificarProveedorButton, gbc);

		JButton deleteProductButton = new JButton("Eliminar Producto");
		gbc.gridx = 2;
		gbc.gridy = 0;
		buttonPanel.add(deleteProductButton, gbc);

		JButton showPedidosButton = new JButton("Mostrar Pedidos");
		gbc.gridx = 2;
		gbc.gridy = 1;
		buttonPanel.add(showPedidosButton, gbc);

		JButton showProductosButton = new JButton("Mostrar Productos");
		gbc.gridx = 2;
		gbc.gridy = 2;
		buttonPanel.add(showProductosButton, gbc);

		JButton showProveedoresButton = new JButton("Mostrar Proveedores");
		gbc.gridx = 2;
		gbc.gridy = 3;
		buttonPanel.add(showProveedoresButton, gbc);

		JButton modifyProdButton = new JButton("Modificar Producto");
		gbc.gridx = 2;
		gbc.gridy = 4;
		buttonPanel.add(modifyProdButton, gbc);

		add(buttonPanel, BorderLayout.CENTER);

		showProveedoresButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteMostrarProveedores mostrarProve = new GerenteMostrarProveedores(dni, ctrl, cardLayout,
						mainPanel);
				mainPanel.add(mostrarProve, "GerenteProveedores");
				cardLayout.show(mainPanel, "GerenteProveedores");
			}
		});

		showPedidosButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteMostrarPedidos mostrarPedidos = new GerenteMostrarPedidos(dni, ctrl, cardLayout, mainPanel);
				mainPanel.add(mostrarPedidos, "GerentePedidos");
				cardLayout.show(mainPanel, "GerentePedidos");
			}
		});

		newProveedorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteNewProveedor generarProv = new GerenteNewProveedor(dni, ctrl, cardLayout, mainPanel);
				mainPanel.add(generarProv, "GenerarProveedor");
				cardLayout.show(mainPanel, "GenerarProveedor");
			}
		});

		deleteProveedorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteDeleteProveedor generarProv = new GerenteDeleteProveedor(dni, ctrl, cardLayout, mainPanel);
				mainPanel.add(generarProv, "GenerarProveedor");
				cardLayout.show(mainPanel, "GenerarProveedor");
			}
		});

		showProductosButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteMostrarProductos generarProv = new GerenteMostrarProductos(dni, ctrl, cardLayout, mainPanel);
				mainPanel.add(generarProv, "GenerarProveedor");
				cardLayout.show(mainPanel, "GenerarProveedor");
			}
		});

		anyadirProductButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyadirProducto generarProv = new AnyadirProducto(dni, ctrl, cardLayout, mainPanel);
				mainPanel.add(generarProv, "GenerarProveedor");
				cardLayout.show(mainPanel, "GenerarProveedor");
			}
		});
		deleteProductButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteEliminarProducto generarProv = new GerenteEliminarProducto(dni, ctrl, cardLayout, mainPanel);
				mainPanel.add(generarProv, "GenerarProveedor");
				cardLayout.show(mainPanel, "GenerarProveedor");
			}
		});

		modificarProveedorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteModificarProveedor generarProv = new GerenteModificarProveedor(dni, ctrl, cardLayout, mainPanel);
				mainPanel.add(generarProv, "GenerarProveedor");
				cardLayout.show(mainPanel, "GenerarProveedor");
			}
		});

		generarPedidoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteGenerarPedido generarProv = new GerenteGenerarPedido(dni, ctrl, cardLayout, mainPanel);
				mainPanel.add(generarProv, "GenerarProveedor");
				cardLayout.show(mainPanel, "GenerarProveedor");
			}
		});

		modifyProdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GerenteModificarProducto generarProv = new GerenteModificarProducto(dni, ctrl, cardLayout, mainPanel);
				mainPanel.add(generarProv, "GenerarProveedor");
				cardLayout.show(mainPanel, "GenerarProveedor");
			}
		});

		// Agregar etiquetas para cada columna
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(new JLabel("Proveedores"), gbc);

		gbc.gridx = 1;
		buttonPanel.add(new JLabel("Productos"), gbc);

		gbc.gridx = 2;
		buttonPanel.add(new JLabel("Pedidos"), gbc);

		// Columna 1: Proveedores
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(newProveedorButton, gbc);

        gbc.gridy++;
        buttonPanel.add(deleteProveedorButton, gbc);

        gbc.gridy++;
        buttonPanel.add(modificarProveedorButton, gbc);

        gbc.gridy++;
        buttonPanel.add(showProveedoresButton, gbc);

        // Columna 2: Productos
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(anyadirProductButton, gbc);

        gbc.gridy++;
        buttonPanel.add(deleteProductButton, gbc);

        gbc.gridy++;
        buttonPanel.add(modifyProdButton, gbc);

        gbc.gridy++;
        buttonPanel.add(showProductosButton, gbc);

        // Columna 3: Pedidos
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonPanel.add(generarPedidoButton, gbc);

        gbc.gridy++;
        buttonPanel.add(showPedidosButton, gbc);

	}
}
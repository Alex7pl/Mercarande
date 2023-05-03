package view;

import control.Controller;
import model.Categoria;
import model.Producto;
import model.Proveedor;
import resources.Pair;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GerenteGenerarPedido extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private JComboBox<String> proveedoresComboBox;
	private JComboBox<String> prod;
	private JSpinner cantidad;
	private List<Pair<String, Integer>> productos;
	private JButton anadir;
	private JButton generar;

	private JTable productosPedidoTable;
	private DefaultTableModel productosPedidoTableModel;

	public GerenteGenerarPedido(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
		this.ctrl = ctrl;
		setLayout(new BorderLayout());
		productos = new ArrayList<>();

		TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
		add(topPanel, BorderLayout.NORTH);

		List<Proveedor> proveedores = ctrl.getProveedores();

		String[] columnNames = { "ID", "NIF", "Nombre", "Domicilio Fiscal", "Email", "Telefono", "Productos",
				"Categoria" };
		Object[][] rowData = new Object[proveedores.size()][8];
		proveedoresComboBox = new JComboBox<>();
		prod = new JComboBox<>();
		cantidad = new JSpinner();

		for (int i = 0; i < proveedores.size(); i++) {
			Proveedor proveedor = proveedores.get(i);
			proveedoresComboBox.addItem(proveedor.getNombre());
			rowData[i][0] = proveedor.getID();
			rowData[i][1] = proveedor.getNIF();
			rowData[i][2] = proveedor.getNombre();
			rowData[i][3] = proveedor.getDomicilioFiscal();
			rowData[i][4] = proveedor.getEmail();
			rowData[i][5] = proveedor.getTelefono();
			rowData[i][6] = proveedor.getProductos();
			rowData[i][7] = proveedor.getCategoria();
		}

		proveedoresComboBox.addActionListener(e -> {
			actualizarProductosCombo();
		});

		// Panel de información de proveedores y sus productos
		JPanel proveedoresPanel = new JPanel();
		proveedoresPanel.setLayout(new BorderLayout());
		JTable table = new JTable(rowData, columnNames);
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		proveedoresPanel.add(scrollPane, BorderLayout.CENTER);

		// Panel de productos y cantidades añadidas al pedido
		JPanel pedidoPanel = new JPanel();
		pedidoPanel.setLayout(new BorderLayout());
		String[] pedidoColumnNames = { "Producto", "Cantidad" };
		productosPedidoTableModel = new DefaultTableModel(null, pedidoColumnNames);
		productosPedidoTable = new JTable(productosPedidoTableModel);
		JScrollPane productosPedidoScrollPane = new JScrollPane(productosPedidoTable);
		pedidoPanel.add(productosPedidoScrollPane, BorderLayout.CENTER);

		// Crear un JSplitPane y agregar los paneles
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, proveedoresPanel, pedidoPanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.8); // El 80% del espacio se asigna al panel izquierdo (tabla de información de
										// proveedores y sus productos)

		this.add(splitPane, BorderLayout.CENTER);

		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		formPanel.add(proveedoresComboBox, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		formPanel.add(prod, gridBagConstraints);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		formPanel.add(cantidad, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		anadir = new JButton("Añadir a pedido");
		formPanel.add(anadir, gridBagConstraints);

		anadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String produ = ctrl.getIDProductoConNombre(prod.getSelectedItem().toString());
				int c = (int) cantidad.getValue();
				Pair<String, Integer> p = new Pair<>(produ, c);
				productos.add(p);
				productosPedidoTableModel.addRow(new Object[] { produ, c });
			}
		});

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		generar = new JButton("Generar Pedido");
		formPanel.add(generar, gridBagConstraints);

		generar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String p = proveedoresComboBox.getSelectedItem().toString();
				Proveedor pro = ctrl.getProveedorID(p);
				Categoria ca = Categoria.valueOf(pro.getCategoria());
				ctrl.getTrabajadorConDNI(dniTrabajador).generarPedido(productos, p, ca);
				JOptionPane.showMessageDialog(GerenteGenerarPedido.this, "Pedido generado exitosamente", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		/*centerPanel.*/add(formPanel, BorderLayout.SOUTH);
		//this.add(centerPanel, BorderLayout.CENTER);
	}

	private void actualizarProductosCombo() {
		String proveedorSeleccionado = (String) proveedoresComboBox.getSelectedItem();

		Proveedor proveedor = ctrl.getProveedorID(proveedorSeleccionado);

		List<Producto> enTienda = ctrl.getTienda().get(ctrl.getIndice(proveedor.getCategoria())).getListaProductos();

		prod.removeAllItems();

		for (int i = 0; i < enTienda.size(); i++) {
			prod.addItem(enTienda.get(i).getNombre());
		}
	}
}

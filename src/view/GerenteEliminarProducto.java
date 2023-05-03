
package view;

import control.Controller;
import model.Pasillo;
import model.Producto;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GerenteEliminarProducto extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<Pasillo> tienda;
	private List<Producto> productos;
	private JComboBox<Object> productosBox;

	public GerenteEliminarProducto(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
		setLayout(new BorderLayout());

		TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
		add(topPanel, BorderLayout.NORTH);

		tienda = ctrl.getTienda();
		productos = tienda.get(0).getListaProductos();
		int tamano = 0;

		String[] columnNames = { "ID", "Nombre", "IDproveedor", "Marca", "Categoria", "Precio", "Unidades" };
		for (int k = 0; k < tienda.size(); k++) {
			tamano = tamano + tienda.get(k).getListaProductos().size();
		}
		Object[][] rowData = new Object[tamano][7];

		int in = 0;
		List<Producto> productosLista = new ArrayList<Producto>();
		for (int j = 0; j < tienda.size(); j++) {
			productos = tienda.get(j).getListaProductos();
			for (int i = 0; i < productos.size(); i++) {
				Producto p = productos.get(i);
				productosLista.add(p);
				rowData[in][0] = p.getID();
				rowData[in][1] = p.getNombre();
				rowData[in][2] = p.getProveedor();
				rowData[in][3] = p.getMarca();
				rowData[in][4] = p.getCategoria();
				rowData[in][5] = p.getPrecio();
				rowData[in][6] = p.getUnidades();
				in++;
			}
		}

		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		JTable table = new JTable(rowData, columnNames);
		table.setEnabled(false);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		JScrollPane scrollPane = new JScrollPane(table);
		formPanel.add(scrollPane, gridBagConstraints);

		productosBox = new JComboBox<>();

		for (Producto p : productosLista) {
			productosBox.addItem(p.getNombre());
		}

		JButton delete = new JButton("Eliminar");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.getTrabajadorConDNI(dniTrabajador).eliminarProducto(ctrl.getIDProductoConNombre(productosBox.getSelectedItem().toString()));
				JOptionPane.showMessageDialog(GerenteEliminarProducto.this, "Producto eliminado con exito",
						"Confirmación", JOptionPane.INFORMATION_MESSAGE);
				cardLayout.show(mainPanel, "GerenteWindow");
			}
		});
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		formPanel.add(productosBox, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;

		formPanel.add(delete, gridBagConstraints);
		add(formPanel, BorderLayout.CENTER);

	}

}

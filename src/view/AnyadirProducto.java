package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.Controller;
import model.Producto;
import model.Proveedor;

public class AnyadirProducto extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private JComboBox<String> proveedoresComboBox;
	private JComboBox<String> prod;
	private JTextField nombre;
	private JTextField marca;
	private JTextField precio;

	public AnyadirProducto(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
		this.ctrl = ctrl;
		setLayout(new BorderLayout());

		TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
		add(topPanel, BorderLayout.NORTH);
		proveedoresComboBox = new JComboBox<>();
		prod = new JComboBox<>();

		List<Proveedor> proveedores = ctrl.getProveedores();
		for (Proveedor proveedor : proveedores) {
			proveedoresComboBox.addItem(proveedor.getNombre());
		}

		proveedoresComboBox.addActionListener(e -> {
			actualizarProductosCombo();
		});

		JButton crear = new JButton("Crear");
		crear.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {

		        String nom = nombre.getText();
		        String mar = marca.getText();
		        String pr = precio.getText();

		        if (nom.isEmpty() || mar.isEmpty() || pr.isEmpty()) {
		            JOptionPane.showMessageDialog(AnyadirProducto.this,
		                    "Por favor, complete todos los campos.", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        String proveedorSeleccionado = (String) proveedoresComboBox.getSelectedItem();
		        Proveedor proveedor = ctrl.getProveedorID(proveedorSeleccionado);

		        if (ctrl.pasilloOcupado(proveedor.getCategoria())) {
		            JOptionPane.showMessageDialog(AnyadirProducto.this,
		                    "Pasillo con máxima ocupación, elimine un producto para añadir este", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        } else {
		            float precioFloat = Float.parseFloat(pr);

		            ctrl.getTrabajadorConDNI(dniTrabajador).crearProducto(prod.getSelectedItem().toString(), nom,
		                    proveedor.getNombre(), mar, proveedor.getCategoria(), precioFloat, 0);
		            JOptionPane.showMessageDialog(AnyadirProducto.this, "Producto añadido con éxito", "Confirmación",
		                    JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});


		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		formPanel.add(proveedoresComboBox, gridBagConstraints);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		formPanel.add(prod, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		formPanel.add(new JLabel("Nombre:"), gridBagConstraints);

		nombre = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(nombre, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		formPanel.add(new JLabel("Marca:"), gridBagConstraints);

		marca = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(marca, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		formPanel.add(new JLabel("Precio:"), gridBagConstraints);

		precio = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(precio, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 10;

		formPanel.add(crear, gridBagConstraints);
		this.add(formPanel);

	}

	private void actualizarProductosCombo() {
		// Obtener el proveedor seleccionado
		String proveedorSeleccionado = (String) proveedoresComboBox.getSelectedItem();
		Proveedor proveedor = ctrl.getProveedorID(proveedorSeleccionado);

		// Obtener los productos del proveedor seleccionado y añadirlos al JComboBox de
		// productos
		List<String> productos = proveedor.getProductos();
		List<Producto> enTienda = ctrl.getTienda().get(ctrl.getIndice(proveedor.getCategoria())).getListaProductos();
		// me quito de productos los que ya estan en la tienda
		for (int i = 0; i < productos.size(); i++) {
			for (int j = 0; j < enTienda.size(); j++) {
				if (productos.get(i).equals(enTienda.get(j).getID())) {
					productos.remove(i);
				}
			}
		}
		prod.removeAllItems();

		for (int i = 0; i < productos.size(); i++) {
			prod.addItem(productos.get(i));
		}
	}

}

package view;

import control.Controller;
import model.Categoria;
import model.Producto;
import model.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GerenteModificarProducto extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	@SuppressWarnings("unused")
	private CardLayout cardLayout;
	@SuppressWarnings("unused")
	private JPanel mainPanel;
	private JComboBox<String> productosComboBox;
	private Producto productosSeleccionado;
	private JTextField nombreTextField;
	private JTextField IDTextField;
	private JTextField IDProveedorTextField;
	private JTextField marcaTextField;
	private JTextField precioTextField;
	@SuppressWarnings("unused")
	private JTextField NIFTextField;
	private JTextField categoriaTextField;
	private JTextField unidadesTextField;

	public GerenteModificarProducto(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
		this.ctrl = ctrl;
		this.cardLayout = cardLayout;
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());

		// TopPanel
		TopPanel topPanel = new TopPanel(ctrl.getTrabajadorConDNI(dniTrabajador), ctrl, cardLayout, mainPanel);
		add(topPanel, BorderLayout.NORTH);

		// Formulario
		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		formPanel.add(new JLabel("Seleccione Producto:"), gridBagConstraints);

		List<Producto> productos = listaProductosActualizada();
		String[] productosArray = new String[productos.size()];
		for (int i = 0; i < productos.size(); i++) {
			productosArray[i] = productos.get(i).getNombre() + " - " + productos.get(i).getID()+"-"+productos.get(i).getCategoria(); 
		}
		productosComboBox = new JComboBox<>(productosArray);
		productosComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = productosComboBox.getSelectedIndex();
				if (selectedIndex >= 0 && selectedIndex < productos.size()) {
					productosSeleccionado = productos.get(selectedIndex);
				}
				// Actualizar el formulario con los datos del trabajador seleccionado
				actualizarFormulario();
			}
		});

		gridBagConstraints.gridx = 1;
		formPanel.add(productosComboBox, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
	
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		formPanel.add(new JLabel("Nombre:"), gridBagConstraints);

		nombreTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(nombreTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		formPanel.add(new JLabel("ID:"), gridBagConstraints);

		IDTextField = new JTextField(30);
		IDTextField.setEditable(false);
		gridBagConstraints.gridx = 1;
		formPanel.add(IDTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		formPanel.add(new JLabel("Categoria:"), gridBagConstraints);

		categoriaTextField = new JTextField(30);
		categoriaTextField.setEditable(false);
		gridBagConstraints.gridx = 1;
		formPanel.add(categoriaTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		formPanel.add(new JLabel("ID Proveedor:"), gridBagConstraints);

		IDProveedorTextField = new JTextField(30);
		IDProveedorTextField.setEditable(false);
		gridBagConstraints.gridx = 1;
		formPanel.add(IDProveedorTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		formPanel.add(new JLabel("Unidades:"), gridBagConstraints);

		unidadesTextField = new JTextField(30);
		unidadesTextField.setEditable(false);
		gridBagConstraints.gridx = 1;
		formPanel.add(unidadesTextField, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		formPanel.add(new JLabel("Precio:"), gridBagConstraints);

		precioTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(precioTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		formPanel.add(new JLabel("Marca:"), gridBagConstraints);

		marcaTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(marcaTextField, gridBagConstraints);
		
		add(formPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JButton modifyButton = new JButton("Modificar");
		modifyButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (productosSeleccionado != null) {
		            // Leer los datos del formulario
		           // String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();
		            String nombre = nombreTextField.getText();
		            String id = IDTextField.getText();
		            int unidades = 0;
		            String marca = marcaTextField.getText();
		            float precio = 0;
		            String IDProveedor = IDProveedorTextField.getText();
		            String categoria = categoriaTextField.getText();
		            

		            // Comprobar campos vacíos
		            if (nombre.isEmpty() || id.isEmpty() || IDProveedorTextField.getText().isEmpty()
		                   || unidadesTextField.getText().isEmpty() || precioTextField.getText().isEmpty() || categoriaTextField.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(GerenteModificarProducto.this, "Por favor, complete todos los campos",
		                        "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Validar y convertir los campos numéricos
		            try {
		                unidades = Integer.parseInt(unidadesTextField.getText());
		                precio = Float.parseFloat(precioTextField.getText());
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(GerenteModificarProducto.this,
		                        "Ingrese valores numéricos válidos para Salario, Hora de entrada y Hora de salida",
		                        "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            
		            // Realizar la modificación del trabajador seleccionado
		         ctrl.modificarProducto(id,IDProveedor, nombre,Categoria.valueOf(categoria), marca, precio,unidades);
		            JOptionPane.showMessageDialog(GerenteModificarProducto.this, "Producto modificado", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
		        }}
		});
		buttonPanel.add(modifyButton);


		add(buttonPanel, BorderLayout.SOUTH);
	}

	private List<Producto> listaProductosActualizada() {
		List<Producto>p = new ArrayList<Producto>();
		for (int i=0;i<ctrl.getTienda().size();i++) {
			for (int j=0;j<ctrl.getTienda().get(i).getListaProductos().size();j++) {
				p.add(ctrl.getTienda().get(i).getListaProductos().get(j));
			}
		}
		return p;
	}

	@SuppressWarnings("unused")
	private void actualizarDesplegable() {
		productosComboBox.removeAllItems();
		List<Trabajador> trabajadores = ctrl.getTrabajadores();
		for (Trabajador trabajador : trabajadores) {
			productosComboBox
					.addItem(trabajador.getTipo() + " - " + trabajador.getNombre() + " - " + trabajador.getDNI());
		}
	}
	
	// Método para actualizar el formulario con los datos del trabajador
	// seleccionado
	private void actualizarFormulario() {
	    if (productosSeleccionado != null) {
	        nombreTextField.setText(productosSeleccionado.getNombre());
	        IDTextField.setText(productosSeleccionado.getID());
	        categoriaTextField.setText(productosSeleccionado.getCategoria());
	       precioTextField.setText(Float.toString(productosSeleccionado.getPrecio()));
	        IDProveedorTextField.setText(productosSeleccionado.getProveedor());
	        marcaTextField.setText(productosSeleccionado.getMarca());
	        unidadesTextField.setText(Integer.toString(productosSeleccionado.getUnidades()));
	    } else {
	        nombreTextField.setText("");
	        IDTextField.setText("");
	        categoriaTextField.setText("");
	      //  salarioTextField.setText("");
	        //entradaTextField.setText("");
	     //   DomicilioTextField.setText("");
	    }
	}
}

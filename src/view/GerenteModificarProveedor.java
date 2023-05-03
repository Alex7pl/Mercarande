package view;

import control.Controller;
import model.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GerenteModificarProveedor extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private JComboBox<String> proveedoresComboBox;
	private Proveedor proveedorSeleccionado;
	private JTextField nombreTextField;
	private JTextField IDTextField;
	private JTextField DomicilioTextField;
	private JTextField EmailTextField;
	private JTextField TelefonoTextField;
	private JTextField categoriaTextField;
	private JTextField NIFTextField;

	public GerenteModificarProveedor(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
		this.ctrl = ctrl;
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
		formPanel.add(new JLabel("Seleccione Proveedor:"), gridBagConstraints);

		List<Proveedor> proveedores = listaTrabajadoresActualizada();
		String[] trabajadoresArray = new String[proveedores.size()];
		for (int i = 0; i < proveedores.size(); i++) {
			trabajadoresArray[i] = proveedores.get(i).getNombre() + " - " + proveedores.get(i).getID() + "-"
					+ proveedores.get(i).getCategoria();
		}
		proveedoresComboBox = new JComboBox<>(trabajadoresArray);
		proveedoresComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = proveedoresComboBox.getSelectedIndex();
				if (selectedIndex >= 0 && selectedIndex < proveedores.size()) {
					proveedorSeleccionado = proveedores.get(selectedIndex);
				}
				// Actualizar el formulario con los datos del trabajador seleccionado
				actualizarFormulario();
			}
		});

		gridBagConstraints.gridx = 1;
		formPanel.add(proveedoresComboBox, gridBagConstraints);

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
		formPanel.add(new JLabel("NIF:"), gridBagConstraints);

		NIFTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(NIFTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		formPanel.add(new JLabel("Domicilio:"), gridBagConstraints);

		DomicilioTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(DomicilioTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		formPanel.add(new JLabel("Email:"), gridBagConstraints);

		EmailTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(EmailTextField, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		formPanel.add(new JLabel("Telefono:"), gridBagConstraints);

		TelefonoTextField = new JTextField(30);
		gridBagConstraints.gridx = 1;
		formPanel.add(TelefonoTextField, gridBagConstraints);

		add(formPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JButton modifyButton = new JButton("Modificar");
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (proveedorSeleccionado != null) {
					// Leer los datos del formulario
					// String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();
					String nombre = nombreTextField.getText();
					String id = IDTextField.getText();
					int telefono = 0;
					String email;
					String domicilio;
					String NIF;

					// Comprobar campos vacíos
					if (nombre.isEmpty() || id.isEmpty() || TelefonoTextField.getText().isEmpty()
							|| NIFTextField.getText().isEmpty() || DomicilioTextField.getText().isEmpty()
							|| EmailTextField.getText().isEmpty() || categoriaTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(GerenteModificarProveedor.this,
								"Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Validar y convertir los campos numéricos
					try {
						domicilio = DomicilioTextField.getText();
						telefono = Integer.parseInt(TelefonoTextField.getText());
						email = EmailTextField.getText();
						NIF = NIFTextField.getText();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(GerenteModificarProveedor.this,
								"Ingrese valores numéricos válidos para Salario, Hora de entrada y Hora de salida",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Realizar la modificación del trabajador seleccionado
					ctrl.modificarProveedor(id, nombre, NIF, email, domicilio, telefono);
					JOptionPane.showMessageDialog(GerenteModificarProveedor.this, "Proveedor modificado",
							"Confirmación", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		buttonPanel.add(modifyButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	private List<Proveedor> listaTrabajadoresActualizada() {
		return ctrl.getProveedores();
	}

	// Método para actualizar el formulario con los datos del trabajador
	// seleccionado
	private void actualizarFormulario() {
		if (proveedorSeleccionado != null) {
			nombreTextField.setText(proveedorSeleccionado.getNombre());
			IDTextField.setText(proveedorSeleccionado.getID());
			categoriaTextField.setText(proveedorSeleccionado.getCategoria().toString()); // Modificado para llamar a
																							// toString()
			NIFTextField.setText(proveedorSeleccionado.getNIF()); // Añadido
			DomicilioTextField.setText(proveedorSeleccionado.getDomicilioFiscal());
			EmailTextField.setText(proveedorSeleccionado.getEmail());
			TelefonoTextField.setText(String.valueOf(proveedorSeleccionado.getTelefono())); // Añadido
		} else {
			nombreTextField.setText("");
			IDTextField.setText("");
			categoriaTextField.setText("");
			NIFTextField.setText(""); // Añadido
			DomicilioTextField.setText("");
			EmailTextField.setText("");
			TelefonoTextField.setText(""); // Añadido
		}
	}

}

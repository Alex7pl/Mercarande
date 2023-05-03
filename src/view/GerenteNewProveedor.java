package view;

import control.Controller;
import model.Categoria;
import model.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenteNewProveedor extends JPanel {
	private static final long serialVersionUID = 1L;

	public GerenteNewProveedor(String dniTrabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        Trabajador trabajador = ctrl.getTrabajadorConDNI(dniTrabajador);
        TopPanel topPanel = new TopPanel(trabajador, ctrl, cardLayout, mainPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);

		String[] categorias = { "FRUTA_VERDURA", "PESCADERIA", "CARNICERIA", "CONGELADOS", "BEBIDAS", "HOGAR" };
		JComboBox<String> categoriasComboBox = new JComboBox<>(categorias);
		formPanel.add(new JLabel("Categoría:"), gbc);
		gbc.gridy++;
		formPanel.add(categoriasComboBox, gbc);
		gbc.gridy++;

		JTextField NIFTextField = new JTextField(15);
		formPanel.add(new JLabel("NIF:"), gbc);
		gbc.gridy++;
		formPanel.add(NIFTextField, gbc);
		gbc.gridy++;

		JTextField nameTextField = new JTextField(15);
		formPanel.add(new JLabel("Nombre:"), gbc);
		gbc.gridy++;
		formPanel.add(nameTextField, gbc);
		gbc.gridy++;

		JTextField domicilioTextField = new JTextField(15);
		formPanel.add(new JLabel("Domicilio Fiscal:"), gbc);
		gbc.gridy++;
		formPanel.add(domicilioTextField, gbc);
		gbc.gridy++;

		JTextField emailTextField = new JTextField(15);
		formPanel.add(new JLabel("Email:"), gbc);
		gbc.gridy++;
		formPanel.add(emailTextField, gbc);
		gbc.gridy++;

		JTextField telefonoTextField = new JTextField(15);
		formPanel.add(new JLabel("Teléfono:"), gbc);
		gbc.gridy++;
		formPanel.add(telefonoTextField, gbc);
		gbc.gridy++;

		JTextField idProductosTextField = new JTextField(15);
		formPanel.add(new JLabel("ID Productos (separados por ';'):"), gbc);
		gbc.gridy++;
		formPanel.add(idProductosTextField, gbc);
		gbc.gridy++;

		add(formPanel, BorderLayout.CENTER);

		JButton addButton = new JButton("Añadir");
		addButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String NIF = NIFTextField.getText();
		        String domicilioFiscal = domicilioTextField.getText();
		        String email = emailTextField.getText();
		        String nombre = nameTextField.getText();
		        String telefonoStr = telefonoTextField.getText();
		        String idProductos = idProductosTextField.getText();

		        if (NIF.isEmpty() || domicilioFiscal.isEmpty() || email.isEmpty() || nombre.isEmpty() || telefonoStr.isEmpty() || idProductos.isEmpty()) {
		            JOptionPane.showMessageDialog(GerenteNewProveedor.this,
		                    "Por favor, complete todos los campos.", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        String categoria = (String) categoriasComboBox.getSelectedItem();
		        int telefono = Integer.parseInt(telefonoStr);
		        String[] productos = idProductos.split(";");

		        ctrl.nuevoProveedor(dniTrabajador, NIF, nombre, domicilioFiscal, email, telefono,
		                Categoria.valueOf(categoria), productos);

		        JOptionPane.showMessageDialog(GerenteNewProveedor.this, "Proveedor añadido con éxito", "Confirmación",
		                JOptionPane.INFORMATION_MESSAGE);
		    }
		});


        formPanel.add(addButton, gbc);

	}
}

package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import control.Controller;
import resources.Pair;

public class CAJCGVVenta extends JPanel{
	
	 private static final long serialVersionUID = 1L;
	    private Controller ctrl;
	    private String DNIT;
	    private CardLayout cardLayout;
	    private JPanel mainPanel;
	    private List<Pair<String, JSpinner>> productosList;
	    private List<String> productos;

	    public CAJCGVVenta(String DNIT, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
	        this.ctrl = ctrl;
	        this.DNIT = DNIT;
	        this.cardLayout = cardLayout;
	        this.mainPanel = mainPanel;
	        this.productosList = new ArrayList<>();

	        initGUI();
	    }

	    private void initGUI() {
	        // Obtener los productos y crear una lista de pares (String, JSpinner) para mostrar cada producto y su cantidad
	        JPanel centerPanel = new JPanel();
	        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        
	     // Crear un JPanel para contener el JLabel y agregarlo al panel central
	        JPanel labelPanel = new JPanel();
	        labelPanel.setAlignmentX(0.5f);
	        centerPanel.add(labelPanel);

	        JLabel userLabel = new JLabel("Selecciona las cantidades de los productos que quieres vender:");
	        labelPanel.add(userLabel);

	        this.getProductos();


	        for (String producto : productos) {
	            JLabel label = new JLabel(producto);
	            label.setFont(label.getFont().deriveFont(18f));
	            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, this.maximoAlmacen(producto), 1));
	            spinner.setFont(spinner.getFont().deriveFont(14f));
	            productosList.add(new Pair<>(producto, spinner));
	            JPanel panel = new JPanel();
	            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	            panel.setBorder(new EmptyBorder(5, 10, 5, 10));
	            panel.add(label);
	            panel.add(Box.createHorizontalStrut(20));
	            panel.add(spinner);
	            panel.setPreferredSize(new Dimension(500, 30));
	            centerPanel.add(panel);
	            centerPanel.add(Box.createVerticalStrut(5));
	        }

	        // Crear el botón Aceptar y agregar un ActionListener para generar la lista de pares (String, Integer)
	        JButton btnAceptar = new JButton("Aceptar");
	        btnAceptar.addActionListener(e -> {
	            List<Pair<String, Integer>> seleccionados = new ArrayList<>();
	            for (Pair<String, JSpinner> p : productosList) {
	                int cantidad = (int) ((JSpinner) p.getSecond()).getValue();
	                if (cantidad > 0) {
	                    seleccionados.add(new Pair<>(p.getFirst(), cantidad));
	                }
	            }
	            
	            String productosSeleccionados = "";
	            for (Pair<String, Integer> p : seleccionados) {
	                productosSeleccionados += "- " + p.getFirst() + " x" + p.getSecond() + "\n";
	            }

	            // Mostrar el diálogo de confirmación con la lista de productos seleccionados
	            int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres hacer esta venta?\n\nProductos seleccionados:\n" + productosSeleccionados,
	                                    "Confirmar Venta", JOptionPane.YES_NO_OPTION);
	            if (opcion == JOptionPane.YES_OPTION) {
	                this.ctrl.crearVenta(seleccionados, DNIT);
	                JOptionPane.showMessageDialog(CAJCGVVenta.this, "Existencias añadidas", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
	            }
	        });

	        // Agregar el botón Aceptar al panel
	        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        bottomPanel.add(btnAceptar);

	        // Agregar 
	        this.setLayout(new BorderLayout());
	        this.add(new TopPanel(ctrl.getTrabajadorConDNI(DNIT), ctrl, cardLayout, mainPanel), BorderLayout.NORTH);
	        this.add(centerPanel, BorderLayout.CENTER);
	        this.add(bottomPanel, BorderLayout.SOUTH);
	    }
	    
	    private void getProductos() {
	    	
	    	this.productos = this.ctrl.getTodosProductos();
	    }
	    
	    private int maximoAlmacen(String ID) {
	    	
	    	return this.ctrl.getUnidadesProductoSin(ID, true);
	    }
}

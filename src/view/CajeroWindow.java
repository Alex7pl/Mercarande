package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.Controller;

public class CajeroWindow  extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private String DNIT;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	    
	public CajeroWindow(String DNIT, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
    	this.ctrl = ctrl;
    	this.DNIT = DNIT;
    	this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        initGUI();
    }
    
    private void initGUI() {
    
		
          add(new TopPanel(ctrl.getTrabajadorConDNI(DNIT), ctrl, cardLayout, mainPanel), BorderLayout.NORTH);
    	  JPanel centerPanel = new JPanel(new GridBagLayout());
          GridBagConstraints gbc = new GridBagConstraints();
          gbc.insets = new Insets(10, 10, 10, 10);

          JButton btnCrear = new JButton("Crear y gestionar venta");
          btnCrear.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  // Lógica para reponer existencias
            	  CAJCGVVenta ventaPanel = new CAJCGVVenta(DNIT, ctrl, cardLayout, mainPanel);
                  mainPanel.add(ventaPanel, "ventaPanel");
                  cardLayout.show(mainPanel, "ventaPanel");
              }
          });
          gbc.gridx = 0;
          gbc.gridy = 0;
          centerPanel.add(btnCrear, gbc);

          JButton btnMostrar = new JButton("Mostrar ventas");
          btnMostrar.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  // Lógica para recepcionar pedido
            	  CAJMostrarVentas ventaPanel = new CAJMostrarVentas(DNIT, ctrl, cardLayout, mainPanel);
                  mainPanel.add(ventaPanel, "ventaPanel");
                  cardLayout.show(mainPanel, "ventaPanel");
              }
          });
          gbc.gridx = 0;
          gbc.gridy = 1;
          centerPanel.add(btnMostrar, gbc);
          
          btnCrear.setPreferredSize(new Dimension(200, 50));
          btnCrear.setFont(new Font("Arial", Font.PLAIN, 15));

          btnMostrar.setPreferredSize(new Dimension(200, 50));
          btnMostrar.setFont(new Font("Arial", Font.PLAIN, 15));
        
        add(centerPanel, BorderLayout.CENTER);
        
		this.setSize(1200, 900);
		this.setVisible(true);
    }
}
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Controller;

public class ReponedorWindow extends JPanel {
    
    private Controller ctrl;
    private String DNIT;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public ReponedorWindow(String DNIT, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
    	this.ctrl = ctrl;
    	this.DNIT = DNIT;
    	this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        initGUI();
    }
    
    private void initGUI() {
    
		
        //mainPanel.add(new ControlPanel(), BorderLayout.NORTH);
    	  JPanel centerPanel = new JPanel(new GridBagLayout());
          GridBagConstraints gbc = new GridBagConstraints();
          gbc.insets = new Insets(10, 10, 10, 10);

          JButton btnReponer = new JButton("Reponer existencias");
          btnReponer.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  // L�gica para reponer existencias
              }
          });
          gbc.gridx = 0;
          gbc.gridy = 0;
          centerPanel.add(btnReponer, gbc);

          JButton btnRecepcionar = new JButton("Recepcionar pedido");
          btnRecepcionar.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  // L�gica para recepcionar pedido
              }
          });
          gbc.gridx = 0;
          gbc.gridy = 1;
          centerPanel.add(btnRecepcionar, gbc);
        
        add(centerPanel, BorderLayout.CENTER);
        
		this.setSize(1200, 900);
		this.setVisible(true);
    }
}
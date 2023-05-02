package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Controller;

public class CajeroWindow  extends JPanel {
	
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
    	
    }
}
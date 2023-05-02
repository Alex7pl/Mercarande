package view;

import control.Controller;
import model.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Controller ctrl;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JTextField userField;
    private JPasswordField passwordField;

    public LoginPanel(Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
        this.ctrl = ctrl;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel userLabel = new JLabel("Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(userLabel, gbc);

        userField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(userField, gbc);

        JLabel passwordLabel = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Iniciar sesión");
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        add(loginButton, gbc);

        // KeyListener para detectar la tecla Enter y ejecutar el botón de inicio de sesión
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    iniciarSesion();
                }
            }
        };

        userField.addKeyListener(keyAdapter);
        passwordField.addKeyListener(keyAdapter);
    }
    
    private void iniciarSesion() {
    	String user = userField.getText();
        String password = new String(passwordField.getPassword());

        if (ctrl.comprobarEsTrabajador(user)) {
            if (ctrl.comprobarCredencialesCorrectas(user, password)) {
                Trabajador trabajador = ctrl.getTrabajadorConUsuario(user);
                String workerType = trabajador.getClass().getSimpleName();

                JPanel workerPanel = null;

                switch (workerType) {
                    case "DirectorRRHH":
                        workerPanel = new DirectorRRHHWindow(trabajador.getDNI(), ctrl, cardLayout, mainPanel);
                        break;
                    case "Gerente":
                        workerPanel = new GerenteWindow(trabajador.getDNI(), ctrl, cardLayout, mainPanel);
                        break;
                    case "Cajero":
                    	workerPanel = new CajeroWindow(trabajador.getDNI(), ctrl, cardLayout, mainPanel);
                        break;
                    case "Reponedor":
                    	workerPanel = new ReponedorWindow(trabajador.getDNI(), ctrl, cardLayout, mainPanel);
                        break;
                    case "Limpiador":
                    	workerPanel = new LimpiadorWindow(trabajador.getDNI(), ctrl, cardLayout, mainPanel);
                        break;
                }

                if (workerPanel != null) {
                    mainPanel.add(workerPanel, workerType + "Window");
                    cardLayout.show(mainPanel, workerType + "Window");
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Error al cargar la ventana del trabajador.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(LoginPanel.this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(LoginPanel.this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
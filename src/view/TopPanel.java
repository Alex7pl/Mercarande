package view;

import control.Controller;
import model.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;

public class TopPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Trabajador trabajador;
	@SuppressWarnings("unused")
	private Controller ctrl;
	@SuppressWarnings("unused")
	private CardLayout cardLayout;
	@SuppressWarnings("unused")
	private JPanel mainPanel;

	public TopPanel(Trabajador trabajador, Controller ctrl, CardLayout cardLayout, JPanel mainPanel) {
		this.trabajador = trabajador;
		this.ctrl = ctrl;
		this.cardLayout = cardLayout;
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());

		JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		try {
			ImageIcon logoIcon = new ImageIcon(ImageIO.read(getClass().getResource("/resources/mercarande_logo.png")));
			Image logoImage = logoIcon.getImage();

			int originalWidth = logoImage.getWidth(null);
			int originalHeight = logoImage.getHeight(null);

			int newWidth = originalWidth / 9;
			int newHeight = originalHeight / 9;

			Image scaledImage = logoImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);

			JLabel logoLabel = new JLabel(scaledIcon);
			logoPanel.add(logoLabel);
		} catch (Exception e) {
			System.err.println("Error al cargar el logotipo: " + e.getMessage());
		}
		add(logoPanel, BorderLayout.NORTH);

		JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel userTypeLabel = new JLabel(trabajador.getClass().getSimpleName());
		userInfoPanel.add(userTypeLabel);

		JLabel nameLabel = new JLabel(trabajador.getNombre());
		userInfoPanel.add(nameLabel);

		JLabel dniLabel = new JLabel(trabajador.getDNI());
		userInfoPanel.add(dniLabel);

		add(userInfoPanel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		try {
			ImageIcon homeIcon = new ImageIcon(ImageIO.read(getClass().getResource("/resources/home_icon.png")));
			Image homeImage = homeIcon.getImage();

			int originalWidth = homeImage.getWidth(null);
			int originalHeight = homeImage.getHeight(null);

			int newWidth = originalWidth / 27;
			int newHeight = originalHeight / 27;

			Image scaledImage = homeImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);

			JButton homeButton = new JButton(scaledIcon);
			homeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String workerType = trabajador.getClass().getSimpleName();
					cardLayout.show(mainPanel, workerType + "Window");
				}
			});
			buttonsPanel.add(homeButton);
		} catch (Exception e) {
			System.err.println("Error al cargar el Icono de inicio: " + e.getMessage());
		}

		if (!trabajador.getTipo().equals("Limpiador")) {
			JButton limpiezaPasillosButton = new JButton("Limpieza Pasillos");
			limpiezaPasillosButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LimpiezaPasillos limpiezaPanel = new LimpiezaPasillos(trabajador.getDNI(), ctrl, cardLayout, mainPanel);
	                mainPanel.add(limpiezaPanel, "limpiezaPanel");
	                cardLayout.show(mainPanel, "limpiezaPanel");
				}
			});

			buttonsPanel.add(limpiezaPasillosButton);
		}

		JButton logOutButton = new JButton("Cerrar sesion");
		logOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ctrl.guardarDatos();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.err.println("Error al guardar los nuevos datos: " + e1.getMessage());
					;
				}
				cardLayout.show(mainPanel, "loginPanel");
			}
		});
		buttonsPanel.add(logOutButton);

		add(buttonsPanel, BorderLayout.SOUTH);

	}
}

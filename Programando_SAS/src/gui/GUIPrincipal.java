package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GUIPrincipal extends JFrame {

	private JPanel contentPane;

	private GUIClientes panel1;

	private GUIProductos panel2;

	private GUIVenta panel3;

	private GUIResumen panel4;

	private JPanel jpVistas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					GUIPrincipal frame = new GUIPrincipal();

					frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIPrincipal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1300, 560);

		setTitle("Programarlo S.A.S");

		setLocationRelativeTo(null);

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);

		JPanel jpPrincipal = new JPanel();

		contentPane.add(jpPrincipal, BorderLayout.CENTER);

		jpPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel jpBotones = new JPanel();

		FlowLayout flowLayout = (FlowLayout) jpBotones.getLayout();

		jpBotones.setBackground(new Color(211, 211, 211));

		jpPrincipal.add(jpBotones, BorderLayout.NORTH);

		JButton btnProductos = new JButton("Productos");

		btnProductos.setBackground(new Color(248, 248, 255));

		jpBotones.add(btnProductos);

		JButton btnClientes = new JButton("Clientes");

		btnClientes.setBackground(new Color(248, 248, 255));

		jpBotones.add(btnClientes);

		JButton btnVentas = new JButton("Ventas");

		btnVentas.setBackground(new Color(248, 248, 255));

		jpBotones.add(btnVentas);

		JButton btnResumen = new JButton("Resumen");
		
		jpBotones.add(btnResumen);

		JPanel jpVistas = new JPanel();

		jpVistas.setBackground(new Color(119, 136, 153));

		jpPrincipal.add(jpVistas, BorderLayout.CENTER);

		jpVistas.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();

		jpVistas.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Bienvenido a Programarlo S.A.S ");

		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));

		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("despl\u00E1cese entre las ventanas con los botones de la parte superior");

		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));

		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		jpVistas.add(lblNewLabel_2, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();

		contentPane.add(panel_2, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("Desarrollado por SERGIO CANTOR VASQUEZ y RODRIGO CANTOR VASQUEZ");

		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		panel_2.add(lblNewLabel_1);

		panel1 = new GUIClientes();

		btnClientes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jpVistas.removeAll();

				jpVistas.add(panel1);

				jpVistas.revalidate();

				jpVistas.repaint();
			}
		});

		panel2 = new GUIProductos();

		btnProductos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jpVistas.removeAll();

				jpVistas.add(panel2);

				jpVistas.revalidate();

				jpVistas.repaint();

			}

		});

		panel3 = new GUIVenta();

		btnVentas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jpVistas.removeAll();

				jpVistas.add(panel3);

				jpVistas.revalidate();

				jpVistas.repaint();

			}
		});

		panel4 = new GUIResumen();

		btnResumen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				jpVistas.removeAll();

				jpVistas.add(panel4);

				jpVistas.revalidate();

				jpVistas.repaint();
				
				panel4.mostrarTablaFacturas();
				
				panel4.mostrarTodosProductosFactura();
				
			}
		});
	}

}

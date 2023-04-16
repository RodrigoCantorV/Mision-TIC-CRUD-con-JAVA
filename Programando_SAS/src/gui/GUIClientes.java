package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import clases.Cliente;
import data_acces_object_DAO.ClientesDAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JSeparator;

public class GUIClientes extends JPanel {
	private JTextField txtCedula;

	private JTextField txtNombre;

	private JTextField txtApelldio;

	private JTextField txtNacionalidad;

	private JTextField txtCorreo;

	private Cliente datosCliente;

	private JTable tblCliente;

	/**
	 * Create the panel.
	 */
	public GUIClientes() {
		setBackground(new Color(255, 255, 255));

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();

		panel.setBackground(new Color(119, 136, 153));

		add(panel, BorderLayout.WEST);

		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();

		panel_1.setForeground(new Color(255, 250, 250));

		panel_1.setBackground(new Color(119, 136, 153));

		panel.add(panel_1, BorderLayout.NORTH);

		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JLabel lblCedula = new JLabel("Cedula:");

		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(lblCedula);

		txtCedula = new JTextField();

		txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(txtCedula);

		txtCedula.setColumns(20);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");

		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(lblNewLabel_1);

		txtNombre = new JTextField();

		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido:");

		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(lblApellido);

		txtApelldio = new JTextField();

		txtApelldio.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(txtApelldio);

		txtApelldio.setColumns(10);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");

		lblNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(lblNacionalidad);

		txtNacionalidad = new JTextField();

		txtNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(txtNacionalidad);

		txtNacionalidad.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo:");

		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(lblCorreo);

		txtCorreo = new JTextField();

		txtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtCorreo.setAlignmentY(Component.BOTTOM_ALIGNMENT);

		panel_1.add(txtCorreo);

		txtCorreo.setColumns(10);

		JSeparator separator = new JSeparator();

		panel_1.add(separator);

		JSeparator separator_1 = new JSeparator();

		panel_1.add(separator_1);

		JButton btnLimpar = new JButton("Limpiar");

		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});

		panel_1.add(btnLimpar);

		JPanel panel_2 = new JPanel();

		panel_2.setBackground(new Color(192, 192, 192));

		add(panel_2, BorderLayout.SOUTH);

		JButton btnInsertar = new JButton("Guardar");

		btnInsertar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (validarCamposVacios() == true) {

					JOptionPane.showInternalMessageDialog(null, "Debe de llenar todos los campos");

				} else {

					obtenerDatos();

					registarCliente();

					mostrarDatosTabla(tblCliente);
				}
			}
		});
		panel_2.add(btnInsertar);

		JButton btnModificar = new JButton("Modificar");

		btnModificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!txtCedula.getText().equalsIgnoreCase("0")) {

					if (validarCamposVacios() == true) {

						JOptionPane.showInternalMessageDialog(null,
								"Debe llenar todos los campos para poder modificar el cliente");

					} else {

						modificarCliente();

						limpiar();

						mostrarDatosTabla(tblCliente);
					}
				} else {

					JOptionPane.showMessageDialog(null, "Este es un cliente universal no se puede modificar");

					limpiar();
				}
			}
		});

		JButton btnBuscar = new JButton("Buscar");

		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (validarCampoCedulaVacio() == true) {

					JOptionPane.showInternalMessageDialog(null,
							"Debe Ingresar el campo Cedula para Buscar el cliente deseado");

				} else {

					buscarCliente();

					mostrarDatosTabla(tblCliente);

				}
			}
		});

		panel_2.add(btnBuscar);

		panel_2.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");

		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!txtCedula.getText().equalsIgnoreCase("0")) {

					if (validarCamposVacios() == true) {

						JOptionPane.showInternalMessageDialog(null,
								"Debe llener todos los campos para eliminar el cliente deseado");

					} else {

						buscarCliente();

						String respuesta = JOptionPane.showInputDialog("Seguro deseas eliminar este registro: SI/NO");

						if (respuesta.equalsIgnoreCase("si")) {

							eliminarCliente();

							limpiar();

							mostrarDatosTabla(tblCliente);
						}
					}

				} else {
					
					JOptionPane.showMessageDialog(null, "Este es un cliente universal no se puede eliminar");

					limpiar();
				}
			}
		});

		panel_2.add(btnEliminar);

		JPanel panel_3 = new JPanel();

		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();

		flowLayout.setHgap(12);

		panel_3.setForeground(new Color(255, 255, 255));

		panel_3.setBackground(new Color(255, 69, 0));

		add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel_10 = new JLabel("CLIENTES");

		lblNewLabel_10.setForeground(new Color(255, 250, 250));

		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 20));

		panel_3.add(lblNewLabel_10);

		tblCliente = new JTable();

		mostrarDatosTabla(tblCliente);

		add(new JScrollPane(tblCliente), BorderLayout.CENTER);

	}

	boolean validarCamposVacios() {

		if (txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApelldio.getText().isEmpty()) {

			return true;

		} else {

			return false;

		}
	}

	boolean validarCampoCedulaVacio() {
		if (txtCedula.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	Cliente obtenerDatos() {

		datosCliente = new Cliente();

		datosCliente.setCedulaCliente(Integer.parseInt(txtCedula.getText()));

		datosCliente.setNombreCliente(txtNombre.getText());

		datosCliente.setApellidoCliente(txtApelldio.getText());

		datosCliente.setNacionalidadCliente(txtNacionalidad.getText());

		datosCliente.setCorreoCliente(txtCorreo.getText());

		return datosCliente;

	}

	void registarCliente() {

		ClientesDAO cliente = new ClientesDAO();

		cliente.crearCliente(obtenerDatos());
	}

	void mostrarDatosTabla(JTable tablaClientes) {

		ClientesDAO listaClientes = new ClientesDAO();

		String titulo[] = { "CEDULA", "NOMBBRE", "APELLIDO", "NACIONALIDAD", "CORREO" };

		DefaultTableModel model = new DefaultTableModel(null, titulo);

		ArrayList<Cliente> listaTablaClientes = listaClientes.listarCliente();

		String[] datosCliente = new String[5];

		for (Cliente tbc : listaTablaClientes) {

			datosCliente[0] = tbc.getCedulaCliente() + "";

			datosCliente[1] = tbc.getNombreCliente() + "";

			datosCliente[2] = tbc.getApellidoCliente() + "";

			datosCliente[3] = tbc.getNacionalidadCliente() + "";

			datosCliente[4] = tbc.getCorreoCliente() + "";

			model.addRow(datosCliente);

		}

		tblCliente.setModel(model);
	}

	void buscarCliente() {

		ClientesDAO clienteDAO = new ClientesDAO();

		Cliente clienteBuscado = new Cliente();

		clienteBuscado.setCedulaCliente(Integer.parseInt(txtCedula.getText()));

		clienteBuscado = clienteDAO.buscarCliente(clienteBuscado);

		txtNombre.setText(clienteBuscado.getNombreCliente());

		txtApelldio.setText(clienteBuscado.getApellidoCliente());

		txtNacionalidad.setText(clienteBuscado.getNacionalidadCliente());

		txtCorreo.setText(clienteBuscado.getCorreoCliente());

	}

	void modificarCliente() {

		ClientesDAO clienteDAO = new ClientesDAO();

		Cliente modificarCliente = new Cliente(Integer.parseInt(txtCedula.getText()), txtNombre.getText(),
				txtApelldio.getText(), txtNacionalidad.getText(), txtCorreo.getText());

		clienteDAO.modificarCliente(modificarCliente);

	}

	void eliminarCliente() {

		ClientesDAO clienteDAO = new ClientesDAO();

		Cliente eliminado = new Cliente();

		eliminado.setCedulaCliente(Integer.parseInt(txtCedula.getText()));

		clienteDAO.eliminarCliente(eliminado);
	}

	void limpiar() {

		txtCedula.setText("");

		txtNombre.setText("");

		txtApelldio.setText("");

		txtNacionalidad.setText("");

		txtCorreo.setText("");
	}

}

package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import clases.Factura;
import clases.ProductoFactura;
import data_acces_object_DAO.FacturaDAO;
import data_acces_object_DAO.ProductoFacturaDAO;
import java.awt.Point;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class GUIVenta extends JPanel {
	private JTextField txtIdFactura;
	private JTable tblFacturas;
	private JTextField txtProductoFacturaId;
	private JTextField txtIdFactura2;
	private JTextField txtProductoId;
	private JTextField txtCantidad;
	private JTable tblRegistroProductos;
	private JTextField txtNombreEmpresa;
	private JTextField txtCiudad;
	private JTextField txtCedula;
	private JDateChooser dateFecha;
	private JButton btnRegistrar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnTransaccion;

	/**
	 * Create the panel.
	 */
	public GUIVenta() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		
		panel.setBackground(Color.RED);
		
		add(panel, BorderLayout.NORTH);

		
		JLabel lblNewLabel = new JLabel("VENTAS");
		
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblNewLabel.setForeground(Color.WHITE);
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.add(lblNewLabel);

		
		JPanel panel_1 = new JPanel();
		
		panel_1.setBackground(new Color(119, 136, 153));
		
		add(panel_1, BorderLayout.WEST);
		
		panel_1.setLayout(new BorderLayout(0, 0));

		
		JPanel panel_2 = new JPanel();
		
		panel_2.setBackground(new Color(119, 136, 153));
		
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		
		JLabel lblFactura = new JLabel("Id_Factura: ");
		
		lblFactura.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_2.add(lblFactura);

		
		txtIdFactura = new JTextField();
		
		txtIdFactura.setBackground(Color.LIGHT_GRAY);
		
		txtIdFactura.setEditable(false);
		
		panel_2.add(txtIdFactura);
		
		txtIdFactura.setColumns(20);

		
		JLabel lblNombreEmpresa = new JLabel("Nombre de la empresa: ");
		
		lblNombreEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_2.add(lblNombreEmpresa);

		
		txtNombreEmpresa = new JTextField();
		
		txtNombreEmpresa.setText("Tienda unale\u00F1a");
		
		panel_2.add(txtNombreEmpresa);
		
		txtNombreEmpresa.setColumns(10);

		
		JLabel lblFecha = new JLabel("Fecha factura:");
		
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_2.add(lblFecha);

		
		dateFecha = new JDateChooser();
		
		dateFecha.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel_2.add(dateFecha);

		
		JLabel lblCiudad = new JLabel("Ciudad: ");
		
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_2.add(lblCiudad);

		
		txtCiudad = new JTextField();
		
		txtCiudad.setText("Bogota");
		
		panel_2.add(txtCiudad);
		
		txtCiudad.setColumns(10);

		JLabel lblCedula = new JLabel("Cedula del cliente:");
		
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_2.add(lblCedula);

		txtCedula = new JTextField();
		
		txtCedula.setBackground(Color.LIGHT_GRAY);
		
		txtCedula.setEditable(false);
		
		panel_2.add(txtCedula);
		
		txtCedula.setColumns(10);

		
		JLabel lblNewLabel_1 = new JLabel(".");
		
		panel_2.add(lblNewLabel_1);

		JButton btnNuevaFactura = new JButton("Nueva Factura");
		
		btnNuevaFactura.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				ingresarCedulaCliente();
				
				nuevaFactura();
				
				obtenerFechaActua();
				
				registrarFactura();
				
				bloquearBotonesResgistroProductos();
				
				mostrarDatosTablaFactura(tblFacturas);
				
				if (verificarExistenciaCliente() == false) {
				
					txtCedula.setText("");
					
					txtIdFactura.setText("");
					
					bloquearBotonesResgistroProductos();
				
				} else {
				
					btnNuevaFactura.setEnabled(false);
					
					txtIdFactura2.setText(txtIdFactura.getText());
				}
			}
		});
		
		panel_2.add(btnNuevaFactura);

		
		JPanel panel_3 = new JPanel();
		
		add(panel_3, BorderLayout.CENTER);
		
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		
		JPanel panel_4 = new JPanel();
		
		panel_3.add(panel_4);
		
		panel_4.setLayout(new BorderLayout(0, 0));

		
		JLabel lblTituloTablaFacturas = new JLabel("Tabla de facturas");
		
		lblTituloTablaFacturas.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblTituloTablaFacturas.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_4.add(lblTituloTablaFacturas, BorderLayout.NORTH);

		
		tblFacturas = new JTable();
		
		panel_4.add(new JScrollPane(tblFacturas), BorderLayout.CENTER);

		
		JPanel panel_5 = new JPanel();
		
		panel_3.add(panel_5);

		panel_5.setLayout(new BorderLayout(0, 0));

		
		JPanel panel_6 = new JPanel();
		
		panel_5.add(panel_6, BorderLayout.NORTH);

		
		JLabel lblNewLabel_3 = new JLabel("REGISTRAR PRODUCTOS DEL CLIENTE");
		
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		panel_6.add(lblNewLabel_3);

		
		JPanel panel_7 = new JPanel();
		
		panel_5.add(panel_7, BorderLayout.CENTER);
		
		panel_7.setLayout(new BorderLayout(0, 0));

		
		JPanel panel_8 = new JPanel();
		
		panel_7.add(panel_8, BorderLayout.NORTH);

		
		JLabel lblProductoFacturaId = new JLabel("Prodducto_Factura_ID");
		
		panel_8.add(lblProductoFacturaId);

		
		txtProductoFacturaId = new JTextField();
		
		txtProductoFacturaId.setEditable(false);
		
		txtProductoFacturaId.setBackground(Color.LIGHT_GRAY);
		
		panel_8.add(txtProductoFacturaId);
		
		txtProductoFacturaId.setColumns(10);

		JLabel lblFacturaId2 = new JLabel("Id_Factura:");
		
		panel_8.add(lblFacturaId2);

		
		txtIdFactura2 = new JTextField();
		
		txtIdFactura2.setEditable(false);
		
		txtIdFactura2.setBackground(Color.LIGHT_GRAY);
		
		panel_8.add(txtIdFactura2);
		
		txtIdFactura2.setColumns(10);

		
		JLabel lblProductoId = new JLabel("Id_Producto:");
		
		panel_8.add(lblProductoId);

		
		txtProductoId = new JTextField();
		
		txtProductoId.setBackground(Color.WHITE);
		
		panel_8.add(txtProductoId);
		
		txtProductoId.setColumns(10);

		
		JLabel lblCantidad = new JLabel("Cantidad:");
		
		panel_8.add(lblCantidad);

		
		txtCantidad = new JTextField();

		panel_8.add(txtCantidad);
		
		txtCantidad.setColumns(10);

		
		btnRegistrar = new JButton("Registrar");
		
		btnRegistrar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				// ALTER TABLE producto_factura AUTO_INCREMENT = 1
				
				if (!txtCantidad.getText().isEmpty() && !txtProductoId.getText().isEmpty()) {
				
					if (verificarExistenciaProducto() == false) {
					
						JOptionPane.showMessageDialog(null, "El producto no se encuentra en la base de datos");
					
					} else {
					
						registrarProducto();
						
						mostrarProductosFactura();
						
						if (verificarCantidadRegistrosProductosFactura() != 0) {
						
							btnModificar.setEnabled(true);
							
							btnEliminar.setEnabled(true);
							
							btnTransaccion.setEnabled(true);
						
						} else {
						
							btnModificar.setEnabled(false);
							
							btnEliminar.setEnabled(false);
							
							btnTransaccion.setEnabled(false);
						}
					}
				
				} else {
				
					JOptionPane.showMessageDialog(null, "Debes llenar los campos CANTIDAD Y ID_PRODUCTO");
				}

			}
		});
		
		panel_8.add(btnRegistrar);

		tblRegistroProductos = new JTable();
		
		tblRegistroProductos.addMouseListener(new MouseAdapter() {
		
			public void mousePressed(MouseEvent e) {
			
				JTable table = (JTable) e.getSource();
				
				Point point = e.getPoint();
				
				int row = table.rowAtPoint(point);
				
				if (e.getClickCount() == 1) {
				
					txtProductoFacturaId.setText(
							tblRegistroProductos.getValueAt(tblRegistroProductos.getSelectedRow(), 0).toString());
					txtIdFactura2.setText(
							tblRegistroProductos.getValueAt(tblRegistroProductos.getSelectedRow(), 2).toString());
					txtProductoId.setText(
							tblRegistroProductos.getValueAt(tblRegistroProductos.getSelectedRow(), 3).toString());
					txtCantidad.setText(
							tblRegistroProductos.getValueAt(tblRegistroProductos.getSelectedRow(), 1).toString());
				}
			}
		});
		
		panel_7.add(new JScrollPane(tblRegistroProductos), BorderLayout.CENTER);

		
		JPanel panel_9 = new JPanel();
		
		panel_7.add(panel_9, BorderLayout.SOUTH);

		
		btnModificar = new JButton("Modificar");
		
		btnModificar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				if (verificarExistenciaProducto() == false) {
				
					JOptionPane.showMessageDialog(null, "El producto no se encuentra en la base de datos");
				
				} else {
				
					if (txtProductoFacturaId.getText().isEmpty()) {
					
						JOptionPane.showMessageDialog(null,
								"No has seleccionado el producto que deseas modificar.\n Selecciona un registro oprimiendo sobre la tabla");
					
					} else {
					
						if (verificarCantidadRegistrosProductosFactura() != 0) {
						
							modificarProductoFactura();
							
							txtProductoFacturaId.setText("");
							
							mostrarProductosFactura();
						
						} else {
						
							mostrarProductosFactura();
							
							btnModificar.setEnabled(false);
							
							btnEliminar.setEnabled(false);
							
							btnTransaccion.setEnabled(false);
						}
					}
				}
			}
		});

		btnTransaccion = new JButton("Terminar Transaccion ");
		
		btnTransaccion.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				resetearProductosFactura();
				
				txtIdFactura.setText("");
				
				txtCedula.setText("");
				
				btnTransaccion.setEnabled(false);	
				
				btnModificar.setEnabled(false);
				
				btnEliminar.setEnabled(false);
				
				btnNuevaFactura.setEnabled(true);
				
				txtProductoFacturaId.setText("");
				
				txtIdFactura2.setText("");
				
				txtProductoId.setText("");
				
				txtCantidad.setText("");
				
				bloquearBotonesResgistroProductos();
			}
		});
		
		panel_9.add(btnTransaccion);
		
		panel_9.add(btnModificar);

		
		btnEliminar = new JButton("Eliminar");
		
		btnEliminar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				if (!txtProductoFacturaId.getText().isEmpty()) {
				
					eliminarProductoFactura();
					
					txtProductoFacturaId.setText("");
					
					mostrarProductosFactura();

				} else {

					JOptionPane.showMessageDialog(null, "Debes seleccionar el registro que deseas eliminar");
				}

				if (verificarCantidadRegistrosProductosFactura() == 0) {
				
					btnModificar.setEnabled(false);
					
					btnEliminar.setEnabled(false);
					
					btnTransaccion.setEnabled(false);
					
					mostrarProductosFactura();
					
					txtProductoFacturaId.setText("");
				
				}
				
				mostrarProductosFactura();
			}
		});

		panel_9.add(btnEliminar);

		
		mostrarDatosTablaFactura(tblFacturas);
		
		bloquearBotonesResgistroProductos();
	}

	
	void nuevaFactura() {
	
		int ultimaFactura;
		
		FacturaDAO factura = new FacturaDAO();
		
		ultimaFactura = factura.obtenerUltimaFactura() + 1;
		
		txtIdFactura.setText(ultimaFactura + "");
	}

	void ingresarCedulaCliente() {
		
		String respuesta1 = JOptionPane.showInputDialog("¿El cliente se encuntra registrado en el sistema SI / NO?");
		
		if (respuesta1.equalsIgnoreCase("si")) {
		
			String respuesta2 = JOptionPane.showInputDialog("Ingrese la cedula de cliente");
			
			txtCedula.setText(respuesta2);
		
		} else {
		
			txtCedula.setText("");
		}
	}

	void obtenerFechaActua() {
		
		dateFecha.setDate(new Date());
	}

	void mostrarDatosTablaFactura(JTable tablaFactura) {

		FacturaDAO facturaDAO = new FacturaDAO();

		String titulo[] = { "ID_FACTURA", "NOMBRE DE LA EMPRESA", "FECHA DE LA FACTURA", "CIUDAD",
				"CEDULA DEL CLIENTE" };

		DefaultTableModel model = new DefaultTableModel(null, titulo);

		ArrayList<Factura> listaTablaFactura = facturaDAO.obtenerFacturas();

		String[] datosFactura = new String[5];

		for (Factura tbf : listaTablaFactura) {

			datosFactura[0] = tbf.getIdFactura() + "";

			datosFactura[1] = tbf.getNombreEmpresaFactura();

			datosFactura[2] = tbf.getFechaFactura() + "";

			datosFactura[3] = tbf.getCiudadFactura();

			datosFactura[4] = tbf.getClienteIdFactura() + "";

			model.addRow(datosFactura);

		}

		tblFacturas.setModel(model);
	}

	void registrarFactura() {

		FacturaDAO facturaDAO = new FacturaDAO();

		Factura facturaActual = new Factura();

		facturaActual.setIdFactura(Integer.parseInt(txtIdFactura.getText()));

		facturaActual.setNombreEmpresaFactura(txtNombreEmpresa.getText());

		facturaActual.setFechaFactura(dateFecha.getDate());

		facturaActual.setCiudadFactura(txtCiudad.getText());

		if (txtCedula.getText().isEmpty()) {

			facturaActual.setClienteIdFactura(0);

			txtCedula.setText("0");

		} else {
			facturaActual.setClienteIdFactura(Integer.parseInt(txtCedula.getText()));
		}

		facturaDAO.crearFactura(facturaActual);
	}

	void bloquearBotonesResgistroProductos() {
		if (txtCedula.getText().isEmpty()) {

			txtProductoFacturaId.setEditable(false);

			txtIdFactura2.setEditable(false);

			txtProductoId.setEditable(false);

			txtCantidad.setEditable(false);

			btnRegistrar.setEnabled(false);

			btnModificar.setEnabled(false);

			btnEliminar.setEnabled(false);

			btnTransaccion.setEnabled(false);

		} else {

			// txtProductoFacturaId.setEditable(true);

			// txtIdFactura2.setEditable(true);

			txtProductoId.setEditable(true);

			txtCantidad.setEditable(true);

			btnRegistrar.setEnabled(true);

		}
	}

	boolean verificarExistenciaCliente() {

		FacturaDAO nuevaFactura = new FacturaDAO();

		boolean verificarExistencia;

		verificarExistencia = nuevaFactura.verificarExistenciaCliente(Integer.parseInt(txtCedula.getText()));

		return verificarExistencia;
	}

	void registrarProducto() {

		ProductoFacturaDAO productosFacturaDAO = new ProductoFacturaDAO();

		ProductoFactura miProductoFactura = new ProductoFactura();

		miProductoFactura.setCantidadProductoFactura(Integer.parseInt(txtCantidad.getText()));

		miProductoFactura.setIdFactura(Integer.parseInt(txtIdFactura2.getText()));

		miProductoFactura.setIdProdcuto(Integer.parseInt(txtProductoId.getText()));

		productosFacturaDAO.registrarProductoFactura(miProductoFactura);

	}

	boolean verificarExistenciaProducto() {

		ProductoFacturaDAO productosFacturaDAO = new ProductoFacturaDAO();

		return productosFacturaDAO.verificarExistenciaProducto(Integer.parseInt(txtProductoId.getText()));

	}

	void modificarProductoFactura() {

		ProductoFacturaDAO productosFacturaDAO = new ProductoFacturaDAO();

		ProductoFactura miProductoFactura = new ProductoFactura();

		miProductoFactura.setIdProductoFactura(Integer.parseInt(txtProductoFacturaId.getText()));

		miProductoFactura.setCantidadProductoFactura(Integer.parseInt(txtCantidad.getText()));

		miProductoFactura.setIdFactura(Integer.parseInt(txtIdFactura2.getText()));

		miProductoFactura.setIdProdcuto(Integer.parseInt(txtProductoId.getText()));

		productosFacturaDAO.modificarRegistroFactura(miProductoFactura);
	}

	void mostrarProductosFactura() {

		ProductoFacturaDAO productosFacturaDAO = new ProductoFacturaDAO();

		String titulo[] = { "ID_PRODUCTO_FACTURA", "CANTIDAD", "ID_FACTURA", "ID_PRODUCTO" };

		DefaultTableModel model = new DefaultTableModel(null, titulo);

		ArrayList<ProductoFactura> listaProductosFactura = productosFacturaDAO
				.listarProductosFactura(Integer.parseInt(txtIdFactura.getText()));

		String[] datosProductoFactura = new String[4];

		for (ProductoFactura tbfpf : listaProductosFactura) {

			datosProductoFactura[0] = tbfpf.getIdProductoFactura() + "";

			datosProductoFactura[1] = tbfpf.getCantidadProductoFactura() + "";

			datosProductoFactura[2] = tbfpf.getIdFactura() + "";

			datosProductoFactura[3] = tbfpf.getIdProdcuto() + "";

			model.addRow(datosProductoFactura);

		}
		tblRegistroProductos.setModel(model);
	}

	int verificarCantidadRegistrosProductosFactura() {
		int cantidad;

		ProductoFacturaDAO productosFacturaDAO = new ProductoFacturaDAO();

		cantidad = productosFacturaDAO.totalProductosPorFactura(Integer.parseInt(txtIdFactura.getText()));

		return cantidad;

	}

	void eliminarProductoFactura() {

		ProductoFacturaDAO productosFacturaDAO = new ProductoFacturaDAO();

		productosFacturaDAO.eliminarProductoFactura(Integer.parseInt(txtProductoFacturaId.getText()));

	}
	
	void resetearProductosFactura() {

		ProductoFacturaDAO productosFacturaDAO = new ProductoFacturaDAO();

		String titulo[] = { "ID_PRODUCTO_FACTURA", "CANTIDAD", "ID_FACTURA", "ID_PRODUCTO" };

		DefaultTableModel model = new DefaultTableModel(null, titulo);

		ArrayList<ProductoFactura> listaProductosFactura = productosFacturaDAO
				.listarProductosFactura(Integer.parseInt(txtIdFactura.getText()));

		String[] datosProductoFactura = new String[4];

		for (ProductoFactura tbfpf : listaProductosFactura) {

			datosProductoFactura[0] = "";

			datosProductoFactura[1] = "";

			datosProductoFactura[2] = "";

			datosProductoFactura[3] = "";

			model.addRow(datosProductoFactura);

		}
		tblRegistroProductos.setModel(model);
	}

}

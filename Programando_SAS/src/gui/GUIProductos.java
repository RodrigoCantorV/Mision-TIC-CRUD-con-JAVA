package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.DropMode;
import java.awt.Dimension;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import com.toedter.components.JSpinField;
import clases.Cliente;
import clases.Producto;
import data_acces_object_DAO.ClientesDAO;
import data_acces_object_DAO.ProductosDAO;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUIProductos extends JPanel {
	
	private JTextField txtProducto;
	
	private JTextField txtNombre;
	
	private JTextField txtPrecio;
	
	private JTextField txtDescripcion;
	
	private JTable tblProductos;
	
	private Producto datosProducto;
	
	private JDateChooser dateVencimiento;

	/**
	 * Create the panel.
	 */
	public GUIProductos() {
	
		setBackground(new Color(255, 255, 255));
		
		setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		
		panel.setMinimumSize(new Dimension(0, 0));
		
		panel.setBorder(null);
		
		panel.setBackground(new Color(119, 136, 153));
		
		add(panel, BorderLayout.WEST);
		
		panel.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setBackground(new Color(119, 136, 153));
		
		panel.add(panel_1, BorderLayout.NORTH);
		
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		
		JLabel lblProducto = new JLabel("id_Producto");
		
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_1.add(lblProducto);
		
		
		txtProducto = new JTextField();
		
		txtProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_1.add(txtProducto);
		
		txtProducto.setColumns(18);
		
		
		JLabel lblNombre = new JLabel("Nombre del producto:");
		
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_1.add(lblNombre);
		
		
		txtNombre = new JTextField();
		
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_1.add(txtNombre);
		
		txtNombre.setColumns(18);
		
		
		JLabel lblPrecio = new JLabel("Precio del prodcuto:");
		
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_1.add(lblPrecio);
		
		
		txtPrecio = new JTextField();
		
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_1.add(txtPrecio);
		
		txtPrecio.setColumns(18);
		
		
		JLabel lblDescripcion = new JLabel("Descripcion del producto:");
		
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		panel_1.add(lblDescripcion);
		
		
		txtDescripcion = new JTextField();
		
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_1.add(txtDescripcion);
		
		txtDescripcion.setColumns(18);
		
		
		JLabel dateFecha = new JLabel("Fecha de vencimiento:");
		
		dateFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		panel_1.add(dateFecha);
		
		
		JButton btnLimpiar = new JButton("Limpiar");
		
		btnLimpiar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				limpiar();
			
			}

		});
		
		
		dateVencimiento = new JDateChooser();
		
		dateVencimiento.setDateFormatString("y/MM/d");
		
		dateVencimiento.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel_1.add(dateVencimiento);
		
		panel_1.add(btnLimpiar);
		
		
		JPanel panel_2 = new JPanel();
		
		panel_2.setBackground(new Color(255, 69, 0));
		
		add(panel_2, BorderLayout.NORTH);
		
		
		JLabel lblNewLabel = new JLabel("PRODUCTOS");
		
		lblNewLabel.setForeground(Color.WHITE);
		
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		panel_2.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		
		panel_3.setBackground(new Color(192, 192, 192));
		
		add(panel_3, BorderLayout.SOUTH);
		
		
		JButton btnGuardar = new JButton("Guardar");
		
		btnGuardar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				if (validarCamposVacios() == true) {
				
					JOptionPane.showInternalMessageDialog(null, "Debe de llenar todos los campos");
				} else {
					
					obtenerDatos();
					
					registrarProducto();
					
					mostrarDatosTabla(tblProductos);
				
				}
			
			}
		
		});
		
		panel_3.add(btnGuardar);
		
		
		JButton btnBuscar = new JButton("Buscar");
		
		btnBuscar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				if (validarIdProducto() == true) {
				
					JOptionPane.showInternalMessageDialog(null, "Debe Ingresar el campo id_producto para Buscar el producto deseado");
				
				} else {
					
				
					buscarProducto();
					
					mostrarDatosTabla(tblProductos);
				
				}
			
			}
		
		});
		
		panel_3.add(btnBuscar);
		
		
		JButton btnModificar = new JButton("Modificar");
		
		btnModificar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
							
				if (validarIdProducto() == true) {
					JOptionPane.showInternalMessageDialog(null, "Debe llenar todos los campos para poder modificar el cliente");
				
				} else {
					
					modificarProducto();
				
					mostrarDatosTabla(tblProductos);
					
					limpiar();
				}
			}
		});
		
		panel_3.add(btnModificar);
		
		
		JButton btnEliminar = new JButton("Eliminar");
		
		btnEliminar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
				if (validarIdProducto() == true) {
				
					JOptionPane.showInternalMessageDialog(null, "Debe Ingresar el campo ID_PRODUCTO para Buscar el cliente deseado");
				
				} else {
				
					buscarProducto();
					
					String respuesta = JOptionPane.showInputDialog("Seguro deseas eliminar este registro: SI/NO");
					
					if(respuesta.equalsIgnoreCase("si")) {
					
						eliminarProducto();
					
						limpiar();
						
						mostrarDatosTabla(tblProductos);
					
					}
				
				}
			
			}
		
		});
		
		panel_3.add(btnEliminar);
		
		
		tblProductos = new JTable();
		
		add(new JScrollPane(tblProductos), BorderLayout.CENTER);
		
		mostrarDatosTabla(tblProductos);

	}
	
	boolean validarCamposVacios() {
	
		if (txtProducto.getText().isEmpty() || txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
		
			return true;
		
		} else {
		
			return false;
	
		}
	
	}
	
	boolean validarIdProducto() {
	
		if (txtProducto.getText().isEmpty()) {
		
			return true;
		
		} else {
		
			return false;
		
		}
		
	}
	

	void mostrarDatosTabla(JTable tablaProdictos) {

		ProductosDAO listaProductos = new ProductosDAO();

		String titulo[] = { "ID_PRODUCTO", "NOMBBRE DEL PRODUCTO", "PRECIO", "DESCRIPCION", "FECHA DE VENCIMIENTO" };

		DefaultTableModel model = new DefaultTableModel(null, titulo);

		ArrayList<Producto> listaTablaProductos = listaProductos.listarProducto();

		String[] datosCliente = new String[5];

		for (Producto tbc : listaTablaProductos) {

			datosCliente[0] = tbc.getIdProducto() + "";

			datosCliente[1] = tbc.getNombreProducto() + "";

			datosCliente[2] = tbc.getPrecioProducto() + "";

			datosCliente[3] = tbc.getDescripcionProducto() + "";

			datosCliente[4] = tbc.getFechaVencimientoProducto() + "";

			model.addRow(datosCliente);

		}

		tblProductos.setModel(model);
	}
	
	Producto obtenerDatos() {
		
		datosProducto = new Producto();
		
		datosProducto.setIdProducto(Integer.parseInt(txtProducto.getText()));
		
		datosProducto.setNombreProducto(txtNombre.getText());
		
		datosProducto.setPrecioProducto(Integer.parseInt(txtPrecio.getText()));
		
		datosProducto.setDescripcionProducto(txtDescripcion.getText());
		
		datosProducto.setFechaVencimientoProducto(dateVencimiento.getDate());
		
		return datosProducto;

	}
	
	
	
	void registrarProducto() {
		
		ProductosDAO producto = new ProductosDAO();
		
		producto.crearProdcuto(obtenerDatos());
	}
	
	void buscarProducto() {

		ProductosDAO productoDAO = new ProductosDAO();

		Producto buscarProducto = new Producto();
		
		buscarProducto.setIdProducto(Integer.parseInt(txtProducto.getText()));
		
		Producto productoBuscado = new Producto();
		
		productoBuscado = productoDAO.buscarProducto(buscarProducto);

		txtNombre.setText(productoBuscado.getNombreProducto());

		txtPrecio.setText(productoBuscado.getPrecioProducto()+"");

		txtDescripcion.setText(productoBuscado.getDescripcionProducto());

		dateVencimiento.setDate(productoBuscado.getFechaVencimientoProducto());

	}
	
	void modificarProducto() {
	
	ProductosDAO productoDAO = new ProductosDAO();
			
	productoDAO.modificarProdcuto(obtenerDatos());
	
	}
	
	void eliminarProducto() {
		
		ProductosDAO clienteDAO = new ProductosDAO();
		
		Producto eliminado = new Producto();
		
		eliminado.setIdProducto(Integer.parseInt(txtProducto.getText()));
		
		clienteDAO.eliminarProdcuto(eliminado);
	}
	
	void limpiar() {
		
		txtProducto.setText("");
		
		txtNombre.setText("");
		
		txtPrecio.setText("");
		
		txtDescripcion.setText("");
		
		dateVencimiento.setDate(new java.util.Date());
	
	}
	
}

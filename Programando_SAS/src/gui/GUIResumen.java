
package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import clases.Factura;
import clases.ProductoFactura;
import data_acces_object_DAO.FacturaDAO;
import data_acces_object_DAO.ProductoFacturaDAO;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;

public class GUIResumen extends JPanel {
	public JTable table;
	public JTable table_1;

	/**
	 * Create the panel.
	 */
	public GUIResumen(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		
		add(panel);
		
		panel.setLayout(new BorderLayout(0, 0));
		
		
		table = new JTable();
		
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		JPanel panel_2 = new JPanel();
		
		panel_2.setBackground(new Color(255, 69, 0));
		
		panel.add(panel_2, BorderLayout.NORTH);
		
		
		JLabel lblNewLabel = new JLabel("FACTURAS TOTALES");
		
		panel_2.add(lblNewLabel);
		
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JPanel panel_1 = new JPanel();
		
		add(panel_1);
		
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		table_1 = new JTable();
		
		panel_1.add(new JScrollPane(table_1), BorderLayout.CENTER);
		
		
		JPanel panel_3 = new JPanel();
		
		panel_3.setBackground(new Color(255, 69, 0));
		
		panel_1.add(panel_3, BorderLayout.NORTH);
		
		
		JLabel lblNewLabel_1 = new JLabel("PRODUCTOS_FACTURAS");
		
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		panel_3.add(lblNewLabel_1);
		
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		
	}
	
	void mostrarTablaFacturas() {
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

		table.setModel(model);
	}

	void mostrarTodosProductosFactura() {

		ProductoFacturaDAO productosFacturaDAO = new ProductoFacturaDAO();

		String titulo[] = { "ID_PRODUCTO_FACTURA", "CANTIDAD", "ID_FACTURA", "ID_PRODUCTO" };

		DefaultTableModel model = new DefaultTableModel(null, titulo);

		ArrayList<ProductoFactura> listaProductosFactura = productosFacturaDAO.listarTodosProductosFactura();

		String[] datosProductoFactura = new String[4];

		for (ProductoFactura tbfpf : listaProductosFactura) {

			datosProductoFactura[0] = tbfpf.getIdProductoFactura() + "";

			datosProductoFactura[1] = tbfpf.getCantidadProductoFactura() + "";

			datosProductoFactura[2] = tbfpf.getIdFactura() + "";

			datosProductoFactura[3] = tbfpf.getIdProdcuto() + "";

			model.addRow(datosProductoFactura);

		}
		table_1.setModel(model);
	}

}

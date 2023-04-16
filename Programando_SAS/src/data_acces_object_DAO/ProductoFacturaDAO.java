package data_acces_object_DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import clases.ProductoFactura;
import conexion.Conexion;
import interfaces.IProductoFactura;

public class ProductoFacturaDAO implements IProductoFactura {

	@Override
	public ArrayList<ProductoFactura> listarProductosFactura(int ID_Factura) {

		String consulta = "SELECT * FROM producto_factura WHERE producto_factura.fac_id =? ";

		ArrayList<ProductoFactura> listaProductosFactura = new ArrayList<ProductoFactura>();

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		Connection con = miConexion.getConnection();

		try {

			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(consulta);

			pstm.setInt(1, ID_Factura);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				listaProductosFactura.add(new ProductoFactura(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaProductosFactura;
	}

	@Override
	public void registrarProductoFactura(ProductoFactura miProductoFactura) {

		String sentencia = "INSERT INTO producto_factura (profac_cantidad,fac_id,pro_id) values (?,?,?)";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		Connection con = miConexion.getConnection();

		try {

			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sentencia);

			pstm.setInt(1, miProductoFactura.getCantidadProductoFactura());

			pstm.setInt(2, miProductoFactura.getIdFactura());

			pstm.setInt(3, miProductoFactura.getIdProdcuto());

			pstm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			miConexion.desconectar();

		}

	}
	
	public boolean verificarExistenciaProducto(int idProducto) {
		
		String sentencia = "SELECT * FROM producto WHERE pro_id=?";
		
		boolean existencia = true;
		
		Conexion miConexion = new Conexion();
		
		miConexion.conectarse();
		
		Connection con = miConexion.getConnection();
		
		try {
		
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sentencia);
			
			pstm.setInt(1,idProducto);
			
			ResultSet rs = pstm.executeQuery();
			
			existencia = rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			miConexion.conectarse();
		}
		
		return existencia;
		
	}

	@Override
	public void modificarRegistroFactura(ProductoFactura miProductoFactura) {
		
		String consulta = "UPDATE producto_factura SET profac_id =?,profac_cantidad=?,fac_id=?,pro_id=? WHERE profac_id=?";
		
		Conexion miConexion = new Conexion();
		
		miConexion.conectarse();
		
		Connection con = miConexion.getConnection();
		
		try {
			
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(consulta);
	
			pstm.setInt(1,miProductoFactura.getIdProductoFactura());
			
			pstm.setInt(2, miProductoFactura.getCantidadProductoFactura());
			
			pstm.setInt(3, miProductoFactura.getIdFactura());
			
			pstm.setInt(4,miProductoFactura.getIdProdcuto());
			
			pstm.setInt(5,miProductoFactura.getIdProductoFactura());
			
			pstm.execute();
			
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		
	}

	@Override
	public int totalProductosPorFactura(int id_factura) {
		int cantidad = 0;
		
		String consulta="SELECT COUNT(producto_factura.profac_id) FROM producto_factura WHERE producto_factura.fac_id =?";
		
		Conexion miConexion = new Conexion();
		
		miConexion.conectarse();
		
		Connection con = miConexion.getConnection();
		
		try {
			
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(consulta);
			
			pstm.setObject(1, id_factura);
			
			ResultSet rs = pstm.executeQuery();
			
			rs.next();
			
			cantidad=rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cantidad;
	}

	@Override
	public void eliminarProductoFactura(int idProductoFactura) {
		
		String sentencia="DELETE FROM producto_factura  WHERE profac_id=? ";
		
		Conexion miConexion = new Conexion();
		
		miConexion.conectarse();
		
		Connection con = miConexion.getConnection();
		
		try {
			
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sentencia);
		
			pstm.setInt(1,idProductoFactura);
			
			pstm.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	@Override
	public ArrayList<ProductoFactura> listarTodosProductosFactura() {
		String consulta = "SELECT * FROM producto_factura";

		ArrayList<ProductoFactura> listaProductosFactura = new ArrayList<ProductoFactura>();

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		Connection con = miConexion.getConnection();

		try {

			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(consulta);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				listaProductosFactura.add(new ProductoFactura(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaProductosFactura;
	}

}

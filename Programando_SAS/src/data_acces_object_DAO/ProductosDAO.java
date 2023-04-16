package data_acces_object_DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.mysql.jdbc.PreparedStatement;
import clases.Producto;
import conexion.Conexion;
import interfaces.IProducto;

public class ProductosDAO implements IProducto {
	
	private PreparedStatement pstm;

	private ResultSet rs;

	private Connection con;
	
	@Override
	public void crearProdcuto(Producto miProducto) {
	
		String consulta = "INSERT INTO producto(pro_id,pro_nombre,pro_precio,pro_descripcion,pro_fecha_vencimiento) VALUES(?,?,?,?,?)";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		con = miConexion.getConnection();
		
		try {

			if (validarProducto(miProducto) == false) {

				pstm = (PreparedStatement) con.prepareStatement(consulta);

				pstm.setInt(1, miProducto.getIdProducto());

				pstm.setString(2, miProducto.getNombreProducto());

				pstm.setInt(3, miProducto.getPrecioProducto());

				pstm.setString(4, miProducto.getDescripcionProducto());
				
			    long timeInMilliSeconds = miProducto.getFechaVencimientoProducto().getTime();
			        
			    java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
				
				pstm.setDate(5,date1);

				pstm.execute();

				JOptionPane.showMessageDialog(null, "Producto registrado correctamente");

			} else {
		
				JOptionPane.showMessageDialog(null, "El id del producto que que tratas de registrar ya se encuntra en uso.\n"
													+ "Por favor virifica la base de datos");
		
			}
		} catch (SQLException e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Error al regitrar los datos");

		} finally {
		
			miConexion.desconectar();
		}
		
	}

	@Override
	public void modificarProdcuto(Producto miProducto) {
	
		String consulta = "UPDATE producto SET pro_id =?,pro_nombre=?,pro_precio=?,pro_descripcion=?,pro_fecha_vencimiento=? WHERE pro_id=?";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		con = miConexion.getConnection();

		try {

			if (validarProducto(miProducto) == true) {

				pstm = (PreparedStatement) con.prepareStatement(consulta);
				
				pstm.setInt(1, miProducto.getIdProducto());
				
				pstm.setString(2, miProducto.getNombreProducto());
				
				pstm.setInt(3, miProducto.getPrecioProducto());
				
				pstm.setString(4, miProducto.getDescripcionProducto());
				
			    long timeInMilliSeconds = miProducto.getFechaVencimientoProducto().getTime();
		        
			    java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
				
				pstm.setDate(5, date1);
				
				pstm.setInt(6, miProducto.getIdProducto());
				
				
				pstm.execute();
				
				JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
				
			} else {
		
				JOptionPane.showMessageDialog(null, "El Producto no se encuentra registrado");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			miConexion.desconectar();
		}
		
	}

	@Override
	public void eliminarProdcuto(Producto miProducto) {
		
		String consulta = "DELETE FROM producto WHERE pro_id=?";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		con = miConexion.getConnection();

		try {

			if (validarProducto(miProducto) == true) {

				pstm = (PreparedStatement) con.prepareStatement(consulta);
				
				pstm.setInt(1, miProducto.getIdProducto());
								
				pstm.execute();
				
				JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
				
			} else {
				
				JOptionPane.showMessageDialog(null, "El Producto no se encuentra registrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			miConexion.desconectar();
		}
		
	}

	@Override
	public ArrayList<Producto> listarProducto() {
		
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();

		String consulta = "SELECT * FROM producto";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		con = miConexion.getConnection();

		try {

			pstm = (PreparedStatement) con.prepareStatement(consulta);

			rs = pstm.executeQuery();

			while (rs.next()) {

				listaProductos.add(
						
						new Producto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5)));
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
	
			miConexion.desconectar();
		}
		return listaProductos;
	}

	@Override
	public Producto buscarProducto(Producto idProducto) {

		Producto miProducto = new Producto();

		String consulta = "SELECT * FROM producto where pro_id=?";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		miConexion.conectarse();

		con = miConexion.getConnection();

		try {

			if (validarProducto(idProducto) == true) {

				pstm = (PreparedStatement) con.prepareStatement(consulta);

				pstm.setInt(1, idProducto.getIdProducto());

				rs = pstm.executeQuery();

				rs.next();

				miProducto.setNombreProducto(rs.getString(2));

				miProducto.setPrecioProducto(Integer.parseInt(rs.getString(3)));

				miProducto.setDescripcionProducto(rs.getString(4));

				miProducto.setFechaVencimientoProducto(rs.getDate(5));
				
			} else {
			
				JOptionPane.showMessageDialog(null, "El Producto que busca no esta registrado");
			}

		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			
			miConexion.desconectar();
		}

		return miProducto;
	}
	
	public boolean validarProducto(Producto miProducto) {
		
		String validarDatos = "SELECT * FROM producto where pro_id=?";
		
		try {

			pstm = (PreparedStatement) con.prepareStatement(validarDatos);

			pstm.setInt(1, miProducto.getIdProducto());

			rs = pstm.executeQuery();

			if (rs.next()) {

				return true;
				
			} else {

				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

}

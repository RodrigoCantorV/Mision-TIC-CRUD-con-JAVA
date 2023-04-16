package data_acces_object_DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import clases.Factura;
import conexion.Conexion;
import interfaces.IFactura;

public class FacturaDAO implements IFactura {

	
	public int obtenerUltimaFactura() {
		
		int UltimoRegistro = 0;
		
		Conexion miConexion = new Conexion();
		
		miConexion.conectarse();
		
		Connection con = miConexion.getConnection();
		
		String consulta = "SELECT count(fac_id) FROM factura";
		
		try {
		
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(consulta);
			
			ResultSet rs = pstm.executeQuery();
			
			rs.next();
			
			UltimoRegistro = rs.getInt(1);
			
		
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		
		}finally {
			miConexion.desconectar();
		}
		
		return UltimoRegistro;
	}

	@Override
	public ArrayList<Factura> obtenerFacturas() {
		
		ArrayList<Factura> listaFactura = new ArrayList<Factura>();
		
		Conexion miConexion = new Conexion();
		
		miConexion.conectarse();
		
		Connection con = miConexion.getConnection();
		
		String consulta= "SELECT * FROM factura";
		
		try {
			
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(consulta);
		
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				listaFactura.add(new Factura(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			miConexion.desconectar();
			
		}
		
		return listaFactura;
	}

	@Override
	public void crearFactura(Factura nuevaFactura) {
		
		boolean verificarExistenciaCliente = verificarExistenciaCliente(nuevaFactura.getClienteIdFactura());
		
		if(verificarExistenciaCliente == true) {
		
		Conexion miConexion = new Conexion();
		
		miConexion.conectarse();
		
		Connection con = miConexion.getConnection();
		
		String consulta = "INSERT INTO factura(fac_id,fac_nombre_empresa,fac_fecha,fac_ciudad,cli_id) values(?,?,?,?,?)";
		
		try {
			
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(consulta);
		
			pstm.setInt(1, nuevaFactura.getIdFactura());
			
			pstm.setString(2, nuevaFactura.getNombreEmpresaFactura());
			
			long timeInMilliSeconds = nuevaFactura.getFechaFactura().getTime();
	        
		    java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
			
			pstm.setDate(3,date1);
			
			pstm.setString(4,nuevaFactura.getCiudadFactura());
			
			pstm.setInt(5,nuevaFactura.getClienteIdFactura());
			
			pstm.execute();
			
			JOptionPane.showMessageDialog(null,"Factura creada con exito, agrega los productos correspondientes");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Hubo un error al registrar la factura");
			
			e.printStackTrace();
		}finally {
			miConexion.desconectar();
		}
		
		}else {
			JOptionPane.showMessageDialog(null, "Este cliente no es encuentra en la base de datos");
		}
		
	}
	
	public boolean verificarExistenciaCliente(int cedulaCliente) {
		boolean existe = true;
		
		Conexion miConexion = new Conexion();
		
		miConexion.conectarse();
		
		Connection con = miConexion.getConnection();
		
		String consulta = "SELECT * FROM cliente WHERE cliente.cli_id =?";
		
		try {
		
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(consulta);
			
			pstm.setInt(1, cedulaCliente);
			
			ResultSet rs = pstm.executeQuery();
			
			existe = rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return existe;
		
	}

}


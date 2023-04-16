package data_acces_object_DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import clases.Cliente;
import conexion.Conexion;
import interfaces.ICliente;

public class ClientesDAO implements ICliente {

	private PreparedStatement pstm;

	private ResultSet rs;

	private Connection con;

	public void crearCliente(Cliente miCliente) {

		String consulta = "INSERT INTO cliente(cli_id,cli_nombre,cli_apellido,cli_nacionalidad,cli_correo) VALUES(?,?,?,?,?)";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		con = miConexion.getConnection();

		try {

			if (validarCliente(miCliente) == false) {

				pstm = (PreparedStatement) con.prepareStatement(consulta);

				pstm.setInt(1, miCliente.getCedulaCliente());

				pstm.setString(2, miCliente.getNombreCliente());

				pstm.setString(3, miCliente.getApellidoCliente());

				pstm.setString(4, miCliente.getNacionalidadCliente());

				pstm.setString(5, miCliente.getCorreoCliente());

				pstm.execute();

				JOptionPane.showMessageDialog(null, "Cliente registrado correctamente");

			} else {
				JOptionPane.showMessageDialog(null, "El cliente ya esta registrado");
			}
		} catch (SQLException e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Error al regitrar los datos");

		} finally {
			miConexion.desconectar();
		}
	}

	@Override
	public void modificarCliente(Cliente miCliente) {

		String consulta = "UPDATE cliente SET cli_id =?,cli_nombre=?,cli_apellido=?,cli_nacionalidad=?,cli_correo=? WHERE cli_id=?";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		con = miConexion.getConnection();

		try {

			if (validarCliente(miCliente) == true) {

				pstm = (PreparedStatement) con.prepareStatement(consulta);
				
				pstm.setInt(1, miCliente.getCedulaCliente());
				
				pstm.setString(2, miCliente.getNombreCliente());
				
				pstm.setString(3, miCliente.getApellidoCliente());
				
				pstm.setString(4, miCliente.getNacionalidadCliente());
				
				pstm.setString(5, miCliente.getCorreoCliente());
				
				pstm.setInt(6, miCliente.getCedulaCliente());
				
				
				pstm.execute();
				
				JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
				
			} else {
				JOptionPane.showMessageDialog(null, "El cliente no se encuentra registrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			miConexion.desconectar();
		}
	}

	@Override
	public void eliminarCliente(Cliente miCliente) {

		String consulta = "DELETE FROM cliente WHERE cli_id=?";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		con = miConexion.getConnection();

		try {

			if (validarCliente(miCliente) == true) {

				pstm = (PreparedStatement) con.prepareStatement(consulta);
				
				pstm.setInt(1, miCliente.getCedulaCliente());
								
				pstm.execute();
				
				JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
				
			} else {
				JOptionPane.showMessageDialog(null, "El cliente no se encuentra registrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			miConexion.desconectar();
		}

	}

	@Override
	public Cliente buscarCliente(Cliente idCliente) {

		Cliente miCliente = new Cliente();

		String consulta = "SELECT * FROM cliente where cli_id=?";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		miConexion.conectarse();

		con = miConexion.getConnection();

		try {

			if (validarCliente(idCliente) == true) {

				pstm = (PreparedStatement) con.prepareStatement(consulta);

				pstm.setInt(1, idCliente.getCedulaCliente());

				rs = pstm.executeQuery();

				rs.next();

				miCliente.setNombreCliente(rs.getString(2));

				miCliente.setApellidoCliente(rs.getString(3));

				miCliente.setNacionalidadCliente(rs.getString(4));

				miCliente.setCorreoCliente(rs.getString(5));
			} else {
				JOptionPane.showMessageDialog(null, "El Cliente que busca no esta registrado");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			miConexion.desconectar();
		}

		return miCliente;
	}

	@Override
	public ArrayList<Cliente> listarCliente() {

		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

		String consulta = "SELECT * FROM cliente";

		Conexion miConexion = new Conexion();

		miConexion.conectarse();

		con = miConexion.getConnection();

		try {

			pstm = (PreparedStatement) con.prepareStatement(consulta);

			rs = pstm.executeQuery();

			while (rs.next()) {

				listaClientes.add(
						new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} finally {
			miConexion.desconectar();
		}
		return listaClientes;

	}

	public boolean validarCliente(Cliente miCliente) {
		String validarDatos = "SELECT * FROM cliente where cli_id=?";
		try {

			pstm = (PreparedStatement) con.prepareStatement(validarDatos);

			pstm.setInt(1, miCliente.getCedulaCliente());

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

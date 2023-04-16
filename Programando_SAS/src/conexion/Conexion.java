package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
	
	static String bd = "unal";
	
	static String login = "root";
	
	static String password = "";
	
	static String url = "jdbc:mysql://localhost/" + bd;

	Connection conn = null;

	public void conectarse() {
		try {
	
			Class.forName("com.mysql.jdbc.Driver");
		
			conn = DriverManager.getConnection(url, login, password);
		
			if (conn != null) {
		
				System.out.println("Conexion a la base de datos " + bd + " esta lista.");
			}
		} catch (SQLException e) {
		
			System.out.println(e);
			
			JOptionPane.showMessageDialog(null,"No se encuentra la base de datos. \n Por favor revise su conexion en el servidor \n y la existencia de la base de datos");
		
			System.out.println("error base de datos desconocida o el servidos no esta en ejecucion");
		
		} catch (ClassNotFoundException e) {

			System.out.println(e);
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public void desconectar() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

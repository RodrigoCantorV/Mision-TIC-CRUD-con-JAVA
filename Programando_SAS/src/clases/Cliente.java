package clases;

import java.util.Date;

public class Cliente {
	private int cedulaCliente;
	private String nombreCliente;
	private String apellidoCliente;
	private String nacionalidadCliente;
	private String correoCliente;
	
	public Cliente(int cedulaCliente, String nombreCliente, String apellidoCliente, String nacionalidadCliente,String correoCliente) {
		this.cedulaCliente = cedulaCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.nacionalidadCliente = nacionalidadCliente;
		this.correoCliente = correoCliente;
	}
	public Cliente() {
		
	}
	public int getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(int cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoCliente() {
		return apellidoCliente;
	}
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}
	public String getNacionalidadCliente() {
		return nacionalidadCliente;
	}
	public void setNacionalidadCliente(String nacionalidadCliente) {
		this.nacionalidadCliente = nacionalidadCliente;
	}
	public String getCorreoCliente() {
		return correoCliente;
	}
	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}
	
	
}

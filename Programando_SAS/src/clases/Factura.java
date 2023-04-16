package clases;

import java.util.Date;

public class Factura {
	private int idFactura;
	private String nombreEmpresaFactura;
	private Date fechaFactura;
	private String ciudadFactura;
	private int clienteIdFactura;

	public Factura(int idFactura, String nombreEmpresaFactura, Date fechaFactura, String ciudadFactura,
			int clienteIdFactura) {
		this.idFactura = idFactura;
		this.nombreEmpresaFactura = nombreEmpresaFactura;
		this.fechaFactura = fechaFactura;
		this.ciudadFactura = ciudadFactura;
		this.clienteIdFactura = clienteIdFactura;
	}

	public Factura() {

	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getNombreEmpresaFactura() {
		return nombreEmpresaFactura;
	}

	public void setNombreEmpresaFactura(String nombreEmpresaFactura) {
		this.nombreEmpresaFactura = nombreEmpresaFactura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getCiudadFactura() {
		return ciudadFactura;
	}

	public void setCiudadFactura(String ciudadFactura) {
		this.ciudadFactura = ciudadFactura;
	}

	public int getClienteIdFactura() {
		return clienteIdFactura;
	}

	public void setClienteIdFactura(int clienteIdFactura) {
		this.clienteIdFactura = clienteIdFactura;
	}

}

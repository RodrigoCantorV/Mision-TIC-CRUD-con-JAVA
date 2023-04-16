package clases;
import java.util.Date;

public class Producto {
	private int idProducto;
	private String nombreProducto;
	private int precioProducto;
	private String descripcionProducto;
	private Date fechaVencimientoProducto;

	public Producto(int idProducto, String nombreProducto, int precioProducto, String descripcionProducto,Date fechaVencimientoProducto) {
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
		this.descripcionProducto = descripcionProducto;
		this.fechaVencimientoProducto = fechaVencimientoProducto;
	}

	public Producto() {}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(int precioProducto) {
		this.precioProducto = precioProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public Date getFechaVencimientoProducto() {
		return fechaVencimientoProducto;
	}

	public void setFechaVencimientoProducto(Date fechaVencimientoProducto) {
		this.fechaVencimientoProducto = fechaVencimientoProducto;
	}
	

}

package clases;

public class ProductoFactura {
	private int idProductoFactura;
	private int cantidadProductoFactura;
	private int idFactura;

	public ProductoFactura() {
		
	}
	
	public ProductoFactura(int idProductoFactura, int cantidadProductoFactura, int idFactura, int idProdcuto) {
		this.idProductoFactura = idProductoFactura;
		this.cantidadProductoFactura = cantidadProductoFactura;
		this.idFactura = idFactura;
		this.idProdcuto = idProdcuto;
	}
	
	public ProductoFactura(int cantidadProductoFactura, int idFactura, int idProdcuto) {
		this.cantidadProductoFactura = cantidadProductoFactura;
		this.idFactura = idFactura;
		this.idProdcuto = idProdcuto;
	}
	
	public int getIdProductoFactura() {
		return idProductoFactura;
	}

	public void setIdProductoFactura(int idProductoFactura) {
		this.idProductoFactura = idProductoFactura;
	}

	public int getCantidadProductoFactura() {
		return cantidadProductoFactura;
	}

	public void setCantidadProductoFactura(int cantidadProductoFactura) {
		this.cantidadProductoFactura = cantidadProductoFactura;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdProdcuto() {
		return idProdcuto;
	}

	public void setIdProdcuto(int idProdcuto) {
		this.idProdcuto = idProdcuto;
	}

	private int idProdcuto;
}

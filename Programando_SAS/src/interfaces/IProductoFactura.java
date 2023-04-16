package interfaces;

import java.util.ArrayList;

import clases.ProductoFactura;

public interface IProductoFactura {
	
	public ArrayList<ProductoFactura> listarProductosFactura(int ID_Factura);
	
	public ArrayList<ProductoFactura> listarTodosProductosFactura();
	
	public void registrarProductoFactura(ProductoFactura miProductoFactura);
	
	public void modificarRegistroFactura(ProductoFactura miProductoFactura);
	
	public int totalProductosPorFactura(int id_factura);
	
	public void eliminarProductoFactura(int idProductoFactura);
	
	
}

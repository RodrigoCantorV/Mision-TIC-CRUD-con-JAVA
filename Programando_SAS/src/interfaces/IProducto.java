package interfaces;

import java.util.ArrayList;

import clases.Producto;

public interface IProducto {
	
	public void crearProdcuto(Producto miProducto);

	public void modificarProdcuto(Producto miProducto);

	public void eliminarProdcuto(Producto miProducto);
	
	public ArrayList<Producto> listarProducto();

	public Producto buscarProducto(Producto idProducto);

}

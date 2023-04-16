package interfaces;

import java.util.ArrayList;

import clases.Cliente;

public interface ICliente {
	
	public void crearCliente(Cliente miCliente);

	public void modificarCliente(Cliente miCliente);

	public void eliminarCliente(Cliente miCliente);

	public ArrayList<Cliente> listarCliente();
	
	public Cliente buscarCliente(Cliente idCliente);
	
}

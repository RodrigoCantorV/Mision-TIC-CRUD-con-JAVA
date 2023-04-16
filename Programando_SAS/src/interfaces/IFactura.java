package interfaces;

import java.util.ArrayList;

import clases.Factura;

public interface IFactura {
	
public int obtenerUltimaFactura();

public ArrayList<Factura> obtenerFacturas();

public void crearFactura(Factura nuevaFactura);

}
